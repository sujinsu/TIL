# HttpClient



`Spring WebClient` : 웹으로 API 호출하기 위해 사용되는 Http client 모듈 중 하나

`RestTemplate` : Java 에서 가장 많이 사용하는 Http  Client



차이점

- RestTemplate은 Blocking 방식
- WebClient는 Non-Blocking 방식



![image](https://github.com/sujinsu/TIL/assets/87465326/4950d08b-f370-40a1-a6e0-dddb58c93c91)


💡 **Blocking vs Non-Blocking**
: 제어권 반환 여부
**Blocking :** 요청하고 응답이 올 때까지 기다리는 방식

**Non-Blocking** : 요청하고 다른 일 수행 후 나중에 응답 신호가 오면 결과를 읽어 처리하는 방식



💡 **동기방식 vs 비동기방식**
: 응답 여부 체크
**동기방식 :** 요청자와 제공자 사이 계속 Connection

**비동기방식** : Connection이 끊어지고 서로간에 이벤트를 통해 통신



## RestTamplate

- **Multi-Thread와 Blocking방식**
![image](https://github.com/sujinsu/TIL/assets/87465326/ee81ec5a-5f95-418d-a1a3-1fc6660cae1f)





- Thread Pool 은 요청자 어플리케이션 구동 시 미리 만듦
- Request 는 Queue에 쌓이고, 가용 스레드 O → 할당 및 처리
- 1요청 1스레드 할당
  - Blocking 방식 → 응답이 올 때까지 다른 요청에 할당 X

```java
/**
* defaultMaxPerRoute : 기본 connection 개수
* maxTotal : 최대 connection 개수
**/

@Configuration
public class RestTemplateConfig {
	public RestTemplate getRestTemplate(int defaultMaxPerRoute, int maxTotal) {
		PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
		connManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
		connManager.setMaxTotal(maxTotal);

		HttpClient client = HttpClientBuilder.create().setConnectionManager(connManager).build();

		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(client);
		factory.setConnectTimeout(3000);
		factory.setReadTimeout(3000);

		return new RestTemplate(factory);

	}

	@Bean
	public RestTemplate restTemplate() {
		return getRestTemplate(20, 50);
	}
}
```

- HttpClient 를 SpringBean 으로 등록
  - SpringBean
    - http나 db의 connection pool과 같이 미리 생성된 class
    - Application 시작 시 Spring Ioc (Inversion Of Control) 컨테이너에 의해 생성 및 관리



 💡 요청을 처리할 스레드가 있다면 문제 X

BUT 스레드가 다 차는 경우 요청은 Queue에 대기 네트워킹 및 DB 통신 시 대기 문제가 여러 스레드에서 발생하면

→ 가용 스레드 수 현저히 감소, 서비스 속도 감소



## Spring WebClient

- **Single Thread 와 Non-Blocking 방식**
- core 당 1개의 Thread 이용
![image](https://github.com/sujinsu/TIL/assets/87465326/fcd1c873-1ded-45fc-9116-cdb7bcbeaf01)


- 각 Request 는 Event Loop 내에 Job으로 등록
- EventLoop는 각 Job을 제공자에게 요청
  - 결과를 기다리지 않고 다른 job처리
  - 제공자로부터 callback으로 응답이 오면, 그 결과를 요청자에게 제공
- 이벤트에 반응형으로 동작하도록 설계

```java
@GetMapping("/test2")
    public Mono<String> doTest2() {
        HttpClient httpClient = HttpClient.create()
            .tcpConfiguration(
                client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000) //miliseconds
                    .doOnConnected(
                        conn -> conn.addHandlerLast(new ReadTimeoutHandler(5))  //sec
                            .addHandlerLast(new WriteTimeoutHandler(60)) //sec
                    )
            );    

        WebClient client = WebClient.builder()
            .baseUrl("<http://localhost:5011>")
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .build();
        
        return client.get() 
            .uri("/webclient/test-builder")
            .retrieve()
            .bodyToMono(String.class);          
    }
```

- `timeout`

  - HttpClient 생성 시 지정
  - webClient.clientConnector 이용하여 적용

- `baseUrl`

  - host 지정
  - uri 에는 path 만 지정하여 사용

- `filter`

  - request, response 컨트롤

  ```java
  @GetMapping("/test2")
      public Mono<String> doTest2() {
  		...
          
          WebClient client = WebClient.builder()
              .baseUrl("<http://localhost:5011>")
              .clientConnector(new ReactorClientHttpConnector(httpClient))
              .filter(
                  (req, next) -> next.exchange(
                      ClientRequest.from(req).header("from", "webclient").build()
                  )
              )
              .filter(
                  ExchangeFilterFunction.ofRequestProcessor(
                      clientRequest -> {
                          log.info(">>>>>>>>>> REQUEST <<<<<<<<<<");
                          log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
                          clientRequest.headers().forEach(
                              (name, values) -> values.forEach(value -> log.info("{} : {}", name, value))
                          );
                          return Mono.just(clientRequest);
                      }
                  )
              )
              .filter(
                  ExchangeFilterFunction.ofResponseProcessor(
                      clientResponse -> {
                          log.info(">>>>>>>>>> RESPONSE <<<<<<<<<<");
                          clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.info("{} : {}", name, value)));
                          return Mono.just(clientResponse);
                      }
                  )
              )
              .build();
  		
          ...
      }
  ```

  - 첫번째 필터 : RequestHeader 추가
  - 두번째 필터 : RequestHeader 로깅
  - 세번째 필터 : Response Header 로깅

- Spring Bean

  - webClient를 사용하는 모든 수행에서 공통 HttpRequest Header, Cookie 가 있다면
    - defaultHeader, defaultCookie 추가하여 사용
  - Controller 에서 `@Autowired private WebClient webClient;` 로 미리 만들어진 webclient 사용

  ```java
  @Configuration
  public class WebClientConfig {
      private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(getClass());
      
      @Bean
      public WebClient webClient() {
          HttpClient httpClient = HttpClient.create()
              .tcpConfiguration(
                  client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000) //miliseconds
                      .doOnConnected(
                          conn -> conn.addHandlerLast(new ReadTimeoutHandler(5))  //sec
                              .addHandlerLast(new WriteTimeoutHandler(60)) //sec
                      )
              );
  
          //Memory 조정: 2M (default 256KB)
          ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
              .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(2*1024*1024)) 
              .build();
  
          return WebClient.builder()
              .baseUrl("<http://localhost:5011>")
              .clientConnector(new ReactorClientHttpConnector(httpClient))
              .filter(
                  (req, next) -> next.exchange(
                      ClientRequest.from(req).header("from", "webclient").build()
                  )
              )
              .filter(
                  ExchangeFilterFunction.ofRequestProcessor(
                      clientRequest -> {
                          log.info(">>>>>>>>>> REQUEST <<<<<<<<<<");
                          log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
                          clientRequest.headers().forEach(
                              (name, values) -> values.forEach(value -> log.info("{} : {}", name, value))
                          );
                          return Mono.just(clientRequest);
                      }
                  )
              )
              .filter(
                  ExchangeFilterFunction.ofResponseProcessor(
                      clientResponse -> {
                          log.info(">>>>>>>>>> RESPONSE <<<<<<<<<<");
                          clientResponse.headers().asHttpHeaders().forEach((name, values) -> values.forEach(value -> log.info("{} : {}", name, value)));
                          return Mono.just(clientResponse);
                      }
                  )
              )
              .exchangeStrategies(exchangeStrategies)            
              .defaultHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.3")
              .defaultCookie("httpclient-type", "webclient")
              .build();
      }
  }
  ```

  - baseUrl 재지정 → `mutate()`

  ### 예시 코드

  B

  - build.gradle

  ```java
  		// webflux
      implementation ('org.springframework.boot:spring-boot-starter-webflux')
              
      // httpclient
      implementation ('org.springframework.boot:spring-boot-starter-web-services')
      implementation ('org.apache.httpcomponents:httpclient:4.5')
  ```

  - webClientConfig.java

  ```java
  @Slf4j
  @Configuration
  public class WebClientConfig {
  
  	private HttpClient httpClient() {
  
  		ConnectionProvider provider = ConnectionProvider.builder("custom-pool")
  				.maxConnections(1000)
  				.pendingAcquireTimeout(Duration.ofMillis(0))
  				.pendingAcquireMaxCount(-1)
  				.evictInBackground(Duration.ofSeconds(27))
  				.maxIdleTime(Duration.ofSeconds(58))
  				.maxLifeTime(Duration.ofSeconds(58))
  				.build();
  		
  		return HttpClient.create(provider).secure(t -> {
  			try {
  				t.sslContext(SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build());
  			} catch (SSLException e) {
  				log.error("SSLException\\n", e);
  			}
  			})
  				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
  				.option(ChannelOption.SO_KEEPALIVE, true)
  				.doOnConnected(conn -> conn
  						.addHandlerLast(new ReadTimeoutHandler(50))
  						.addHandlerLast(new WriteTimeoutHandler(5)));				
  	}
  
  	@Bean
  	@Scope("prototype")
  	public WebClient restCustomClient(WebClient.Builder webClientBuilder) {
  
  		return webClientBuilder.clientConnector(new ReactorClientHttpConnector(httpClient()))
  				.defaultHeaders(httpHeaders -> {
  					httpHeaders.setContentType(MediaType.APPLICATION_JSON);
  					httpHeaders.set("Accept", MediaType.APPLICATION_JSON_VALUE);
  				})
  				.filter(requestLog())
  				.build();
  	}
  
  	@Bean
  	@Scope("prototype")
  	public WebClient formCustomClient(WebClient.Builder webClientBuilder) {
  
  		return webClientBuilder.clientConnector(new ReactorClientHttpConnector(httpClient()))
  				.defaultHeaders(httpHeaders ->
  					httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED)
  				)
  				.filter(requestLog())
  				.build();
  	}
  	
  	@Bean
  	@Scope("prototype")
  	public WebClient multipartformCustomClient(WebClient.Builder webClientBuilder) {
  
  		return webClientBuilder.clientConnector(new ReactorClientHttpConnector(httpClient()))
  				.defaultHeaders(httpHeaders ->
  					httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA)
  				)
  				.filter(requestLog())
  				.build();
  	}
  	
  	@Bean
  	@Scope("prototype")
  	public WebClient customClient(WebClient.Builder webClientBuilder) {
  
  		return webClientBuilder.clientConnector(new ReactorClientHttpConnector(httpClient())).build();
  	}
  
  	private static ExchangeFilterFunction requestLog() {
  		return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
  			log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
  			clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("Header: {}={}", name, value)));
  			return Mono.just(clientRequest);
  		});
  	}
  }
  ```

  R

  ```java
  String res = webClient.post()
                  .uri(uri)
                  .body(BodyInserters.fromValue(om.writeValueAsString(dto)))
                  .accept(MediaType.APPLICATION_JSON)
                  .retrieve()
                  .bodyToMono(String.class)
                  .block();String res = webClient.post()
                  .uri(uri)
                  .body(BodyInserters.fromValue(om.writeValueAsString(dto)))
                  .accept(MediaType.APPLICATION_JSON)
                  .retrieve()
                  .bodyToMono(String.class)
                  .block();
  ```







참고자료 )

https://happycloud-lee.tistory.com/220
https://steady-coding.tistory.com/611

https://docs.spring.io/spring-framework/reference/web/webflux-webclient.html

https://tecoble.techcourse.co.kr/post/2021-07-25-resttemplate-webclient/

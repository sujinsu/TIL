

# **[R2DBC](https://r2dbc.io/)**



[R2DBC](https://r2dbc.io/)

> Reacitve Relational Database Connectivity

Spring5 & SpringBoot2

- 적은 수의 스레드로 동시성 처리
- 더 적은 하드웨어 리소스로 확장하는 non-blocking 애플리케이션  스택
- `spring webflux & r2dbc` 조합은  `spring mvc & jpa` 과 같이 함께 쓰기 좋음.

```java
Reactive

변화, 가용성, 처리 가능 상태에 반응하는 것에 중심을 두고 만든 프로그래밍 모델
작업을 기다리기보다 완료되거나 데이터를 사용할 수 있게 되면 반응

>> 논블로킹도 리액티브
```

[What is Reactive?](https://godekdls.github.io/Spring Data R2DBC/whatisreactive/)

- 의존성

```
implementation 'org.springframework.boot:spring-boot-starter-data-r2dbc'
```

### **특징**

- webflux 코드는 반드시 Mono<T> 로 return





## **WebFlux**

> spring5에서 새로 추가된 모듈
>
> reactive 스타일의 어플리케이션 개발을 돕는 모듈
>
> 장점
>
> - 고성능, spring과 완벽한 통합
> - netty지원
>   - 비동기 이벤트 기반 네트워크 응용프로그램 프레임워크
>   - NIO 방식(Non-Blocking Input Ouput) : 무한대기 현상 회피 가능
> - 비동기 non-blocking 메세지 처리
>
> 단점
>
> - 오류 처리 복잡, Back pressure 기능 없음

```
## 기존 Spring 블록킹 방식

웹에서 서버에 요청이 왔을 때, 응답이 모두 종료될 때까지 블록킹
So, 동시 요청 처리를 위해 멀티쓰레드 지원
(하나의 작업이 thread에서 진행 & 다른 thread가 다른 요청 받아서 처리)

>> thread가 느러아 할당에 필요한 리소스 늘어나 비효율 가능성 증가 !

## Spring5의 Non Blocking

클라이언트 요청에 별도 thread 생성 X, buffer 사용하여 요청을 받고 뒤에서 처리하는 thread는 여러개를 두어서 처리
>> node.js 의 싱글스레드 논블로킹을 따라가는 것과 같음.

Q. Why 블로킹 -> 논블로킹 ?

A. 수천 개의 스트림 데이터가 초당 계속 업데이트 되는 시스템, 적절한 응답이 필요하면 부담이 커 이를 효율적으로 처리하기 위해
```









# **Mono**

```java
Reactive Streams 의 Publisher 인터페이스를 구현하는 구현체

0~1개의 데이터 처리하기 위한 Reactor 객체

여러 스트림을 하나의 결과로 모아줄 때 사용

cf) 비슷한 개념 : 자바에서 하나의 결과 or 결과가 없는 경우 >> List  사용 X
```

# **Flux**

```java
0~N개의 데이터 처리하기 위한 Reactor 객체

각각의 Mono를 합쳐, 여러개의 값을 처리할 때 사용
```







## **리액티브 스트림**

> Publisher 전송 >> 데이터 sequence 대로 저송
>
> Subscriber 가 데이터 수신

- next : 다음신호
- complete : 신호가 끝남
- error : 신호 보내는 도중 에러 발생
- 기본 예시

```
// Integer 값을 발생하는 Flux 생성
Flux<Integer> seq = Flux.just(4, 5, 6);

// 구독
seq.subscribe(System.out::println);
```

- 예시

```
Flux.just(1,2,3);
// --1-2-3 -|-> 1,2,3 세개의 next 신호, 마지막에 complete 신호 발생, 시퀀스 종료

Flux.just();
// 아무런 시퀀스가 없다면, complete 신호만 발생

Mono.just(1);
// --1-|-> Mono는 최대 발생할 수 있는 값 1개
```

- 구독 & 신호 발생

  - 구독이 일어났을 때 doOnNext메서드 실행
  - 시퀀스는 바로 신호 발생 X, 구독 시점에 신호 발생 O

  ```
  Flux.just(1, 2, 3)
      .doOnNext(i -> System.out.println("호출: " + i))
      .subscribe(i -> System.out.println("출력 결과: " + i));
  
  // 호출 :1
  // 출력 결과 :1
  // 호출 :2
  // 출력 결과 :2
  // 호출 :3
  // 출력 결과 :3
  ```

[참고](https://wedul.site/569)

[참고2](https://brunch.co.kr/@purpledev/28)

package com.example.demo.common;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.http.HttpClient;

@Slf4j
@Configuration
public class WebClientConfig {
//    @Bean
//    public WebClient webClient() {
//        return WebClient.builder().build();
//    }
    @Bean
    public WebClient customWebClient() {
        return WebClient.builder()
            .filter(ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
                // 요청 세부사항 로그 출력
                System.out.println("Request: " + clientRequest.method() + " " + clientRequest.url());
                clientRequest.headers().forEach((name, values) -> values.forEach(value -> System.out.println(name + ": " + value)));
                return Mono.just(clientRequest);
            }))
            .filter(ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
                // 응답 세부사항 로그 출력
                System.out.println("Response Status Code: " + clientResponse.statusCode());
                return Mono.just(clientResponse);
            }))
            .build();
}

}

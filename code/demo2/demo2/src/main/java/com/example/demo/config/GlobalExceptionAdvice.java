package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartException;
import reactor.core.publisher.Mono;

@Slf4j
@ControllerAdvice

public class GlobalExceptionAdvice {

    @ExceptionHandler(MultipartException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<String> handleMultipartException(MultipartException e) {
        return Mono.just("파일 크기가 너무 큽니다: " + e.getMessage());
    }
}

package com.example.demo.web.controller;

import com.example.demo.util.FileUploadUtil;
import io.swagger.annotations.Api;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Api("AsyncDemo")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AsyncDemoController {

    private final WebClient webClient;
    private FileUploadUtil fileUploadUtil;

    private static final String X_ORIGINAL_FORWARDED_FOR = "X-Original-Forwarded-For";
    @Value("${spring.task.uploadpath}")
    private String uploadDir;
    @PostConstruct
    public void init() {
        this.fileUploadUtil = new FileUploadUtil(uploadDir, 10 * 1024 * 1024L); // 10M
    }
    @PostMapping("/upload2")
    public Mono<String> handleFileUpload(@RequestPart("file") FilePart file, ServerHttpRequest request) throws IOException {
        String userInfo = getRemoteHostAddress(request);
//        fileUploadUtil.uploadFiles("filePath", List.of(file));
        Flux<DataBuffer> dataBufferFlux = convertToDataBuffer(file);
        return sendFileToAnotherApplication(file.filename(), userInfo, dataBufferFlux);
    }

    private Flux<DataBuffer> convertToDataBuffer(FilePart file) {
        return file.content();
    }

    private Mono<String> sendFileToAnotherApplication(String filename, String userInfo, Flux<DataBuffer> dataBufferFlux) {
        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("userInfo", userInfo).contentType(MediaType.TEXT_PLAIN);
        bodyBuilder.asyncPart("file", dataBufferFlux, DataBuffer.class);

        return webClient.post()
                .uri("http://localhost:8081/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .exchangeToMono(response -> {
                    if (response.statusCode().isError()) {
                        // 에러 상태 코드의 경우, 에러 메시지 추출
                        return response.bodyToMono(String.class)
                                .flatMap(errorMsg -> Mono.just("파일 전송 실패: " + errorMsg));
                    } else {
                        // 성공 상태 코드의 경우, 성공 메시지 반환
                        return Mono.just("파일 전송 완료");
                    }
                })
                .onErrorResume(e -> Mono.just("파일 전송 실패: " + e.getMessage()));
    }

    private String getRemoteHostAddress(ServerHttpRequest request) {
        if (Objects.isNull(request.getHeaders().get(X_ORIGINAL_FORWARDED_FOR))) {
            List<String> xForwardedValues = extractXForwardedValues(request);
            Collections.reverse(xForwardedValues);
            if (!xForwardedValues.isEmpty()) {
                int index = Math.min(xForwardedValues.size(), Integer.MAX_VALUE) - 1;
                return new InetSocketAddress(xForwardedValues.get(index), 0).getAddress().getHostAddress();
            }
            return request.getRemoteAddress().getAddress().getHostAddress();
        } else {
            return request.getHeaders().get("X-Forwarded-For").get(0);
        }
    }

    private List<String> extractXForwardedValues(ServerHttpRequest request) {

        List<String> xForwardedValues = request.getHeaders().get("X-Forwarded-For");
        if (xForwardedValues == null || xForwardedValues.isEmpty()) {
            return Collections.emptyList();
        }
        if (xForwardedValues.size() > 1) {
            return Collections.emptyList();
        }
        List<String> values = Arrays.asList(xForwardedValues.get(0).split(", "));
        if (values.size() == 1 && !StringUtils.hasText(values.get(0))) {
            return Collections.emptyList();
        }
        return values;
    }
}

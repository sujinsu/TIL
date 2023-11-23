//package com.example.demo.web.controller;
//
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.text.PDFTextStripper;
//import org.jetbrains.annotations.NotNull;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.codec.multipart.FilePart;
//import org.springframework.http.codec.multipart.Part;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestPart;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//import java.io.File;
//import java.io.IOException;
//
////import io.swagger.annotations.Api;
//@Tag(name = "Demo2", description = "Demo2 API")
//@RestController
//@Slf4j
//public class Demo2Controller {
//
//    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public Mono<ResponseEntity<String>> handleFileUpload(@RequestPart("file") @NotNull Flux<FilePart> parts,
//                                         @RequestPart("userInfo") String userInfo) {
//
//        System.out.println("userInfo = " + userInfo);
//        return parts
//                .filter(part -> part instanceof FilePart  && part.name().equals("file"))
//                .cast(FilePart.class)
//                .flatMap(filePart -> {
//                    try {
//                        String fileExtension = filePart.filename().toLowerCase();
//                        if (fileExtension.equals("pdf")) {
//                            PDDocument document = PDDocument.load((File) filePart.content().toStream());
//                            PDFTextStripper pdfStripper = new PDFTextStripper();
//
//                            // 첫 페이지만 읽기
//                            pdfStripper.setStartPage(1);
//                            pdfStripper.setEndPage(1);
//
//                            String text = pdfStripper.getText(document);
//
//                            // 추출된 텍스트를 라인별로 나누고, 최대 5줄만 출력하기
//                            String[] lines = text.split("\n");
//                            for (int i = 0; i < Math.min(5, lines.length); i++) {
//                                System.out.println(lines[i]);
//                            }
//
//                            document.close();
//                        }
//                        return Mono.just(ResponseEntity.ok("파일 업로드 및 처리 완료"));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                        return Mono.just(ResponseEntity.badRequest().body("파일 처리 중 오류 발생: " + e.getMessage()));
//                    }
//                })
//                .next() // 첫 번째 Mono만 반환
//                .defaultIfEmpty(ResponseEntity.ok("파일이 없습니다."));
//    }
//}

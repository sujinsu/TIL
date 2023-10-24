package com.example.demo.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Api("Demo2")
@RestController
public class Demo2Controller {

    @ApiOperation(value = "PDF 업로드 확인", notes = "")
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestPart("file") MultipartFile file, @RequestPart("userInfo") String userInfo) {
        try {
            System.out.println("Received file: " + file.getOriginalFilename());
            System.out.println("User Info: " + userInfo);

            PDDocument document = PDDocument.load(file.getInputStream());
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // 첫 페이지만 읽기
            pdfStripper.setStartPage(1);
            pdfStripper.setEndPage(1);

            String text = pdfStripper.getText(document);

            // 추출된 텍스트를 라인별로 나누고, 최대 5줄만 출력하기
            String[] lines = text.split("\n");
            for (int i = 0; i < Math.min(5, lines.length); i++) {
                System.out.println(lines[i]);
//                System.out.println(new String(lines[i].getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8));
            }

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to upload the file.");
        }
        return ResponseEntity.ok("File uploaded successfully.");
    }
}

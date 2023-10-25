package com.example.demo.web.controller;

import com.example.demo.util.FileUploadUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Api("Demo")
@RestController
@Slf4j
public class DemoController {
    @Value("${spring.task.uploadpath}")
    private String uploadDir;
    private FileUploadUtil fileUploadUtil;

    @PostConstruct
    public void init() {
        this.fileUploadUtil = new FileUploadUtil(uploadDir, 10 * 1024 * 1024L); // 10M
    }

    @ApiOperation(value = "PDF 업로드", notes = "")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,  HttpServletRequest request) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please provide a valid file.");
        }
        fileUploadUtil.uploadFiles("filePath", List.of(file));

        String userInfo = retrieveUserInfo(request);
        // Call method to send the file and user info to another server
        sendFileToServer(file, userInfo);

        return ResponseEntity.ok("File uploaded successfully.");
    }


    private String retrieveUserInfo(HttpServletRequest request) {
        final String UNKOWN = "unknown";
        final String[] HEADERS = {
                "X-Forwarded-For"
                , "Proxy-Client-IP"
                , "WL-Proxy-Client-IP"
                ,"HTTP_CLIENT_IP"
                ,"HTTP_X_FORWARDED_FOR"};

        boolean find = false;
        String ip = null;
        for(String header : HEADERS){
            ip = request.getHeader(header);
            if(StringUtils.isEmpty(ip) || UNKOWN.equalsIgnoreCase(ip)){
            } else {
                find=true;
                break;
            }
        }

        if(!find){
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    private void sendFileToServer(MultipartFile file, String userInfo) {
        // Logic to send file and user info to another server
        String serverUrl = "http://localhost:8081/upload";

        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
        bodyMap.add("file", new FileSystemResource(convert(file)));
        bodyMap.add("userInfo", userInfo);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(serverUrl, HttpMethod.POST, requestEntity, String.class);
            System.out.println("Response code: " + response.getStatusCode());
        }catch (Exception e){
            log.info("Demo : {} PDF 중계 실패", e.getMessage() );
        }
    }

    private File convert(MultipartFile file) {
        File convFile = new File(file.getOriginalFilename());
//        try {
//            convFile.createNewFile();
//            FileOutputStream fos = new FileOutputStream(convFile);
//            fos.write(file.getBytes());
//            fos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return convFile;
    }
}

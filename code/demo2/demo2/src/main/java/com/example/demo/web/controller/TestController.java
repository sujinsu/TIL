package com.example.demo.web.controller;



import com.example.demo.modules.value.ChatResponseVo;
import com.example.demo.modules.value.ResponseVo;
import com.example.demo.modules.value.RagFileResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Api(description = "Demo2 API")
@Slf4j
@RequestMapping(value="/api_v1")
public class TestController {

    @PostMapping(value = "/chat/{mode}")
    public ChatResponseVo chatCall(@PathVariable String mode) {
        ChatResponseVo chatResponseVo = new ChatResponseVo();
        ResponseVo.SysInfo sysInfo = new ResponseVo.SysInfo();
        ChatResponseVo.ProcessResult result = new ChatResponseVo.ProcessResult();
        ChatResponseVo.ProcessResult.Usage usage = new ChatResponseVo.ProcessResult.Usage();
        result.setUsage(usage);
        chatResponseVo.setProcessResult(result);
        chatResponseVo.setSysInfo(sysInfo);
        System.out.println("mode = " + mode);
        return chatResponseVo;
    }


    @GetMapping(value = "/rag/{chatSessionId}")
    public RagFileResponseVo ragCall(@PathVariable String chatSessionId) {
        RagFileResponseVo ragFileResponseVo = new RagFileResponseVo();
        ResponseVo.SysInfo sysInfo = new ResponseVo.SysInfo();
        RagFileResponseVo.ProcessResult processResult = new RagFileResponseVo.ProcessResult();
        RagFileResponseVo.ProcessResult.FileDetail fileDetail = new RagFileResponseVo.ProcessResult.FileDetail();

        processResult.setFileDetail(fileDetail);
        ragFileResponseVo.setProcessResult(processResult);
        ragFileResponseVo.setSysInfo(sysInfo);


        System.out.println("chatSessionId = " + chatSessionId);
        return ragFileResponseVo;
    }


    @ApiOperation(value = "PDF 업로드 확인", notes = "")
    @RequestMapping(value = "/rag/{chatSessionId}", method = {RequestMethod.POST}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @RequestMapping(value = "/rag/{chatSessionId}", method = {RequestMethod.POST})
    public ResponseVo ragFileUploadCall(@PathVariable String chatSessionId, @RequestPart(value="file", required = true) MultipartFile file) {
//        MultipartFile file = files.get(0);
        ResponseVo responseVo = new ResponseVo();
        ResponseVo.SysInfo sysInfo = new ResponseVo.SysInfo();
        ResponseVo.SysInfo.Status status = new ResponseVo.SysInfo.Status();

        sysInfo.setStatus(status);
        responseVo.setSysInfo(sysInfo);
        System.out.println("User Info: " + chatSessionId);
        try {
            System.out.println("Received file: " + file.getOriginalFilename());
            System.out.println("User Info: " + chatSessionId);

            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename()).toLowerCase();

            if(fileExtension.equals("pdf")){
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
            }

        } catch (Exception e) {
            e.printStackTrace();
            return responseVo;
        }
        return responseVo;
    }
}

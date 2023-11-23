package com.example.demo.web.value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RagFileResponseVo {
    private ResponseVo.SysInfo sysInfo;

    private ProcessResult processResult;
    @Getter
    @Setter
    @NoArgsConstructor
    public static class ProcessResult {
        private List<FileDetail> fileList;
        private FileDetail fileDetail;
        @Getter
        @Setter
        @NoArgsConstructor
        public static class FileDetail {
            private String fileNm;
            private String ragCompleteYn;
        }
    }
}

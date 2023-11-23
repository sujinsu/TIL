package com.example.demo.modules.value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatResponseVo {

    private ResponseVo.SysInfo sysInfo;

    private ProcessResult processResult;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ProcessResult {

        private String gptRes;

        private Usage usage;
        @Getter
        @Setter
        @NoArgsConstructor
        public static class Usage {
            private int completionTokens;
            private int promptTokens;
            private int totalTokens;

        }
    }
}

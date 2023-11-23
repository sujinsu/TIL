package com.example.demo.web.value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseVo {

    private SysInfo sysInfo;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class SysInfo{
        private Status status;
        @Getter
        @Setter
        @NoArgsConstructor
        public static class Status{
            private String code;
            private String msg;
        }
    }

}
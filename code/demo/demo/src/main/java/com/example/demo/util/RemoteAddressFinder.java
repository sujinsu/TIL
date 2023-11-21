//package com.example.demo.util;
//
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//
//public class RemoteAddressFinder {
//
//    public static String getAddress(HttpServletRequest request) {
//        final String UNKOWN = "unknown";
//        final String[] HEADERS = {
//                "X-Forwarded-For"
//                , "Proxy-Client-IP"
//                , "WL-Proxy-Client-IP"
//                ,"HTTP_CLIENT_IP"
//                ,"HTTP_X_FORWARDED_FOR"
//                // 1-3일 추가
//                ,"X-Real-IP"
//                ,"HTTP_X_FORWARDED"
//                ,"HTTP_VIA"
//                ,"IPV6_ADR"
//                ,"X-RealIP"
//                ,"REMOTE_ADDR"
//        };
//
//        boolean find = false;
//        String ip = null;
//        //String headers = "";
//        for(String header : HEADERS){
//            ip = request.getHeader(header);
//            if(StringUtils.isEmpty(ip) || UNKOWN.equalsIgnoreCase(ip)){
//                //headers += String.format("no exist header : {%s} ,",header);
//                continue;
//            } else {
//                //headers += String.format("client ip find header : {%s}",header);
//                find=true;
//                break;
//            }
//        }
//
//        if(!find){
//            ip = request.getRemoteAddr();
//        }
//        return ip;
//    }
//}

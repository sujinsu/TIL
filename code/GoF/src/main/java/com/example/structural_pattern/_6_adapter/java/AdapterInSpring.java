package com.example.structural_pattern._6_adapter.java;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * HandlerAdapter : 핸들러(컨트롤러를 구현하는 요청을 처리 및 응답 로직 처리)가 다양한 형태일 수 있음. 기존 helloController같은 예제 외에도 많은 핸들러 존재
 * -> DispatcherServlet.doDispatch 읽어 보기
 * ㄴ 핸들러는 object : 특정한 하나가 아니라 다양한 형태가 될 수 있기 때문에
 * ㄴ spring은 http sevlet request와 response 를 받아서 model and view를 넘겨주는 어댑터를 제공해준다.
 */
public class AdapterInSpring {

    public static void main(String[] args) {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();
        HandlerAdapter handlerAdapter = new RequestMappingHandlerAdapter();
    }
}

package com.example.structural_pattern._12_proxy.java;

import com.example.structural_pattern._12_proxy.after.GameService;
import com.example.structural_pattern._12_proxy.after.DefaultGameService;

import java.lang.reflect.Proxy;

/**
 * 런타임에 프록시 인스턴스 생성
 */
public class ProxyInJava {

    public static void main(String[] args) {
        ProxyInJava proxyInJava = new ProxyInJava();
        proxyInJava.dynamicProxy();
    }

    private void dynamicProxy() {
        GameService gameServiceProxy = getGameServiceProxy(new DefaultGameService());
        gameServiceProxy.startGame();
    }

    private GameService getGameServiceProxy(GameService target) {
        // newProxyInstance 메서드 : 첫번째 파라미터 - 클래스로더, 두번째 파라미터 - 프록시가 동적으로 생성해야하는 인터페이스 타입, 세번째 파라미터 - invocationHandler 타입 인스턴스
        return  (GameService) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{GameService.class}, (proxy, method, args) -> {
                    System.out.println("O");
                    method.invoke(target, args);
                    System.out.println("ㅁ");
                    return null;
                });
    }
}

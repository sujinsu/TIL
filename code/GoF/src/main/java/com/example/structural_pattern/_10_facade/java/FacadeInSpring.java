package com.example.structural_pattern._10_facade.java;

import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 브릿지 패턴의 예와 같음
 * BUT 브릿지 패턴은 상이한 인터페이스 연결 목적
 * 퍼사드 패턴은 구체적인 기술을 뒤로 감추는 목적
 */
public class FacadeInSpring {

    // JAVA 코드지만 자바 라이브러리에 종속적이지 않음
    public static void main(String[] args) {
        MailSender mailSender = new JavaMailSenderImpl();

        // JDBC, Hibernate, JPA 등 처리방식이 다를 수 있지만 인터페이스 뒤로 숨긴 것
        PlatformTransactionManager platformTransactionManager = new JdbcTransactionManager();

        // Spring MVC API 도 크게 보면 일종의 퍼사드 << 자바 제공 서블릿을 감싸고 있음
        // -> spring 5부터는 MVC 어노테이션과 API 쓰더라도 웹플럭스로 동작 가능
        // -> 즉, 서블릿이 아닐 수도 있다는 것 ! Facade Pattern!
    }
}

package com.example.structural_pattern._7_bridge.java;

import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * 브릿지 패턴의 일부분이라고 할 수 있는 구체적인 것의 인터페이스를 스프링이 많이 만듦
 *
 * MailSender : 구체적 부분의 인터페이스
 * java Mail Sender, JDBC 트랜잭션 매니저 : 구현체
 */
public class BridgeInSpring {

    public static void main(String[] args) {
        MailSender mailSender = new JavaMailSenderImpl();

        PlatformTransactionManager platformTransactionManager = new JdbcTransactionManager();
    }
}

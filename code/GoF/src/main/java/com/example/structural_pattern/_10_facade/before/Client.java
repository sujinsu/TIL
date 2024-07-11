package com.example.structural_pattern._10_facade.before;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * 퍼사드 : 건물의 전경, 앞 문 < 안이 어떤지 알 수 없음
 *
 * 클라이언트 코드가 직접적으로 properties, session, mime message, transport, exception 까지 다양한 의존성 생김
 * ㄴ 루즐리 커플드 : 의존성 변경이 쉽고, 테스트도 편리하게
 */
public class Client {

    public static void main(String[] args) {
        String to = "keesun@whiteship.me";
        String from = "whiteship@whiteship.me";
        String host = "127.0.0.1";

        Properties properties = System.getProperties(); //smtp 서버나 포트 정의
        properties.setProperty("mail.smtp.host", host);

        // 위 properties로 세션 생성 및 자바 메일 세션 >> Mime 메세지를 만들고 정보 설정 >> transport를 가지고 send
        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Test Mail from Java Program");
            message.setText("message");

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

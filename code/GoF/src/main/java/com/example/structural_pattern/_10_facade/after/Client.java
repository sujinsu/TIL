package com.example.structural_pattern._10_facade.after;

/**
 * 퍼사드 패턴 : 복잡한 서브 시스템 의존성을 최소화하는 방법
 * ㄴ 클라이언트가 사용해야하는 복잡한 서브 시스템 의존성을 간단한 인터페이스로 추상화
 * ㄴ mocking하기 쉬워짐
 *
 * EmailSender를 인터페이스로 만들고 디폴트 이메일 센더, 자바 이메일 센더 등 만들면 더욱 유연
 *
 *
 * (+) : 서브 시스템에 대한 의존성을 한 곳으로 몰아둘 수 있음
 * (+) : 서브 시스템에 대한 높은 이해가 필요없음. 디테일한 부분은 뒤로 감추고 클라이언트는 편리하게 사용
 * (-) : 퍼사드 자체가 서브 시스템에 대한 모든 의존성을 다 가지게 됨.
 */
public class Client {

    public static void main(String[] args) {
        EmailSettings emailSettings = new EmailSettings();
        emailSettings.setHost("127.0.0.1");

        EmailSender emailSender = new EmailSender(emailSettings);

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setFrom("keesun");
        emailMessage.setTo("whiteship");
        emailMessage.setCc("일남");
        emailMessage.setSubject("오징어게임");
        emailMessage.setText("밖은 더 지옥이더라고..");

        emailSender.sendEmail(emailMessage);
    }
}

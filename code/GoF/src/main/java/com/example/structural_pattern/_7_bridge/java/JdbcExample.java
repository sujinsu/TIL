package com.example.structural_pattern._7_bridge.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 여러 sql 드라이버가 있고, 이 드라이버에 여러 구현체가 있음
 *
 * DriverManager, connection, statement 는 추상화쪽
 * Driver 는 구현체 (implementation)
 *
 * ㄴ 새로운 디비가 만들어진다고 드라이버 매니저가 바뀌진 않음 추상화된 인터페이스는 바뀌지 않음
 * (+) : Open Closed Principle이 제대로 적용되고 있는 브릿지패턴
 */
public class JdbcExample {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName ("org.h2.Driver");

        try (Connection conn = DriverManager.getConnection ("jdbc:h2:mem:~/test", "sa","")) {

            String sql =  "CREATE TABLE  ACCOUNT " +
                    "(id INTEGER not NULL, " +
                    " email VARCHAR(255), " +
                    " password VARCHAR(255), " +
                    " PRIMARY KEY ( id ))";

            Statement statement = conn.createStatement();
            statement.execute(sql);

            // 브릿지 패턴 설명을 위해 인터페이스를 최대한 노출시키는 코드 (실제로는 이렇게 사용 안 함)
//            PreparedStatement statement1 = conn.prepareStatement(sql);
//            ResultSet resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.example.behavioral_patterns._14_command.java;

import com.example.behavioral_patterns._14_command.after.Command;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple JDBC Insert : JDBC template을 내부적으로 사용
 */
public class CommandInSpring {

    private DataSource dataSource;

    public CommandInSpring(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(Command command) {
        // SimpleJdbcInsert : insert query를 실행하는 데 필요한 모든 정보를 가지고 있는 하나의 커맨드 오브젝트
        SimpleJdbcInsert insert = new SimpleJdbcInsert(dataSource)
                .withTableName("command")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> data = new HashMap<>();
        data.put("name", command.getClass().getSimpleName());
        data.put("when", LocalDateTime.now());
        insert.execute(data);


        // JDBC call : 비슷한 예시 >> stored procedure 호출 시 사용할 수 있는 커맨드 인스턴스를 만들 수 있는 편리한 클래스
    }

}

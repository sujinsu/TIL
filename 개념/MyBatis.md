# MyBatis

: java 언어를 위한 SQL 매핑 프레임 워크.

JDBC코드와 매개변수 설정, 결과를  얻기 위한 코드를 제거





## 특징

- 쿼리를 그대로 사용 → 복잡한 쿼리, 최적화된 쿼리 사용, 유연성
- 동적으로 SQL 생성 및 수정
- SQL 쿼리 결과 →  Java 객체로 쉽게 매핑
- 다양한 프레임워크와 통함 가능 (Spring, JEE 등)



### 장점

- 개발자가 SQL 완벽하게 제어 → 성능 최적화 유리
- Vo (resultType, resultClass 등)을 사용하지 않고 조회 결과를 사용자 정의 DTO,MAP 등으로 맵핑하여 사용 가능
- 동적 SQL, 플러그인 등을 통해 다양한 요구 사항 대응



### 단점

- SQL 의존적, 데이터베이스 변경시 관련 SQL 수정해야 함





### 💡 사용 예시


1. 의존성 추가
2. MyBatis 설정 파일 작성
3. SQL Mapper XML 파일 작성
4. Java 모델 클래스 생성
5. Mapper 인터페이스 생성
6. MyBatis 설정 로딩 및 SqlSessionFactory 생성
7. SqlSession을 사용하여 데이터베이스 연결 및 쿼리 실행


- 의존성 추가
    - Maven : pom.xml
    
    ```java
    <dependencies>
        <!-- MyBatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.6</version> <!-- 버전은 적절히 선택 -->
        </dependency>
    </dependencies>
    ```
    
    - Gradle : build.gradle
    
    ```java
    dependencies {
        // MyBatis
        implementation 'org.mybatis:mybatis:3.5.6'  // 버전은 적절히 선택
    }
    ```
    
- MyBatis 설정파일 : (mybatis-config.xml)

```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/mydb"/>
                <property name="username" value="username"/>
                <property name="password" value="password"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper resource="com/example/mapper/UserMapper.xml"/>
    </mappers>
</configuration>
```

- SQL 매퍼 생성 (’UserMapper.xml’)

```jsx
<mapper namespace="com.example.mapper.UserMapper">
    <select id="findUserById" parameterType="int" resultType="com.example.model.User">
        SELECT id, name, email FROM User WHERE id = #{id}
    </select>
</mapper>
```

- Mapper Interface
    - **@Mapper** 있다면, UserMapper.xml 생성 안 해도 됨.
        - `@Param("")`을 통해서 값을 명시하고 `#{}`을 통해 동적 바인딩 처리
        - INSERT 의 경우, Auto 설정으로 증가된 PK 값을 가져오기 위해  `@Options`
        
        ```java
        @Mapper
        public interface UserMapper {
            @Select("SELECT * FROM user WHERE id = #{id}")
            User selectUserById(int id);
        }
        ```
        
    - **@Mapper** 없을 때
      
        ```java
        package com.example.mapper;
        
        import com.example.model.User;
        
        public interface UserMapper {
            User findUserById(int id);
        }
        ```
        
    - 서브쿼리 (@Many)
      
        ```java
        public class Company {
          
            private int id;
            private String name;
            private String address;
            private List<Employee> employeeList;
        }
        ```
        
        ```java
        @Select("SELECT * FROM company")
        @Results(id="CompanyMap", value={
            @Result(property="name", column="company_name"),
            @Result(property="address", column="company_address"),
            @Result(property="employeeList", column="id", many=@Many(select="com.example.demo.EmployeeMapper.getByCompanyId"))
        })
        List<Company> gettAll();
        ```
        

- MyBatis 설정 로딩 및 SqlSessionFactory 생성

```java
String resource = "mybatis-config.xml";
InputStream inputStream = Resources.getResourceAsStream(resource);
SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
```

- MyBatis 사용하여 조회

```java
try(SqlSession session = sqlSessionFactory.openSession()) {
    UserMapper mapper = session.getMapper(UserMapper.class);
    User user = mapper.findUserById(1);
    System.out.println(user.getName());
}
```



### MyBatis3 주요 구성 요소

| 구성 요소 / 구성 파일 | 설명 |
| --- | --- |
| MyBatis configuration file | MyBatis3의 작업 설정을 설명하는 XML 파일
데이터베이스의 연결 대상, 매핑 파일의 경로, MyBatis3의 작업 설정 등과 같은 세부 사항을 설명 
Spring과 통합하여 사용할 때 데이터베이스의 연결 대상과 매핑 파일 경로 설정을 구성 파일에 지정할 필요가 없습니다. 
그러나 MyBatis3의 기본 작업을 변경하거나 확장 할 때 설정이 수행 |
| org.apache.ibatis.session.SqlSessionFactoryBuilder | MyBatis3 구성 파일을 읽고 생성하는 SqlSessionFactory 구성 요소
이 구성 요소는 스프링과 통합되어 사용할 때 애플리케이션 클래스에서 직접 처리하지 않습니다. |
| org.apache.ibatis.session.SqlSessionFactory | SqlSession을 생성하는 구성 요소
이 구성 요소는 스프링과 통합되어 사용할 때 애플리케이션 클래스에서 직접 처리하지 않습니다. |
| org.apache.ibatis.session.SqlSession | SQL 실행 및 트랜잭션 제어를 위한 API를 제공하는 구성 요소
MyBatis3를 사용하여 데이터베이스에 액세스할 때 가장 중요한 역할을 하는 구성 요소
이 구성 요소를 스프링과 통합하여 사용할 경우 애플리케이션 클래스에서 직접 처리하지 않습니다. |
| Mapper interface | typeafe에서 매핑 파일에 정의된 SQL을 호출하는 인터페이스.
MyBatis3는 매퍼 인터페이스에 대한 구현 클래스를 자동으로 생성하므로 개발자는 인터페이스만 생성하면 됩니다. |
| Mapping file | SQL 및 O/R 매핑 설정을 설명하는 XML 파일입니다. |





### MyBatis3 주요 구성 요소 Database Access 순서

![Untitled](MyBatis%20b4655f6eb94e4995b2ac3819bdc1c08c/Untitled.png)



**(1)~(4) :** 응용 프로그램 시작 시 수행되는 프로세스

| 1 | SqlSessionFactoryBean은 SqlSessionFactoryBuilder를 위해 SqlSessionFactory를 빌드하도록 요청합니다. |
| --- | --- |
| 2 | SessionFactoryBuilder는 SqlSessionFactory 생성을 위해 MyBatis 구성 파일을 읽습니다. |
| 3 | SqlSessionFactoryBuilder는 MyBatis 구성 파일의 정의에 따라 SqlSessionFactory를 생성합니다. 따라서 생성된 SqlSessionFactory는 Spring DI 컨테이너에 의해 저장됩니다. |
| 4 | MapperFactoryBean은 안전한 SqlSession(SqlSessionTemplate) 및 스레드 안전 매퍼 개체(Mapper 인터페이스의 프록시 객체)를 생성합니  다. 따라서 생성되는 매퍼 객체는 스프링 DI 컨테이너에 의해 저장되며 서비스 클래스 등에 DI가 적용됩니다. 매퍼 개체는 안전한 SqlSession(SqlSessionTemplate)을 사용하여 스레드 안전 구현을 제공합니다. |



**(5)~(11) :**  클라이언트의 각 요청에 대해 수행되는 프로세스

| 5 | 클라이언트가 응용 프로그램에 대한 프로세스를 요청합니다. |
| --- | --- |
| 6 | 애플리케이션(서비스)은 DI 컨테이너에서 주입한 매퍼 개체(매퍼 인터페이스를 구현하는 프록시 개체)의 방법을 호출합니다. |
| 7 | 매퍼 객체는 호출된 메소드에 해당하는 SqlSession (SqlSessionTemplate ) 메서드를 호출합니다. |
| 8 | SqlSession (SqlSessionTemplate )은 프록시 사용 및 안전한 SqlSession 메서드를 호출합니다. |
| 9 | 프록시 사용 및 스레드 안전 SqlSession은 트랜잭션에 할당된 MyBatis3 표준 SqlSession을 사용합니다. 트랜잭션에 할당된 SqlSession이 존재하지 않는 경우 SqlSessionFactory 메서드를 호출하여 표준 MyBatis3의 SqlSession을 가져옵니다. |
| 10 | SqlSessionFactory는 MyBatis3 표준 SqlSession을 반환합니다. 반환된 MyBatis3 표준 SqlSession이 트랜잭션에 할당되기 때문에 동일한 트랜잭션 내에 있는 경우 새 SqlSession을 생성하지 않고 동일한 SqlSession을 사용합니다.on 메서드를 호출하고 SQL 실행을 요청합니다. |
| 11 | MyBatis3 표준 SqlSession은 매핑 파일에서 실행할 SQL을 가져와 실행합니다. |







자료: https://khj93.tistory.com/entry/MyBatis-MyBatis%EB%9E%80-%EA%B0%9C%EB%85%90-%EB%B0%8F-%ED%95%B5%EC%8B%AC-%EC%A0%95%EB%A6%AC, https://velog.io/@qotndus43/MyBatis-%EB%8F%99%EC%9E%91-%EC%9B%90%EB%A6%AC%EB%A5%BC-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B3%A0-%EC%82%AC%EC%9A%A9%ED%95%B4%EB%B3%B4%EC%9E%90
# JDBC

: (Java Database Connectivity)



자바 언어를 사용하여 데이터베이스에 접근하는 애플리케이션을 개발하기 위한 표준 인터페이스

JDBC API는 SQL 데이터베이스에 대한 접근을 제공하며, 자바 애플리케이션에서 사용 가능

![img](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2F46f00428-ad1d-401d-8dfb-aa4236b52e5d%2FUntitled.png?table=block&id=a1d3fc46-fea8-4f7f-8caf-5ae61277c4e5&spaceId=4a50ccd5-0771-4bf1-ba97-dabe26064358&width=2000&userId=7f7a99a6-ff17-400b-9bfb-4f9bb249d476&cache=v2)









### **특성**

1. **플랫폼 독립성**: JDBC는 자바 기반이므로, 여러 플랫폼에서 동작합니다.
2. **데이터베이스 독립성**: 적절한 드라이버만 있다면 다양한 데이터베이스에 접근할 수 있습니다.
3. **SQL 지원**: 기본적으로 SQL을 사용하여 데이터베이스 연산을 수행합니다.
4. **동적 쿼리 수행**: PreparedStatement를 통해 동적으로 쿼리를 생성하고 실행할 수 있습니다.





### **장점**

1. **표준화**: JDBC는 데이터베이스 접근에 대한 표준 인터페이스를 제공합니다.
2. **유연성**: 다양한 데이터베이스에 쉽게 연결할 수 있습니다.
3. **성능**: 직접 SQL 쿼리를 작성하므로 성능 최적화에 유리할 수 있습니다.





### **단점**

1. **복잡성**: SQL 쿼리를 문자열로 관리해야 하므로, 복잡한 쿼리를 만들거나 수정할 때 관리가 어렵습니다.
2. **에러 처리**: SQL 예외를 잡아서 자바의 예외로 변환해야 하는 작업이 필요합니다.
3. **코드 양**: 데이터베이스 연결 관리, 쿼리 실행, 리소스 해제 등을 직접 코드로 관리해야 하므로 코드가 많아질 수 있습니다.

👉 • **1개의 클래스에  SQL 및 DB 연결, 자바 언어가 모두 존재하기 때문에 재사용성이 좋지 않음.**



### 

### JDBC URL

DBMS 별로 다르다.

구성 : jdbc:[DBMS]:[데이터베이스식별자]



### 흐름

1. JDBC 드라이버 로드
    - MYSQL : com.mysql.jdbc.Driver
    - ORACLE : oracle.jdbc.driver.OracleDriver
    - MSSQL : com.microsoft.sqlserver.jdbc.SQLServerDriver
    - QUBRID : cubrid.jdbc.driver.CUBRIDDriver
2. DB연결
3. DB에 SQL문 작성
4. DB 연결 종료





### **💡 개발 단계**


1. **JDBC Driver loading**
   - 데이터베이스와의 연결을 위해 드라이버 로딩

2. **Connection**

   - URL, 계정 정보 필요

   - DriverManager.getConnection(url,id,pwd) : Connection 연결 메소드

3. **Statement / PreparedStatement**
   - SQL 구문 정의, 쿼리 전송 전 값 setting(변경될 값은 치환)

4. **executeUpdate() or executeQuery()**

   - executeUpdate() : INSERT, DELETE, UPDATE / 반환값 타입 int

   -  executeQuery() : SELECT / 반환값 타입 ResultSet

5. **ResultSet** (SELECT의 경우)

   - 데이터베이스 조회 결과 집합 표준
   -next() : DB table row 1

   - getString(), getInt() : 한 행 특정 Column값

6. **close** (Connection, Statement, ResultSet)



```
💡 java.sql Package


 1) java.sql.Driver

- DB와 연결하는 Driver class를 만들 때 반드시 implements 해야 하는 interface 
- JDBC 드라이버의 중심이 되는 Interface.

2) java.sql.Connection

- 특정 데이터베이스와 연결정보를 가지는 Interface 

- DriverManager 로부터 Connection 객체를 가져옵니다.

3) java.sql.Statement

- SQL query문을 DB에 전송하는 방법을 정의한 Interface 

- Connection을 통해 가져옵니다.

4) java.sql.ResultSet

- SELECT 구문 실행 결과를 조회할 수 있는 방법을 정의한 Interface 

5) java.sql.PreparedStatement

- Statement의 하위 Interface

- SQL문을 미리 컴파일 하여 실행 속도를 높입니다.

6) java.sql.CallableStatement

- PreparedStatement의 하위 Interface

- DBMS의 Stored procedure을 호출
```







- 사용예시 ([https://yoon990.tistory.com/58](https://yoon990.tistory.com/58))

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
 
public class UserDao {
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/testdb?serverTimezone=UTC&useUniCode=yes&characterEncoding=UTF-8";
	private String user = "[username]";
	private String password = "[pwd]";
	
	//회원 전체 조회 
	public ArrayList<User> selectList(){
		//1. JDBC Driver 로딩
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		ArrayList<User> list = null; //결과데이터를 담을 배열
		String sql = "select * from user"; //SQL문
		try(Connection conn = DriverManager.getConnection(url, user, password); //2. DB서버 연결
			Statement stmt = conn.createStatement(); 			//3. SQL 실행 통로 형성
			ResultSet rs = stmt.executeQuery(sql);
			) {
 
			//5. SQL 결과 처리
			list = new ArrayList<>();
			while(rs.next()) {
				String userId = rs.getString("user_id"); // 컬럼명 (*주의 : DTO의 이름과 DB의 컬럼명이 다름 )
//				String userPw = rs.getString("user_pw"); // select inde 번호 : 1번부터
				String userPw = rs.getString(2); // 연산결과인 경우 컬럼명 없음, 별명, 인덱스번호
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String grade = rs.getString("grade");
				int age = rs.getInt("age");
				User dto = new User(userId, userPw, name, phone, grade,age);
 
				list.add(dto);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//회원 상세 조회 
	public User selectUser(String userid){
		//1. JDBC Driver 로딩
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "select * from user where user_id ='"+userid+"'"; //SQL문
		try(Connection conn = DriverManager.getConnection(url, user, password); //2. DB서버 연결
			Statement stmt = conn.createStatement(); 			//3. SQL 실행 통로 형성
			ResultSet rs = stmt.executeQuery(sql);
			) {
			//5. SQL 결과 처리
			while(rs.next()) {
				String userId = rs.getString("user_id");
				//String userPw = rs.getString("user_pw"); 
				String userPw = rs.getString(2); 
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String grade = rs.getString("grade");
				int age = rs.getInt("age");
				
				return new User(userId, userPw, name, phone, grade,age);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; //찾은 유저가 없는 경우 null반환 
	}
}
```









자료: https://yoon-developer.tistory.com/entry/SPRING-MYBATIS-JDBC-%EC%B0%A8%EC%9D%B4, https://shs2810.tistory.com/18, https://yoon990.tistory.com/58
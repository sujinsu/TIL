# Jmeter

### Jmeter 설치

- 최신 버전의 JMeter는 Java 8 버전 이상을 요구
- bin 디렉토리의 실행파일 실행

[Download Apache JMeter](https://jmeter.apache.org/download_jmeter.cgi)

### 목적

기능의 부하테스트 및 측정을 지원하는 Java Application 도구

응답시간 (Response Time), 처리량(Throughput), 병목 구간 등을 확인 가능

### 성능 테스트 유형

- Load Test : 동시 사용자 or 프로세스 수
  - 트랜잭션의 요청으로 인해 현실적인 부하 수준을 처리하는지에 대한 시스템 성능 측정
- Stress Test : 임계값 이상의 요청 or 비정상적인 요청
  - 시스템의 최고 성능 한계를 측정하기 위한 테스트
- Spike Test : 갑자기 사용자가 몰렸을 때 or 업무 부하가 줄어들 때 정상 처리 여부
- Scalability Test / Soak Test
  - 긴 시간 동안 테스트 진행 → 시스템의 메모리 증가, 성능 정보 변화 등을 확인

### 성능 테스트 용어

- Active User

  - 서버에 연결된 상태로 요청 처리 중인 사용자

- Inactive User

  - 웹브라우저에 결과 화면이 출력된 상태에서 화면의 내용을 읽거나 정보 입력 중인 사용자

- Concurrent User

   : Active User + Inactive User

  - 특정 시점에 시스템에 접속하여 사용하고 있는 사용자

- Virtual User

   : 가상 사용자 수

  - Jmeter에서는 Thread 수로 표현하기도 함

- Response Time/Load Time

  - 응답시간 or 처리시간, 요청을 보낸 후 응답이 완료되어 사용자 화면에 출력될 때까지의 시간

- Latency

  - 요청 후 데이터를 받기 시작할 때까지 시간

- Think Time

  - 하나의 요청에 응답을 수신하고 다음 요청을 보낼 때까지 시간

- Request Interval Time

  - 요청을 보낸 후 다음 요청을 보낼 때까지 시간

- Ramp-Up Period

  - Thread 생성에 걸리는 시간

- Transaction

  - 업무 처리의 단위, 화면 조작 및 응답을 트랜잭션으로 정의

- Throughput

  - 단위 시간당 대상 서버에서 처리되는 요청의 수
  - Apache Jmeter에서는 시간 단위를 보통 TPS로 표현
  - TPM(Transaction Per Minute), TPH(Transaction Per Hour) 등이 있음

  

###  💡 주요 개념

- `Thread Group` : 몇 개의 쓰레드가 동시에 요청을 보내는 지
- `Sampler` : 어떤 유저가 해야 하는 액션
- `Listener` : 응답을 받았을 때 어떤 동작을 취하는 지 (검증, 리포트, 그래프 그리기 등)
- `Configuration` : Sampler 또는 Listener가 사용할 설정 값 (쿠키, JDBC 커넥션 등)
- `Assertion` : 응답 결과의 성공 여부를 판단하는 조건 (응답 코드, 본문 내용 등) 



- **Thread Group**

  - *Number of Threads(users) :* single Thread 수 (사용자 수 의미)
  - *Ramp-Up Period(in seconds) :* single Thread 들을 실행시키기 위한 시간 의미
  - *Loop Count* :  Thread 가 행하는 작업의 반복 횟수

  ```
  Name 
  
  : 테스트 이름
  
  Comments 
  
  : 첨부할 설명
  
  Action to be taken after a Sampler error 
  
  : 샘플러가 에러시에 취할 행동
  
  Number of Threads 
  
  : 쓰레드를 동시에 몇개 생성할지. 동시에 몇개의 트랜잭션을 실행시킬지
  이는 사람이 동시에 접속하는 효과를 낸다. 
  10명이서 동시에 접속하는 상황을 만들고 싶다면 10을 사용하면된다.
  
  Ramp-Up Period 
  
  : 쓰레드를 Ramp-Up Period시간동안 실행해라는 의미이다. 
  단 균등하게 시간을 나눠서 실행하려고 노력한다. 
  예를 들어 Nomber of Threads가 10인데 Ramp-Up Period가 60이면 
  10개의 쓰레드가 6초간격으로 동작하려고 한다. 그렇게 안될 수도 있음
  
  Loop Count 
  
  : 스레드의 반복 횟수를 의미한다. 
  10이면 10번 반복한다. Forever에 체크하면 무한 반복한다.
  
  Delay Thread creation until needed 
  
  : 스레드의 생성을 필요할 때까지 기다린다. 
  체크를 해제하면 안기다리고 날리는데 반응성은 더 좋아지긴 하는데 
  안정성을 위해서 체크
  
  Scheduler 
  
  : 위의 모든 작업을 스케줄화 해서 할 수 있다.
  
  Duration 
  
  : Scheduler를 체크했을때만 사용가능. 
  Thread Properties의 총작업을 하는 시간을 의미한다. 
  예를들어 100초를 정하면 위의 작업을 딱 100초동안 실행한다. 
  100초안에 걸리는 작업이면 조기에 정지되지만 
  위의 작업이 100초를 넘어간다면 더이상 실행하지 않고 멈춘다.
  
  Startup delay 
  
  : 위의 작업을 실행하기 위한 유예기간을 의미한다. 
  쓰레드 그룹이 한개일때는 별 필요없지만 쓰레드 그룹을 여러개 돌릴떄는 
  서로 차등을 줄 수 있다.
  ```

- **HTTP Request Defaults**

  - Thread Group → Add → Config Element → HTTP Request Defaults
  - 실제 요청을 보낼 페이지의 기본 정보 설정
  - 구성
    - Protocal[http] : http or https
    - Server Name or IP : Host or IP
    - Port Number
    - Path
    - Parameters

- **HTTP Request**

  - Thread Group → Add → Sampler → HTTP Request
  - HTTP Request Defaults에서 설정한 기본값 외에 요청을 보낼 상세 값들을 설정

- **Listener**

  - Thread Group → Add → Listener → `View Result Tree`
    - 각 Sampler 에 대한 Sampler Result, Request data, Response data 등 확인
    - Text, HTML 등 형태로 확인 가능
  - Thread Group → Add  → Listener → `Summary Report`
    - 실시간 요청 결과에 대한 응답 시간 통계, 에러율, 처리량 등 확인
    - Label : HTTP Request Sampler 에서 설정한 Name
    - \#Samples : 호출 건수
    - Average/Min/Max/Std.Dev : 응답시간 통계 (1/1000초 단위로 표시됨)
    - Error% : 오류율
    - Throughput : 단위 시간당 처리량 (TPS)

### 성능 테스트 자동화

```java
/{path}/apache-jmeter-5.2.1/bin/jmeter -n -t ./test.jmx
```

- path : Apache Jmeter 설치 경로
- 작성한 테스트 계획을 저장 → .jmx 확장자로 저장 → 위의 커맨드로 실행 → 성능 테스트 자동화

# Network

## Ping

```jsx
목적지 서버를 통하는 네트워크 상태 체크

포트를 지정하지 않고 인터넷 연결만 체크
```

ex) ping [목적지 IP주소]





## Telnet

- ping 테스트는 이상 X, BUT 서버 접속은 안될 때

```jsx
목적지 서버의 해당 어플리케이션까지 살아있는지 확인 

특정서버 서비스가 살아있는지 여부 체크

기본적으로 23번 포트

암호화되지 않은 평문 -> 보안 취약점
```

ex) telnet [목적지 IP주소] [어플리케이션 port 정보]

## Traceroute

- ping 테스트 결과가 느리거나 접속이 안될 때, 어떤 라우터에서 문제를 일으키는지 확인

```jsx
출발지와 목적지 사이의 라우터를 모두 추적
```

ex) traceroute [목적지 IP주소]





------

## Netstat

```jsx
-a : 현재다른PC와 연결(Established)되어 있거나 대기(Listening)중인 모든 포트 번호를 확인 

-r : 라우팅 테이블 확인 및 커넥션되어 있는 포트번호를 확인 

-n : 현재 다른PC와 연결되어 있는 포트번호를 확인

-e : 랜카드에서 송수한 패킷의 용량 및 종류를 확인 

-s : IP, ICMP, UDP프로토콜별의 상태 확인

-t : tcp protocol 

-u : udp protocol 

-p : 프로토콜 사용 Process ID 노출

-c : 1초 단위로 보여줌
```

ex) ***netstat [-a] [-l] [-r] [-n] [-c] [-p프로토콜] [-t] [-u]***



![img](https://www.notion.so/image/https%3A%2F%2Fs3-us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fef963f62-2de7-4d4a-924e-8bfff6b6a1bf%2FUntitled.png?table=block&id=725581e0-4e36-44c7-9373-883042c28fa1&spaceId=4a50ccd5-0771-4bf1-ba97-dabe26064358&width=2000&userId=7f7a99a6-ff17-400b-9bfb-4f9bb249d476&cache=v2)

l **프로토콜 :** 사용한 프로토콜

l **로컬 주소 :** 열려 있는 사용자 컴퓨터의 IP/호스트네임과 포트 번호

l **외부 주소 :** 사용자의 컴퓨터에 접속되어 있는 IP/호스트네임과 포트 번호

l **상태 :** 연결 상태

| 상태        | 내용                                                         |
| ----------- | ------------------------------------------------------------ |
| LISTEN      | 연결 요구를 기다리는 상태, 포트가 열려 있음                  |
| ESTABLISHED | 서로 연결되어 있는 상태                                      |
| SVN_SENT    | 클라이언트가 서버에 SYN 패킷을 보내고 연결을 요청한 상태     |
| SVN_RECV    | 서버가 클라이언트의 SYN 패킷으로 요청을 받은 후 응답으로 SYN/ACK 패킷을 보내고 클라이언트에게 ACK 를 받기 위해 기다리는 상태 |
| TIME_WAIT   | 이미 해당 사이트와 연결이 종료되었거나 다음 연결을 위해 기다리는 상태 |
| CLOSE_WAIT  | 원격의 연결 요청을 받고 연결이 종료되길 기다리는 상태        |
| LAST_ACK    | 연결이 종료되었고 승인을 기다리는 상태                       |
| CLOSED      | 완전히 연결이 종료된 상태                                    |

- 특정 포트 검색
  - ***> netstat –an | find “445”***
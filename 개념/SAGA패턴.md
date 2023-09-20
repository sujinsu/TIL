# SAGA 패턴



### 정의

- 마이크로 서비스들끼리 이벤트를 주고 받음
- 특정 마이크로 서비스에서의 작업 실패 -> 이전까지 완료된 마이크로 서비스들에게 보상 이벤트 소싱함 -> 분산 환경에서 원자성 보장
![image](https://github.com/sujinsu/TIL/assets/87465326/9d644f19-5a31-4508-b399-75e5de636f16)
![image](https://github.com/sujinsu/TIL/assets/87465326/8ad86578-3992-4806-80af-0781a9c83049)



## 패턴 종류

### ***\*Choreography based SAGA pattern\****

> 보유한 서비스 내의 Local 트랜잭션 관리. 종료 시 완료 Event 발행 만약 다음 수행할 트랜잭션이 있다면, App으로 이벤트를 보내고, 해당 App은 완료 이벤트를 받고 작업 진행

- 이때 Event 는 Kafka와 같은 메시지 큐를 통해 비동기 방식으로 전달

> 

(+) : 구성 편리

( - ) : 트랜잭션 현재 상태 확인 어려움
![image](https://github.com/sujinsu/TIL/assets/87465326/66d6a56f-72be-4cf3-b06b-c42e2717c679)
![image](https://github.com/sujinsu/TIL/assets/87465326/64521646-fa7d-4380-a371-9b762674bca4)



### ***\*Orchestration based SAGA pattern\****

> Saga 인스턴스(Manager) 가 별도로 존재. 트랜잭션 관여 모든 App은 Manager에 의해 점진적으로 트랜잭션 수행 & 결과 Manager에게 전달 마지막 트랜잭션 종료 후 Manager 종료 → 전체 트랜잭션 종료

if 중간 실패 시 → Manager에서 보상 트랜잭션 발동, 일관성 유지

> 

(+) :  서비스 간 복잡성 감소, 구현 및 테스트 용이, 현재 트랜잭션 상태를 Manager가 알고 있으므로 롤백이 쉬움

( - ) : 관리를 해야하는 Orchestrator 서비스 추가, 인프라 구현 복잡
![image](https://github.com/sujinsu/TIL/assets/87465326/d9813c2d-e7ba-4817-8e23-0d30efd96fc9)
![image](https://github.com/sujinsu/TIL/assets/87465326/12fd2bea-a6a0-4224-8316-75f86fe49669)





자료

:https://azderica.github.io/01-architecture-msa/

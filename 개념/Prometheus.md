

# Prometheus

[[Prometheus 개념\] Jobs, Instances, Group에 대해 알아보기](https://magpienote.tistory.com/262)

[Monitoring Linux host metrics with the Node Exporter | Prometheus](https://prometheus.io/docs/guides/node-exporter/)

![image](https://github.com/sujinsu/TIL/assets/87465326/8fd9eb72-f928-40f4-a58a-f83a334bef8b)






## Prometheus
![image](https://github.com/sujinsu/TIL/assets/87465326/3f6e2d83-4dbf-43e0-a26b-56023107f223)

- 서버에 클라이언트가 떠 있으면 서버가 주기적으로 클라이언트에 접속해서 데이터를 가져오는 방식

```
💡 구성

- Exporter :  모니터링 대상의 Metric 데이터를 수집하고, Prometheus가 접속했을 때 정보를 알려주는 역할
  - Exporter 실행 → 데이터 수집 & HTTP 엔드포인트 오픈(기본 9100포트) → 서버가 데이터 수집 가능 → 해당 HTTP 엔드포인트 접속 시 Metric 내용 확인 가능
- Prometheus server : Exporter가 열어놓은 HTTP 엔드퐁니트 접속 & Metric 수집 (pull 방식)
- Grafana : Prometheus server와 연동해서 대시보드 등 시각화
- Alertmanager : 알람 받을 규칙 생성 & Alert Manager 로 보내면, Alert Manager가 규칙에 따라 알림을 보낸다.
```







### Lable

> key-value 쌍으로 구성, 메트릭 식별 및 구분에 사용 목적 : 메트릭 필터링, 분류, 검색

- 사용예시

```java
http_requests_total{job="example-job", instance="example-instance1:9090", method="GET", status="200"}
```

- 비슷한 메트릭 예시

```java
// 프로세스에 의해 사용된 총 CPU 시간(초 단위).
process_cpu_seconds_total{job="example-job", instance="example-instance:9090"}

// 열린 파일 디스크립터의 수.
process_open_fds{job="example-job", instance="example-instance:9090"}

// 사용 가능한 메모리 양(바이트 단위)
node_memory_MemAvailable_bytes{job="node", instance="example-node:9100"}

// 네트워크 인터페이스에서 수신한 총 바이트 수.
node_network_receive_bytes_total{job="node", instance="example-node:9100", device="eth0"}
```





### Job

> Job은 Prometheus에서 같은 유형의 타겟을 그룹화하는 데 사용됩니다. 예를 들어, 여러 개의 인스턴스로 구성된 서비스가 있을 때, 이러한 인스턴스들은 동일한 job 아래에 그룹화

```java
scrape_configs:
  - job_name: 'example-job'
    static_configs:
      - targets: ['example-instance1:9090', 'example-instance2:9090']
```





### Group

1. **사용자 정의 레이블로서의 Group**

```java
# prometheus.yml
<...>

scrape_configs:
  - job_name: 'node' # Job 이름
    scrape_interval: 5s # Scape 주기 설정

    static_configs:
      - targets: ['10.1.1.1:3001'] # Instance 1
        labels:
          group: 'production' # group 1

      - targets: ['10.1.1.2:3002', '10.1.1.3:3002'] # Instance 2, 3
        labels:
          group: 'develop' # group 2
```

2. 알림 규칙 그룹화

```java
groups:
  - name: example-alert-group
    rules:
    - alert: HighCPUUsage
      expr: node_cpu_seconds_total{mode="system"} > 0
      for: 5m
```

3. 서비스 디스커버리 그룹화 (ex. kubernetes_sd_configs)

```java
scrape_configs:
  - job_name: 'kubernetes-nodes'
    kubernetes_sd_configs:
    - role: node
      namespaces:
        names:
        - default
        - monitoring
```

[Exporters and integrations | Prometheus](https://prometheus.io/docs/instrumenting/exporters/#software-exposing-prometheus-metrics)







## Prometheus Exporter

> Exporter : 특정 소프트웨어, 하드웨어 or 프로토콜로부터 메트릭 수집 Prometheus 에서 스크랩할 수 있는 형식으로 변환

prometheus.yaml 에 exporter 타켓 추가

- Node Exporter : https://prometheus.io/docs/guides/node-exporter/

```java
scrape_configs:
  - job_name: 'node'
    static_configs:
      - targets: ['localhost:9100']
```

- Mysql Exporter

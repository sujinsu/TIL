# CoreDNS & ConfigMap



## CoreDNS

> Kubernetes 클러스터 도메인 이름 시스템 기능을 제공하는 오픈 소스 DNS 
> DNS 사용 : 클러스터 내부의 네트워크 통신 및 서비스 검색



Kubenetes 클러스터 설치 시 기본적으로 구성 

kube-system 네임 스페이스 에서 실행 

DNS 조회를 위해 클러스터 내부 ‘*.svc.cluster.local’ 도메인 사용 가능



- 특징

  - 가볍고 유연한 아키텍처

  - 클러스터 Pod IP 주소와 서비스 이름 매핑 → DNS 조회 처리

    👉 Pod 및 서비스 간의 통신을 DNS 이름으로 수행

  - 클러스터 보안 강화 : 네트워크 정책에 따라 허용되지 않은 통신을 차단하거나 제한 가능

  - 서비스 디스커버리 지원

    - 서비스 이름을 사용하여 Pod 의 IP 주소 검색 가능





## configMap

> Kubernetes 에서 설정 데이터를 저장하고 관리하기 위한 리소스 
>
> 키 - 값 쌍으로 구성된 데이터 포함 
>
> 애플리케이션 설정, 환경 변수, 구성 파일 등을 저장 가능 
>
> 👉 애플리케이션의 설정 값을 컨테이너에 주입하고 싶을 때 사용하는 리소스



- 특징
  - YAML 또는 JSON 형식으로 정의
  - 클러스터에서 관리되는 중앙 집중식 구성 데이터 소스 사용
  - 애플리케이션 구성 변경 시 재배포 없이 ConfigMap 업데이트
- 생성 방법
  1. configMap 리소스 작성
     - YAML 또는 JSON 파일로 configMap 리소스 정의 → kubectl apply
  2. 커맨드 라인에서 직접 생성
     - kubectl create configmap
  3. 환경 변수 파일로부터 생성
     - kubectl create configmap —from-env-file 플래그 사용
  4. 디렉토리로부터 생성
     - kubectl create configmap —from-file 플래그 사용





- 활용 방법

https://velog.io/@pinion7/Kubernetes-%EB%A6%AC%EC%86%8C%EC%8A%A4-ConfigMap%EC%97%90-%EB%8C%80%ED%95%B4-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B3%A0-%EC%8B%A4%EC%8A%B5%ED%95%B4%EB%B3%B4%EA%B8%B0

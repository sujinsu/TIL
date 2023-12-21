# 쿠버네티스

## 쿠버네티스 (k8s)

- 컨테이너화된 애플리케이션의 배포, 확장 및 관리를 자동화하는 오픈 소스 오케스트레이션 시스템

- ```
  kubectl
  ```

  : 쿠버네티스 클러스터 제어 명령줄 도구

  - 클러스터 내  리소스 관리 : 파드, 서비스, 볼륨 등 쿠버네티스 리소스 생성, 업데이트, 삭제
  - 상태 확인 : 클러스터 상태 조회, 애플리케이션 상태 모니터링
  - 디버깅, 트러블 슈팅 : 로그 확인, 컨테이너 접속 문제 진단
  - config 파일 관리 : yaml, json 형식 파일 사용 리소스 정의 및 관리

### 클러스터와 노드

- 클러스터
  - 쿠버네티스 핵심 구조
  - 여러 노드를 포함하는 컴퓨터 그룹
  - 워크로드 분산, 고가용성 확장성 제공
- 노드
  - 개별 컴퓨터 (물리적 또는 가상)
  - 파드를 실행하는데 필요한 리소스(CPU,메모리 등) 제공

### 접근방식

→ **클러스터**

1. kubectl : 쿠버네티스 클러스터 관리 명령줄 도구 파드, 서비스, 볼륨, 노드 등 다양한 쿠버네티스 리소스 생성, 수정, 삭제

   **`kubectl`**은 쿠버네티스 클러스터와 그 리소스를 관리하기 위한 도구입니다. 주로 파드, 서비스, 데플로이먼트 같은 쿠버네티스 리소스와 상호 작용하는 데 사용됩니다.

   - **kubectl get pod**: 클러스터 내의 파드 목록을 조회합니다.
   - **kubectl exec**: 클러스터 내 특정 파드의 컨테이너에서 명령을 실행합니다. 예를 들어, 파드 내부의 쉘에 접근하는 데 사용할 수 있습니다.

   이러한 **`kubectl`** 명령은 쿠버네티스 API를 통해 수행되며, 클러스터 내의 파드 또는 다른 쿠버네티스 오브젝트와 직접 상호 작용합니다.

2. 쿠버네티스 대시보드 : 웹기반 UI : 클러스터와 클러스터 내 리소스 상태 시각적 모니터링 및 관리

3. API 서버 : 애플리케이션 배포, 스케일링, 업데이트 등 수행

4. 클라우드 기반 클러스터의 경우, 클라우드 제공 업체의 관리 콘솔을 통해 설정 및 관리

: 일반적인 클러스터 관리 작업(예: 파드 관리, 네트워크 정책 설정 등)은 노드 수준이 아닌, 클러스터 수준에서 이루어집니다.

→ **노드**

: SSH 접근 - 개별 노드에 대한 저수준 접근이 필요할 때

- SSH : 원격 시스템에 안전하게 접근하기 위한 프로토콜

```yaml
1. SSH 클라이언트 준비
ex) window의 경우 PuTTY , powershell, wsl

2. SSH 접속 정보 확인
원격으로 접속하고자 하는 서버의 IP 주소 or host명, 사용자 계정, (선택) 포트번호 확인
ex) 192.168.1.10 user

3. SSH 접속 
터미널 또는 명령 프롬프트를 열고 명령어 입력

-> ssh user@192.168.1.10 

4. 보안경고 (* 처음 접속 시)
yes 를 입력하여 계속 진행

5. 비밀번호
서버에 설정된 사용자 계정 pw
인증 성공시, 원격 서버 쉘 프롬프트 접근

6. 원격 서버 작업 수행
: ssh 세션을 통해 원격 서버에서 작업 수행

7. 세션 종료
exit

* 안전한 pw와 ssh 키 기반 인증 방식
* 서버의 네트워크 설정, 방화벽, SSH 서비스 상태 구성 확인
```

이를 통해 특정 노드의 운영 체제 수준에서 작업을 수행할 수 있습니다.

- **노드 관리**: 파일 시스템 접근, 로그 파일 확인, 시스템 프로세스 관리 등을 할 수 있습니다.
- **디버깅 및 트러블슈팅**: 노드 수준에서 문제를 진단하고 해결하는 데 사용됩니다.

SSH 접근은 노드 수준의 저수준 작업을 위한 것이며, 쿠버네티스 클러스터의 리소스 관리와는 다른 수준의 작업입니다.

## 쿠버네티스 리소스

- **pod**

  - 기본적인 배포 단위.
  - 하나 이상의 컨테이너 포함

- **Service**

  - Pod집합에 대한 안정적인 네트워크 인터페이스 제공

  - **목적** : 서비스(Service)는 쿠버네티스 내에서 실행되고 있는 하나 이상의 Pod 집합에 대한 액세스 포인트를 제공합니다. 서비스는 Pod들이 다운되거나 새로 생성될 때 변동 가능한 내부 IP 주소 대신에, 안정적인 인터페이스와 주소를 제공합니다.

  - **기능** : 서비스는 특정 Pod 집합을 논리적으로 그룹화하고, 이들에 대한 네트워크 트래픽을 관리합니다. 서비스는 셀렉터를 사용하여 관리할 Pod들을 식별합니다.

    ```yaml
    apiVersion: v1
    kind: Service
    metadata:
      name: my-service
    spec:
      selector:
        app: my-app
      ports:
        - protocol: TCP
          port: 80
          targetPort: 9376
    
    # 이 예시에서, my-service라는 이름의 서비스는 
    # app: my-app 라벨을 가진 Pod들을 타겟으로 하며, 
    # 포트 80에서 들어오는 트래픽을 Pod의 9376 포트로 전달합니다.
    # → Service : ingress로 인해 라우팅된 트래픽을 받아, 트래픽 처리할 Pod에게 전달
    ```

- **Deployment**

  - Pod의 선언적 업데이트 및 스케일링 관리

    ```yaml
    apiVersion: apps/v1
    kind: Deployment
    metadata:
      name: my-app-deployment
    spec:
      replicas: 3
      selector:
        matchLabels:
          app: my-app
      template:
        metadata:
          labels:
            app: my-app
        spec:
          containers:
          - name: my-app
            image: my-app:1.0
            ports:
            - containerPort: 9376
    
    # 여기서 my-app-deployment는 3개의 레플리카를 가진 웹 애플리케이션입니다. 
    # 이 Deployment는 service.yaml에서 정의된 my-service에 의해 노출됩니다. 
    # ingress.yaml의 example-ingress는 외부 요청을 my-service를 통해 my-app-deployment의 Pod들로 라우팅합니다.
    ```

- **Ingress**

  - 클러스터 외부에서 내부 서비스로 HTTP/HTTPS 트래픽 라우팅

  - **목적** : 인그레스(Ingress)는 클러스터 외부에서 쿠버네티스 내의 서비스로 HTTP/HTTPS 요청을 라우팅합니다. 이는 외부 트래픽을 관리하고, URL 기반의 라우팅을 설정하는 데 사용됩니다.

  - **기능** : 인그레스는 도메인 이름, URL 경로 등에 따라 들어오는 트래픽을 쿠버네티스 클러스터 내의 다양한 서비스로 라우팅합니다. SSL/TLS 인증, 호스트 기반 라우팅, 경로 기반 라우팅 등의 기능을 제공합니다.

    ```yaml
    apiVersion: networking.k8s.io/v1
    kind: Ingress
    metadata:
      name: example-ingress
    spec:
      rules:
      - host: www.example.com
        http:
          paths:
          - path: /testpath
            pathType: Prefix
            backend:
              service:
                name: test
                port:
                  number: 80
    
    # 이 예시에서, example-ingress라는 인그레스는 
    # www.example.com/testpath로 들어오는 모든 요청을 test 서비스의 80 포트로 라우팅합니다.
    # → Ingress : 클러스터 외부에서 들어오는 트래픽을 클러스터 내의 서비스로 라우팅
    ```

- **ConfigMap**

  - 구성데이터를 key-value 쌍으로 저장

  - 애플리케이션 설정 관리

    ```yaml
    apiVersion: v1
    kind: ConfigMap
    metadata:
      name: my-config
    data:
      config.json: |
        {
          "key": "value",
          "databaseUri": "jdbc:mysql://example.com:3306",
    			"logLevel": "INFO"
        }
    ```

    ```yaml
    apiVersion: v1
    kind: ConfigMap
    metadata:
      name: my-config
    data:
      databaseUri: "jdbc:mysql://example.com:3306"
      databaseUser: "user"
    ```

  - 활용 예제 (deployment,yaml)

    - 환경변수

      ```yaml
      apiVersion: apps/v1
      kind: Deployment
      metadata:
        name: my-app
      spec:
        replicas: 1
        template:
          metadata:
            labels:
              app: my-app
          spec:
            containers:
            - name: my-app
              image: my-app:1.0
              env:
              - name: DATABASE_URI
                valueFrom:
                  configMapKeyRef:
                    name: my-config
                    key: databaseUri
              - name: DATABASE_USER
                valueFrom:
                  configMapKeyRef:
                    name: my-config
                    key: databaseUser
      ```

    - 볼륨

      ```yaml
      apiVersion: apps/v1
      kind: Deployment
      metadata:
        name: my-app
      spec:
        replicas: 1
        selector:
          matchLabels:
            app: my-app
        template:
          metadata:
            labels:
              app: my-app
          spec:
            containers:
            - name: my-container
              image: my-app:1.0
              volumeMounts:
              - name: config-volume
                mountPath: /etc/config
            volumes:
            - name: config-volume
              configMap:
                name: my-config
      ```

- **Secret**

  - 민감한 정보(ex. password, token) 저장

    ```yaml
    apiVersion: v1
    kind: Secret
    metadata:
      name: my-secret
    type: Opaque
    data:
      password: cGFzc3dvcmQ= # Base64 인코딩된 'password'
    ```

  - 활용 예제

    ```yaml
    apiVersion: apps/v1
    kind: Deployment
    metadata:
      name: my-app
    spec:
      replicas: 1
      template:
        metadata:
          labels:
            app: my-app
        spec:
          containers:
          - name: my-app
            image: my-app:1.0
            env:
            - name: DATABASE_PASSWORD
              valueFrom:
                secretKeyRef:
                  name: my-secret
                  key: databasePassword
    ```

- **PersistentVolume**

  - 스토리지 리소스 추상화

  - 데이터 영구적 저장

    ```yaml
    apiVersion: v1
    kind: PersistentVolume
    metadata:
      name: my-pv
    spec:
      capacity:
        storage: 10Gi
      accessModes:
        - ReadWriteOnce
      hostPath:
        path: "/mnt/data"
    
    # my-pv라는 이름의 PV가 정의되었습니다. 
    # 이 PV는 10Gi의 저장 용량을 제공하고, ReadWriteOnce 액세스 모드를 지원합니다. 
    # 물리적으로는 호스트 시스템의 /mnt/data 경로에 저장 공간이 할당됩니다.
    ```

- **PersistentVolumeClaim**

  - PersistentVolume에 대한 요청으로 스토리지 리소스 사용 캡슐화

  - PVC는 사용자가 필요한 스토리지의 양과 액세스 모드를 요청하는 방법

    - pvc를 만들지 않으면 pod는 해당 pv를 사용할 수 없음

    ```yaml
    apiVersion: v1
    kind: PersistentVolumeClaim
    metadata:
      name: my-pvc
    spec:
      accessModes:
        - ReadWriteOnce
      resources:
        requests:
          storage: 5Gi
    
    # my-pvc라는 이름의 PVC가 정의되었습니다. 
    # 이 PVC는 5Gi의 저장 용량을 요청하며, ReadWriteOnce 액세스 모드를 요구합니다.
    ```

  - 쿠버네티스는 PVC가 요구하는 조건을 만족하는 PV를 찾아 자동으로 연결합니다. 이 경우 **`my-pvc`** PVC는 **`my-pv`** PV의 조건(저장 용량과 액세스 모드)에 부합하므로, 쿠버네티스는 **`my-pvc`**를 **`my-pv`**에 바인딩합니다.

- **StatefulSet**

  - 상태를 보유하는 Pod 집합을 관리

  - 순서 보장 및 지속성 제공

    ```yaml
    apiVersion: apps/v1
    kind: StatefulSet
    metadata:
      name: my-statefulset
    spec:
      serviceName: "my-service"
      replicas: 3
      selector:
        matchLabels:
          app: my-app
      template:
        metadata:
          labels:
            app: my-app
        spec:
          containers:
          - name: my-app
            image: my-app:latest
            ports:
            - containerPort: 80
    ```

- **DaemonSet**

  - 각 노드에 Pod의 사본을 실행하도록 보장

    ```yaml
    apiVersion: apps/v1
    kind: DaemonSet
    metadata:
      name: my-daemonset
    spec:
      selector:
        matchLabels:
          name: my-daemon
      template:
        metadata:
          labels:
            name: my-daemon
        spec:
          containers:
          - name: my-daemon
            image: my-daemon:latest
    
    # name: my-daemon 레이블을 가진 파드가 대상이 됩니다.
    # 클러스터의 모든 노드에 특정 파트를 배포하는 예제
    # 자동 확장
    # 노드별 서비스 : 로깅, 모니터링, 보안 등 노드 수준에서 필요한 서비스 제공
    # 클러스터의 각 노드에서 실행되어야하는 백그라운드 프로세서에 적합
    ```

→ configmap과 secret 사용예제 : 주로 pod, deployment

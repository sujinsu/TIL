# Harbor


## 주요 특징

1. 보안
    1. Vulnerability : 이미지 스캔하여 취약점 식별
    2. 콘텐츠 신뢰 : 이미지 섬여 → 콘텐츠 무결성 및 출처 보장
    
    -> 기업 사용 용이 (private docker registry)
    
2. 멀티 테넌시 : 다양한 프로젝트 및 사용자 그룹을 위한 RBAC(Role-Based Access Control) 지원

3. 복제 : 하나 이상의 Harbor 인스턴스 간 이미지 복제

4. Extensibility : 확장 가능한 API → 다른 서비스와 통합 가능

5. 통합 Garbage Collection : 사용하지 않는 이미지 레이어 자동 정리, 저장 공간 절약

6. Cloud-Native 기반 오픈 소스 

7. Web UI 제공

8. Docker Image 개인 저장소 제공
    1. docker registry 는 1개까지만 무료
    2. harbor는 추가 요금 없이 다수의 registry 생성 가능





### 장점

- 풍부한 보안 기능 (← 이미지 보안 취약점)
- 멀티 클라우드 환경 지원 : 여러 클라우드 환경에서의 이미지 배포와 복제 지원
- 광범위한 통합
- 커뮤니티 지원 ( ← 오픈소스)





### 단점

- 설치 및 설정

- 커뮤니티 버전, 엔터프라이즈 버전 차이



### 비슷한 도구

> 이미지 레지스트리

- Docker Registry

- [Quay.io](http://Quay.io) : Red hat
- Amazon ECR (Elastic Container Registry)
- Google Container Registry : Google Cloud
- Azure Container Registry : Azure





## 💡 사용법

- **로그인**

```jsx
$ sudo docker login https://[DOMAIN NAME]:[PORT]
```

```jsx
$ sudo docker login https://security.test.com:5000
Username: admin
Password:
　
Login Succeeded
```

- **이미지 업로드**
    
    - `docker tag` : 업로드 이미지에 저장소 주소와 프로젝트 이름 명시
    
    ```jsx
    $ sudo docker tag SOURCE_IMAGE[:TAG] [MyDomain.com]:[PORT]/[PROJECT]/IMAGE[:TAG]
    ```
    
    - `$ sudo docker tag golang security.test.com:5000/test/golang:0.1`
    
    ```jsx
    $ sudo docker push [MyDomain.com]:[PORT]/[PROJECT]/IMAGE[:TAG]
    ```
    
    - `$ sudo docker push security.test.com:5000/test/golang:0.1`
    
- **이미지 삭제**

```jsx
$ sudo docker rmi security.test.com:5000/test/golang:0.1
```

- 이미지 pull

```jsx
$ sudo docker pull [MyDomain.com]:[PORT]/[PROJECT]/IMAGE[:TAG]
```





자료

: https://velog.io/@tkfrn4799/harbor-private-docker-registry

: https://engineering.linecorp.com/ko/blog/harbor-for-private-docker-registry


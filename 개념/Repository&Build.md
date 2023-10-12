# Repository/Build



## Nexus Repository 란?

- Sonatype 에서 만든 저장소 관리자 프로젝트
- **Maven** 에서 사용할 수 있는 가장 널리 사용되는 무료 Repository
- 외부에서 Dependency 수고를 덜고 local nexus (cache)로 사용

→ 빠르게 라이브러리 끌어옴, 개발팀 공용 라이브러리 local nexus 에 배포하여 공유 가능

```
💡 Snapshot vs Release

- Snapshot 
  아직은 안정화 X 
  데일리 빌드 버전, 최신버전
  
- GA (General Availability) 
  정식 릴리즈 버전 상업성 개발시는 안전하게 릴리즈 버전 사용 
  메이븐 repo 에는 GA만 maven 중앙 저장소에 올라가고, 
  RC, M 등은 올라가지 않음

- M (Milestone) 
  팀이나 프로젝트마다 주기마다 배포하는 버전 
  주요 기능 구현마다 릴리즈하고 개발자들에게 피드백 받는 버전
  
- RC (Release Candidate) 
  milestone 에서 정리를 더 해서 내놓는 버전 
  정식 릴리즈 버전은 아니라 안정적 동작을 보장하진 않음

```



- SpringBoot 버전주기
  - SNAPSHOT -> M -> RC -> RELEASE (GA) -> SNAPSHOT (Next Version)





## 사설 Repo 필요성

- WhiteList 로 인해 외부 Repo 접속이 어려운 경우 Proxy 역할
- 비상시 빠르게 사용 가능
- 현재 Maven에 없는 자료 효율적으로 관리
- 한번 다운 받은 dependency 는 로컬 저장 → 협업시 다른 PC에 설치
- 서버 동일 설정
- 예외 파일로 인한 설정이 줄어 전체적인 일관성 증가
- 개발팀 공통 라이브러리 공유
- 특정 솔루션 3rd Party 라이브러리 관리





## 설치

1. 공식 문서의 압축 파일 통해 설치 및 실행

[Repository Manager 3](https://help.sonatype.com/repomanager3)

1. Docker 이미지로 설치 및 실행

[Sonatype nexus 로 docker registry 관리하기](https://www.slideshare.net/ssuser800974/sonatype-nexus-docker-registry)

- nexus 로 docker registry 관리하기







------

# 빌드 관리 도구

- 필요한 라이브러리들을 자동으로 관리
  1. 종속성 다운로드
  2. 소스코드 → 바이너리코드 (컴파일)
  3. 바이너리 코드 패키징
  4. 테스트 실행
  5. 프로덕션 시스템에 배포

<aside> 
    
</aside>

```
💡 빌드란?

소스코드 파일을 컴파일하여 실행할 수 있는 가공물로 변환하는 과정 or 결과물 소스코드(java), 프로젝트에 쓰인 각각의 파일 및 자원 등(xml, jpa, jpg,properties) 을 jvm이나 톰캣같은  WAS가 인식할 수 있는 패키징하는 과정 및 결과물
```







## Maven

Java 프로젝트 빌드를 자동화 해주는 Build Tool

- 자바 전용 프로젝트 Lifecycle 관리 목적 빌드 도구

```java
Build
: 소스코드 파일을 소프트웨어 가공물로 변환하는 과정 또는 그에 대한 결과물

Build Tool
: 프로젝트 생성, 테스트 빌드, 배포 등의 작업을 위한 전용 프로그램

- 라이브러리 버전 동기화 어려움 해소를 위해 등장
```

- 의존 라이브러리 추가
  - 중앙 원격 저장소에서 라이브러리를 쉽게 받을 수 있다

### POM (Project Object Model)

- 프로젝트 정보 : 이름, 개발자 목록, 라이센스 등
- 빌드 설정 : 소스, 리소스, 라이프 사이클별 실행한 플러그인(goal) 등 빌드 관련 설정
- 빌드 환경 : 사용자 환경 별로 달라질 수 있는 프로파일 정보
- POM 연관 정보 : 의존 프로젝트(모듈), 상위 프로젝트, 포함하고 있는 하위 모듈 등







## Gradle

- 빌드 배포 도구 (Build Tool), Java, C/C, Python 지원
- 프로젝트 구성/관리, 테스트, 배포 도구
- 안드로이드 앱의 공식 빌드 시스템
- 별도의 빌드스크립트를 통해 사용할 어플리케이션 버전, 라이브러리 항목 설정
  - ➕ 스크립트 언어 → xml 과 같이 변수선언, if else for 등의 로직 구현 가능, 간결 구성 가능
- 라이브러리 관리
  - Maven Repository 동일하게 사용 가능
  - 설정된 서버를 통해 라이브러리 다운로드 → 동일한 의존성을 가진 환경 구성
  - 자신이 추가한 라이브러리 레파지토리 서버에 올릴 수 있음
- 프로젝트 관리
  - 일관된 디렉토리 구조 → 빌드 프로세스 유지
- 단위 테스트 의존성 관리
  - junit 등 사용을 위해 명시

```java
repositories{
mavenCentral()
    jcenter()
}
```

```
💡 mavenCentral() vs jcenter()

repositories는 각종 의존성 (library) 들을 어떤 원격 저장소에서 받을지 결정

mavenCentral는 많이 사용 
BUT 본인이 만든 라이브러리 업로드 과정과 설치 어려움

→ 이를 보완하여 나온 jcenter

jcenter 에 라이브러리를 업로드하면 mavenCentral에도 업로드되도록 자동화 
최대 java 저장소
```

 

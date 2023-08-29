# ArgoCD





: GitOps 접근 방식의 장점 극대화, 현대적인 쿠버네티스 환경에서의 애플리케이션 배포 간소화, 안정화

![Untitled](ArgoCD%20c0e9d4654b5e4ae1b7a832ee69794a2f/Untitled.png)



```
💡 GitOps


: 운영을 위해 Git을 사용하는 접근 방식. Git Repo 가 시스템의 상태를 명확하게 정의
변경사항 Git repo 통합 시 운영이 원하는 상태와 동기화

1. 원하는 상태 정의

-  Kubernetes 애플리케이션 배포 설정 YAML 파일 작성
   ex) app-deployment.yaml

2. 변경 사항

- yaml 파일 수정하여 새 버전 이미지 사용하도록 업데이트
- 새로운 브랜치 체크아웃, 커밋

3. MR

4. 병합

- GitOps 도구가 변경 사항 감지, 쿠버네티스 클러스터와 동기화

5. 모니터링 및 롤백

(+) 
: 버전 제어 (변경 기록 추적 및 롤백 가능)
: 자동화
: 보안 및 준수**
```



****





### 특징

- Kubernetes 매니페스트, Helm 차트, Kustomize 및 Ksonnet 지원
- Git 리포지토리 연동
- 자동 동기화 : Git 리포지토리와 실제 클러스터 상태 차이 자동 탐지 및 동기화
- 다중 클러스터 배포 : 여러 Kubernetes 클러스터로 애플리케이션 배포하는 것을 지원
- 웹 UI 제공 : 현재 상태, 이력, 롤백 등 작업 시각적 관리
- RBAC : 사용자 및 팀별로 리소스 접근 권한 세부적 관리
- Webhook & API : 자동화 및 통합을 위한 웹훅과 API 지원





### 장점

- GitOps 방식 채택 : Git으로 관리, 추적, 롤백 및 협업 용리
- 다양한 설정 형식 지원 : Helm, Kustomize 등 여러 설정 형식 지원
- 보안 : SSO, RBAC 및 OIDC를 통한 인증을 지원하여 보안 강화 가능
- 통합 및 확장성 : 다양한 플러그인, 확장 기능 지원, 타 시스템과 통합 용이





### 단점

- GitOps, ArgoCD 원리 및 설정을 처음 익히는 데 시간 소요
- 세부 설정의 복잡성 : 복잡한 애플리케이션과 워크플로우 구성 시





### 비슷한 도구

- Flux : CNCF 의 GitOps 도구
- Jenkins X : Jenkins의 클라우드 네이티브 버전
- Spinnaker : Netflix 에서 개발한 다중 클라우드 배포 플랫폼







자료: https://wlsdn3004.tistory.com/37
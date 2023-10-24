# TIL

**[AL1] 기본 자료구조 이해**



**1. 문제 해결 전략**

   - 문제를 작은 단위로 분할

   - 시간 복잡도 Bog-O(빅-오) 표기법 : 최악의 경우


**2. 스택 (FILO)**

   - Array, List

   - 하나의 입출력 장소, 제한적인 데이터 접근 -> 데이터 안전성, 무결성 보장
   - ex) 웹브라우저 방문 기록, 괄호 등
   - 삽입(push)
   - 삭제(pop)
   - 읽기(peek or top)
   - isEmpty


**3. 큐 (FIFO)**

   - Array, List (LinkedList)

   - 삽입(Enqueue) : O(1)
   - 삭제(Dequeue) : O(1)
   - 읽기(Peek) : O(1)
   - isEmpty


**4. 재귀**
   - 자기자신을 호출
     - 기본 조건(base case) : 재귀 호출 중단 조건
     - 재귀 호출(recursive call) : 더 작은 하위 문제로 분해하여 해결
     - ex) 팩토리얼 구현, 피보나치 수열, 분할 정복



**[AL2] 그래프와 트리**

**1. 트리**
   - (+) : 효과적인 계층 구조 표현, 정렬과 탐색에 활용, 삽입 삭제 용이
   - 종류 
     - 일반 트리 
     - 이진 트리(정, 포화, 완전)
     - 이진 탐색 트리(오름차순 정렬)
       - 왼쪽 자식은 부모 노드보다 작고, 오른쪽 자식은 부모 노드보다 큰 값
     - 균형 잡힌 트리
     - 힙트리 (우선순위에 따라 빠르게 검색 ex. 최소힙, 최대힙)
       - 느슨한 정렬 구조 (자식 노드끼리 좌우 위치 정렬 X)
   - 배열 
     -  (+) : 단순성, 탐색 속도, 메모리 관리(포인터 관리X) 
     - ( - ) : 고정 크기, 노드 추가 제거 비효율성
     - 왼쪽 자식 : 부모인덱스 * 2
     - 오른쪽 자식 : 부모인덱스 * 2 + 1
   - 연결리스트 : 노드 = 데이터 요소 + 자식 노드를 가리키는 포인터나 객체
     - (+) : 동적 크기, 추가 제거 용이, 공간 효율성 (각 노드만큼의 공간만 필요), 구조적 유연성
     - ( - ) : 포인터 관리(추가적인 메모리 필요), 탐색 시간(특정 노드 탐색을 위해 루트 노드부터 시작)
   
**2. 그래프**
   - 무방향, 방향, 완전, 부분, 가중, 유향 비순환, 연결, 단절
   
   - 표현 방법 
   
     - 인접 행렬 : 최단 경로 문제
   
       - ```java
         int[][] matrix = new int[][]{ // Java 인접행렬 표현
                 {0, 0, 1}, // A정점에서 이동 가능한 정점을 나타내는 행
                 {1, 0, 1}, // B정점에서 이동 가능한 정점을 나타내는 행
                 {1, 0, 0}  // C정점에서 이동 가능한 정점을 나타내는 행
             };
         ```
   
     - 인접 리스트 : 메모리 사용 최적화
   
       - ```java
         ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
                 graph.add(new ArrayList<>(Arrays.asList(2, null)));
                 graph.add(new ArrayList<>(Arrays.asList(0, 2, null)));
                 graph.add(new ArrayList<>(Arrays.asList(0, null)));
         ```
   
         
   
   - 연산 : 정점 추가, 간석 추가, 정점 제거, 간선 제거, 정점 탐색, 간선 탐색, 인접 정점 탐색, 그래프 순회
   
**3. DFS (깊이우선탐색)**
   - 한 분기 탐색한 후, 다음 분기 이전 해당 분기를 완벽하게 검색. 해당 분기 완벽하게 탐색 후 다음 분기로 이동. 모든 정점 탐색 후 종료
   - 활용 경우 : 경로의 특징을 저장해둬야 하는 문제, 검색 대상 그래프가 큰 경우
   - Stack 자료구조나 재귀를 통해 구현
     - 시작 정점을 인자로 받는 DFS 함수
     - 탈출 코드 , 종료조건 체크
     - 현재 정점 방문 처리
     - 인접 정점 확인
     - 인접 정점 중 방문하지 않은 정점 재귀적으로 DFS 호출
   
**4. BFS (너비우선탐색)**
   - 시작 정점에서부터 거리가 가까운 정점들부터 탐색하며, 단계별로 너비를 늘려가면서 탐색 진행. 시작 시점 탐색하고, 시작 정점의 자식 노드들을 모두 탐색. 더이상의 자식노드가 없을 경우에는 기준 정점을 자식 노드로 변경하여 다시 해당 노드를 기준으로 한 단계 깊은 정점 탐색
   - 활용 경우 : 최단 거리를 구해야하는 문제, 검색 대상 규모가 크지 않고 검색 시작 지점으로부터 가까운 경우
   - queue 자료구조 사용 & 방문 여부 확인 요소 필수
     - 시작 정점을 큐에 넣고, 해당 정점 방문 표시
     - 큐에서 정점을 하나 꺼내어 해당 정점을 현재 정점으로 설정
     - 현재  정점과 인접한 모든 정점 확인
     - 인접 정점 중 방문하지 않은 정점 큐에 넣고 방문 표시
     - 큐가 비어있으면 탐색 종료
       - 종료 조건 체크



**[AL3] 탐색/정렬/동적 계획법** 

**1. 탐색 알고리즘**
   - 선형 탐색 : 처음부터 끝까지 순차적으로 검색.  
     - 시간복잡도 최악 O(n) 
     - ( - ) : 데이터가 큰 경우 비효율적
   - 이진 탐색 : 정렬된 데이터 집합에서 특정 항목 찾는 방법.
     - 시간복잡도 최악 O(log n) 
     - (+) : 큰 규모 효율적 
     - ( - ) : 배열, 리스트와 같은 자료 구조에서만 구현
   - 해쉬 탐색 : 해시 함수 사용 키와 값 연결하는 데이터 구조. 키 해시 함수에 적용 -> 값위치 검색
     - 데이터가 해시 테이블에 해시 충돌이 적게 발생하도록 구성
     - 평균적으로 상수 O(1)
    

**2. 정렬 알고리즘**
   - 버블 정렬 : 배열을 처음부터 끝까지 반복하면서 인접한 원소를 비교, 위치 교환 
     - 활용 : 학습 도구  기초, 소규모 리스트
   - 선택 정렬 : 가장 작은 or 큰 값을 선택하고 그 값을 목록의 앞부분에 위치시키는 방식
     - 활용 : 정렬된 부분 리스트, 특정 위치 요소 찾기, 최대값 혹은 최소값 찾기, 중복요소 처리
   - 삽입 정렬 : 정렬되지 않은 부분의 첫번째 요소를 가져와, 정렬된 부분 적절한 위치에 삽입
     - 활용 : 거의 정렬된 상태, 새로운 요소 삽입 위치 찾기, 작은 크기
   - 퀵 정렬 : **피벗**이라는 임의의 요소를 선택, 이를 기준으로 리스트를 두 부분으로 나눔
     - 활용 : 대용량 데이터, 중간값 찾기, 중복 요소, 쿼리 정렬
   - 병합 정렬 : 리스트 절반으로 분할 및 재귀적으로 정렬, 병합
     - 분할 : 리스트 절반으로 분할, 크기가 1이하 될 때까지 반복
     - 정복 : 재귀적으로 정렬 및 병합
     - 병합 : 정렬된 부분 리스트 병합하여 최종 정렬 리스트 생성
     - 활용 : 대용량 데이터, 정렬된 부분 리스트 병합, 병합 정렬 변형
    

**3. 동적 계획법 (DP)**
   - 동적 계획법
   - 메모이제이션
   - 상향식 & 하향식 접근법
   - 최적 부분 구조 및 중복 문제



**4. 그래프 탐색 심화**
   - 다익스트라
   - 벨만-포드
   - 프롤이드 워셜

- 시뮬레이션 (구현)
- [에라토스테네스의 체](https://firework-ham.tistory.com/8)
  - 소수 : 자기자신과 1만 약수로 가지는 수 
  - 에라토스테네스의 체 : 소수의 배수를 지우면 남은 건 소수가 된다 라고 생각하는 알고리즘
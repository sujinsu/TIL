# [Mysql] INDEX



## 인덱스의 개념

> 테이블의 동작 속도 (조회)를 높여주는 자료구조. 
데이터의 위치를 빠르게 찾아주는 역할
> 

(+) : 조회 속도 향상, 시스템 성능 향상

( - ) : UPDATE, INSERT, DELETE  자주 발생 시 성능 악영향(페이지 정렬 작업, index 정보 갱신 추가 비용)/ 테이블 추가 공간 필요  

👉  효과적인 인덱스 설계 필요

- 단일 인덱스
    - QUERY 문
        - SELECT * FROM table1 WHERE name='홍길동' AND address='경기도';
    - name 컬럼과 address 컬럼 둘 중 어떤 컬럼이 빠른 검색 가능할지 판단 후 빠른 쪽 먼저 검색

```jsx
CREATE TABLE table1(
    uid INT(11) NOT NULL auto_increment,
    id VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    PRIMARY KEY('uid'),
    key idx_name(name),
    key idx_address(address)
)
```

- 다중 컬럼 인덱스
    - QUERY 문
        - SELECT * FROM table2 WHERE address='경기도';
        - → INDEX 사용 효과 X (name이 함께 검색되지 않아서 효과 볼 수 없음)
        - 다중 컬럼 인덱스를 사용할 때는 INDEX로 설정해준 제일 왼쪽컬럼 (name)이 WHERE절에 사용
    - name과 address 의 값을 같이 색인, 검색 ‘홍길동경기도’로 시도

```jsx
CREATE TABLE table2(
    uid INT(11) NOT NULL auto_increment,
    id VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(100) NOT NULL,
    PRIMARY KEY('uid'),
    key idx_name(name, address)    
)
```

## 주의할 점

- 한 테이블 당 3~5개 적당.  (목적에 따라 적절히)
- 조회 시 자주 사용하는 컬럼
- 고유한 값 위주
- 중복도가 낮을 수록
- INDEX 키의 크기는 작게
- PK, JOIN의 연결고리가 되는 컬럼
- 다중 컬럼 INDEX 생성 고려 >>> 단일 인덱스 여러개
- UPDATE가 빈번하지 않은 컬럼
- 가장 효율적인 자료형 : 정수형 자료 (가변적 데이터는 비효율적)

### 인덱스 문법

- 생성

```jsx
-- 단일 인덱스
CREATE INDEX 인덱스이름 ON 테이블이름(필드이름1)

-- 다중 컬럼 인덱스
CREATE INDEX 인덱스이름 ON 테이블이름(필드이름1, 필드이름2, ...)
```

- 조회

```jsx
SHOW INDEX FROM 테이블이름
```

- UNIQUE 인덱스 생성 (중복값 허용 X)

```jsx
-- 단일 인덱스
CREATE UNIQUE INDEX 인덱스 이름 ON 테이블이름(필드이름1)
-- 다중 컬럼 인덱스
CREATE UNIQUE INDEX 인덱스 이름 ON 테이블이름(필드이름1, 필드이름2, ...)
```

- 정렬

```jsx
CREATE INDEX 인덱스이름 ON 테이블이름 (필드이름 DESC)
CREATE INDEX 인덱스이름 ON 테이블이름 (필드이름 ASC)
```

- 삭제

```jsx
ALTER TABLE 테이블이름 DROP INDEX 인덱스이름;
```

- 추가

```jsx
ALTER TABLE 테이블이름 ADD (UNIQUE)INDEX 인덱스이름(컬럼명1, 컬럼명2...);
```

## 종류

- 클러스터형 인덱스
  
    ![Untitled](%5BMysql%5D%20INDEX%20356157b99da44241bc4f53824244ea0e/Untitled.png)
    
- 테이블당 1개
- 기본 키 지정된 컬럼 → 자동으로 클러스터링 인덱스
- 실제 저장된 데이터와 같은 무리 페이지 구조
- 클러스터링 인덱스 기준 데이터 자동 정렬

- 보조 인덱스
  
    ![Untitled](%5BMysql%5D%20INDEX%20356157b99da44241bc4f53824244ea0e/Untitled%201.png)
    
- 한 테이블에 여러 개 가능
- UNIQUE 키워드로 고유 컬럼 지정 → 자동으로 보조 인덱스 생성
- 실제 저장된 데이터와 다른 무리 별도 페이지 구조
- 정렬 X
- CREATE INDEX문으로 직접 보조 인덱스 생성 가능

## 동작 원리

- 균형 트리 (B-tree)
    - 노드 : 데이터가 저장되는 공간

자료: https://spiderwebcoding.tistory.com/6, https://rachel0115.tistory.com/entry/MySQL-%EC%9D%B8%EB%8D%B1%EC%8A%A4-INDEX-%EC%A0%95%EB%A6%AC-%EB%8F%99%EC%9E%91-%EB%B0%A9%EC%8B%9D-%EC%83%9D%EC%84%B1-%EC%82%AD%EC%A0%9C-%EC%84%A4%EA%B3%84
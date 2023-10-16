

# 파일허가권


![image](https://github.com/sujinsu/TIL/assets/87465326/2dfa0803-a303-4e98-8222-9248a4ce1d4e)

![image](https://github.com/sujinsu/TIL/assets/87465326/5ada55af-980b-462d-96f5-8db356f40211)


### **총 8개의 항목**

1. **유형**

- `-` : 일반 파일
- `d` : 디렉토리
- `c` : 문자 디바이스
- `l` : 링크

2. **파일 허가권**



> 세 개씩 잘라서 읽어야 한다.

- 소유자(user) / 그룹(group) / 기타사용자 (other) / 전부 (all) 에 대한 permission
- r : read 읽기 권한
- w : write 쓰기 권한
- x : execute 실행 권한



>  권한을 위한 두 가지 방법

- **문장부호**
  - `chmod o-x file.txt` : other에 대해  x권한을 제거하라
  - `chmod go+rw file.txt` : group과 other 에게 읽기, 쓰기 권한을 줘라
- **숫자**
  - `chmod 777 file.txt` : 사용자, 그룹, 다른 사용자에게 모든 권한 추가하라
  - `chmod 000 file.txt` : 사용자, 그룹, 다른 사용자의 모든 권한을 제거하라









3. **링크의 수**

4. **해당 파일 소유권을 가진 소유 사용자 이름**
5. **해당 파일 소유한 그룹 이름**
6. **파일 크기**
7. **최종 수정 일시**
8. **해당 파일 이름**

자료)
[리눅스 ls -l 명령어 - 파일 정보 확인과 의미](https://www.leafcats.com/137)
[리눅스 chmod 명령어 - 리눅스 허가권(Permission)](https://www.leafcats.com/138)

### 선행조건

- node.js 설치

[Node.js](https://nodejs.org/en)

### Firebase

- 프로젝트 생성

- Firestore Database 설정

  - 추후 firebase init 단계에서 만들어도 되고, 미리 생성해도 된다

  - Firestore Database 시작 → 모드 설정 (테스트 모드/운영 모드)

    - 테스트 모드 : 모든 사용자에게 읽기/쓰기 권한 부여 (보안 취약)
    - 운영 모드 : 모든 읽기/쓰기 권한을 거부, 보안 규칙을 통해 접근 제어

  - Firestore condole 규칙 탭으로 이동하여 `**보안 규칙**` 작성

    ```jsx
    rules_version = '2';
    
    service cloud.firestore {
      match /databases/{database}/documents {
        match /{document=**} {
          allow read, write: if false;
        }
      }
    }
    ```

- Cloud Functions 설정 (요청 제한 로직 구현)

  - firebase CLI 설치

    - `npm install -g firebase-tools`

  - firebase Login

    - `firebase login`
    - Windows powershall 실행 정책으로 스크립트 실행 불가시 → `Set-ExecutionPolicy RemoteSigned`  ( 복원: `Set-ExecutionPolicy Restricted`**)**

  - cloud functions 초기화

    - `firebase init`

    ```jsx
    * Functions
    : Firebase Cloud Functions를 설정하고 관련 파일을 생성합니다. 
    이는 HTTP 요청을 처리하는 서버리스 함수를 작성하기 위해 필요합니다.
    
    * Firestore
    : Firestore 데이터베이스를 사용하여 사용자의 요청 횟수 및 시간을 저장하고 관리합니다. 
    따라서 Firestore의 보안 규칙과 인덱스 파일을 설정하는 것이 필요합니다.
    ```

  - 필요 모듈 설치 : `npm install firebase-functions firebase-admin`

- Cloud Functions 배포

  - ```
    firebase deploy --only functions
    ```

    - if there are errors, 

      ```
      npm run lint -- --fix
      ```

      - 자동으로 수정 시도

[Firebase 데이터베이스 구조 설정]

Users Collection

- User Document (ID로 구별)
  - **`lastRequest`**: 마지막 요청 시간 (Timestamp)
  - **`requestCount`**: 일정 시간 내 요청 횟수 (Number)

functions 폴더 하위에 js 파일 생성 후 작성.

```jsx
// index.js

const functions = require("firebase-functions");
const admin = require("firebase-admin");
admin.initializeApp();

exports.limitRequests = functions.https.onRequest(async (req, res) => {
  // 클라이언트의 IP 주소를 가져옵니다
  const userIp = req.headers["x-forwarded-for"] || req.connection.remoteAddress;
  const ipRef = admin.firestore().collection("ips").doc(userIp);

  try {
    await admin.firestore().runTransaction(async (transaction) => {
      const doc = await transaction.get(ipRef);

      // IP 주소에 대한 문서가 없다면 생성합니다
      if (!doc.exists) {
        transaction.set(ipRef, {
          lastRequest: admin.firestore.Timestamp.now(),
          requestCount: 1,
        });
        res.send("Request succeeded");
        return;
      }

      const data = doc.data();
      const now = admin.firestore.Timestamp.now();
      const limit = 5; // 5분 동안 최대 요청 횟수
      const timeLimit = 300; // 5분 (초 단위)

      // 요청 간격과 횟수 확인
      if (data.lastRequest.toMillis() > (now.toMillis() - timeLimit * 1000) &&
          data.requestCount >= limit) {
        throw new Error("Request limit exceeded");
      }

      // 요청 카운트 업데이트
      const newCount = data.lastRequest.toMillis() >
        (now.toMillis() - timeLimit * 1000) ? data.requestCount + 1 : 1;

      transaction.update(ipRef, {
        lastRequest: now,
        requestCount: newCount,
      });
    });

    res.send("Request succeeded");
  } catch (error) {
    console.error("Error:", error);
    res.status(429).send(error.message);
  }
});
```
```jsx
// 요구사항에 따른 데이터 구조 변화
const functions = require("firebase-functions");
const admin = require("firebase-admin");
admin.initializeApp();

exports.limitRequests = functions.https.onRequest(async (req, res) => {
  const userIp = req.headers["x-forwarded-for"] || req.connection.remoteAddress;
  const ipRef = admin.firestore().collection("ips").doc(userIp);

  try {
    const doc = await ipRef.get();

    if (!doc.exists) {
      await ipRef.set({
        requestCount: 1,
      });
      res.send("Request succeeded");
      return;
    }

    const data = doc.data();
    if (data.requestCount >= 10) {
      throw new Error(`Request limit exceeded: ${userIp}`);
    }

    await ipRef.update({
      requestCount: admin.firestore.FieldValue.increment(1),
    });

    res.send("Request succeeded");
  } catch (error) {
    console.error("Error:", error);
    res.status(429).send(error.message);
  }
});
```
```jsx
// cors 설정 추가
const functions = require("firebase-functions");
const cors = require("cors")({origin: "[허용할 도메인]"});
const admin = require("firebase-admin");
admin.initializeApp();

exports.limitRequests = functions.https.onRequest((req, res) => {
  cors(req, res, async () => {
    const userIp = req.headers["x-forwarded-for"]||req.connection.remoteAddress;
    const ipRef = admin.firestore().collection("ips").doc(userIp);

    try {
      const doc = await ipRef.get();

      if (!doc.exists) {
        await ipRef.set({
          requestCount: 1,
        });
        res.send("Request succeeded");
        return;
      }

      const data = doc.data();
      if (data.requestCount >= 10) {
        throw new Error(`Request limit exceeded: ${userIp}`);
      }

      await ipRef.update({
        requestCount: admin.firestore.FieldValue.increment(1),
      });

      res.send("Request succeeded");
    } catch (error) {
      console.error("Error:", error);
      res.status(429).send(error.message);
    }
  });
});
```
- cors 설정 확인
    - 브라우저의 CORS 정책에 영향 x -⇒ CORS 정책이 클라이언트 측에서만 적용된다는 것을 확인하는 데 유용
        - cURL 또는 다른 커맨드 라인 도구 사용
        - postman
    - 간단한 html
    ```
  <!DOCTYPE html>
  <html>
  <body>
  <script>
    fetch('https://us-central1-fir-3c30d.cloudfunctions.net/limitRequests')
      .then(response => response.text())
      .then(data => console.log(data))
      .catch(error => console.error('Error:', error));
  </script>
  </body>
  </html>
  ```
  - ![image](https://github.com/sujinsu/TIL/assets/87465326/40ae9f85-730e-487a-8732-15a51aeb0402)


### 요금 정책
https://firebase.google.com/pricing?hl=ko
```jsx
Firebase Cloud Functions 비용
무료 티어 (Blaze 플랜의 무료 할당량):

월 2,000,000회의 함수 호출 무료.
매일 400,000GB-초의 컴퓨팅 시간 무료.
매일 5GB의 네트워크 아웃바운드 데이터 전송 무료.
유료 사용 (무료 티어 초과 시):

함수 호출: $0.0000025/호출 (2,000,000회 초과 시)
컴퓨팅 시간: $0.00008/GB-초 (400,000GB-초 초과 시)
아웃바운드 네트워크: $0.12/GB (5GB 초과 시)
```
```jsx
Firebase의 비용은 주로 사용한 서비스와 해당 서비스의 사용량에 따라 달라집니다. Firebase는 Cloud Firestore, Realtime Database, Cloud Functions, Authentication, Hosting 등 다양한 서비스를 제공하며, 각 서비스마다 고유의 요금 체계가 있습니다.

여기서는 Firebase Cloud Functions의 사용량에 초점을 맞춰 대략적인 비용을 예측해 보겠습니다. Cloud Functions의 비용은 함수 실행 횟수, 실행 시간, 네트워크 사용량에 따라 달라집니다. 또한 Firebase는 무료 티어(Blaze 플랜)를 제공하므로, 일정 사용량까지는 비용이 발생하지 않습니다.

Firebase Cloud Functions 비용
무료 티어 (Blaze 플랜의 무료 할당량):

월 2,000,000회의 함수 호출 무료.
매일 400,000GB-초의 컴퓨팅 시간 무료.
매일 5GB의 네트워크 아웃바운드 데이터 전송 무료.
유료 사용 (무료 티어 초과 시):

함수 호출: $0.0000025/호출 (2,000,000회 초과 시)
컴퓨팅 시간: $0.00008/GB-초 (400,000GB-초 초과 시)
아웃바운드 네트워크: $0.12/GB (5GB 초과 시)
사용량별 예상 비용
소규모 사용자: 월 1,000,000회 호출, 200,000GB-초의 컴퓨팅 시간, 2GB의 아웃바운드 데이터

비용: 무료 (무료 티어 내)
중간 규모 사용자: 월 3,000,000회 호출, 600,000GB-초의 컴퓨팅 시간, 10GB의 아웃바운드 데이터

추가 호출 비용: $0.0000025 × 1,000,000 = $2.5
추가 컴퓨팅 시간 비용: $0.00008 × 200,000 = $16
추가 네트워크 비용: $0.12 × 5 = $0.60
총 비용: $19.1 (추가 비용)
대규모 사용자: 월 10,000,000회 호출, 2,000,000GB-초의 컴퓨팅 시간, 50GB의 아웃바운드 데이터

추가 호출 비용: $0.0000025 × 8,000,000 = $20
추가 컴퓨팅 시간 비용: $0.00008 × 1,600,000 = $128
추가 네트워크 비용: $0.12 × 45 = $5.40
총 비용: $153.4 (추가 비용)
```

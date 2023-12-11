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
```
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

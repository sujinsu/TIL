//// index.js
//
//
//const functions = require("firebase-functions");
//const admin = require("firebase-admin");
//admin.initializeApp();
//
//exports.limitRequests = functions.https.onRequest(async (req, res) => {
//  // 클라이언트의 IP 주소를 가져옵니다
//  const userIp = req.headers["x-forwarded-for"] || req.connection.remoteAddress;
//  const ipRef = admin.firestore().collection("ips").doc(userIp);
//
//  try {
//    await admin.firestore().runTransaction(async (transaction) => {
//      const doc = await transaction.get(ipRef);
//
//      // IP 주소에 대한 문서가 없다면 생성합니다
//      if (!doc.exists) {
//        transaction.set(ipRef, {
//          lastRequest: admin.firestore.Timestamp.now(),
//          requestCount: 1,
//        });
//        res.send("Request succeeded");
//        return;
//      }
//
//      const data = doc.data();
//      const now = admin.firestore.Timestamp.now();
//      const limit = 5; // 5분 동안 최대 요청 횟수
//      const timeLimit = 300; // 5분 (초 단위)
//
//      // 요청 간격과 횟수 확인
//      if (data.lastRequest.toMillis() > (now.toMillis() - timeLimit * 1000) &&
//          data.requestCount >= limit) {
//        throw new Error("Request limit exceeded");
//      }
//
//      // 요청 카운트 업데이트
//      const newCount = data.lastRequest.toMillis() >
//        (now.toMillis() - timeLimit * 1000) ? data.requestCount + 1 : 1;
//
//      transaction.update(ipRef, {
//        lastRequest: now,
//        requestCount: newCount,
//      });
//    });
//
//    res.send("Request succeeded");
//  } catch (error) {
//    console.error("Error:", error);
//    res.status(429).send(error.message);
//  }
//});
//

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

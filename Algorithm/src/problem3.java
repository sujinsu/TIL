import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
/*
INPUT

9
2026-01-07T09:00:00 m1 t1 5000 PAY
2026-01-07T09:01:00 m1 t1 5000 PAY
2026-01-07T10:00:00 m2 t2 7000 PAY
2026-01-07T11:00:00 m1 t1 5000 CANCEL
2026-01-08T11:00:01 m2 t2 7000 CANCEL
2026-01-08T09:00:00 m3 t3 1000 CANCEL
2026-01-08T09:00:00 m3 t3 1000 PAY
2026-01-08T10:00:00 m3 t3 1000 CANCEL
2026-01-08T10:00:00 m1 t4 2000 PAY
 */
/*
OUTPUT

m2 7000
m1 2000
 */
/*
timestamp merchantId txId amount type

- timestamp: YYYY-MM-DDTHH:MM:SS
- merchantId: 문자열(공백 없음)
- txId: 문자열(공백 없음) — 거래 고유 ID
- amount: 양의 정수
- type: PAY 또는 CANCEL

 */
public class problem3 {

    static final String PAY = "PAY";
    static final String CANCEL = "CANCEL";

    static class Transaction {
        long time;
        String merchantId;
        String txId;
        long amount;
        String type;

        public Transaction(long time, String merchantId, String txId, long amount, String type) {
            this.time = time;
            this.merchantId = merchantId;
            this.txId = txId;
            this.amount = amount;
            this.type = type;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<String,Long> balance = new HashMap<>(); // merchantId -> balance
        Map<String, Transaction> req = new HashMap<>(); // txId -> tracsaction
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long sec = LocalDateTime.parse(st.nextToken()).toEpochSecond(ZoneOffset.UTC);
            String merchantId = st.nextToken();
            String txId = st.nextToken();
            long amount = Long.parseLong(st.nextToken());
            String type = st.nextToken();

            // O & 취소 -> 취소처리 (86400초 이내에만)
            // X & 승인 -> 승인 처리
            if(req.containsKey(txId) && type.equals(CANCEL)){
                Transaction prev = req.get(txId);
                if(CANCEL.equals(prev.type)) continue;
                if(sec - prev.time <= 86400){
                    balance.put(prev.merchantId, balance.getOrDefault(prev.merchantId, 0L) - prev.amount); // 매출 금액 정정
                    prev.type = type;
                    req.put(txId, prev); // 처리 상태 업데이트
                }
            } else if(!req.containsKey(txId) && type.equals(PAY)){
                balance.put(merchantId, balance.getOrDefault(merchantId, 0L) + amount);
                req.put(txId, new Transaction(sec, merchantId, txId, amount, type));
            }

        }

        ArrayList<Map.Entry<String, Long>> entries = new ArrayList<>(balance.entrySet());
        balance.entrySet()
                .stream()
                .sorted((a, b) -> {
                    int cmp = Long.compare(b.getValue(), a.getValue());
                    if (cmp != 0) return cmp;
                    return a.getKey().compareTo(b.getKey());
                })
                .filter(e -> e.getValue() != 0L)
                .forEach(e -> System.out.println(e.getKey() + " " + e.getValue()));
    }
}

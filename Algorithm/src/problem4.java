import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/*
[문제]
결제 요청 로그가 시간순(오래된 → 최신) 으로 주어진다.

timestamp cardId amount

- timestamp: YYYY-MM-DDTHH:MM:SS
- cardId: 문자열(공백 없음)
- amount: 양의 정수

같은 cardId에서 직전 60초 동안(포함) 같은 amount로 ALLOW된 결제가 이미 2번 있었다면,
이번 결제는 BLOCK
그리고 해당 카드는 현재 시각부터 600초(10분) 동안 차단

blockedUntil = now + 600 (경계는 now < blockedUntil일 때 차단)
그렇지 않으면 ALLOW
중요: BLOCK된 결제는 “ALLOW 기록”에 포함하지 않는다.

즉, “같은 카드 + 같은 금액”이 60초 창에서 3번째가 되면 그 3번째가 BLOCK되고 카드가 10분간 잠김.
 */
/*
INPUT
첫 줄: N (1 ≤ N ≤ 200000)
8
2026-01-07T09:00:00 c1 1000
2026-01-07T09:00:10 c1 1000
2026-01-07T09:00:20 c1 1000
2026-01-07T09:00:30 c1 2000
2026-01-07T09:05:00 c1 1000
2026-01-07T09:10:19 c1 1000
2026-01-07T09:10:20 c1 1000
2026-01-07T09:10:21 c1 1000

OUTPUT
ALLOW
ALLOW
BLOCK
BLOCK
BLOCK
BLOCK
ALLOW
ALLOW
 */
public class problem4 {

    static final String BLOCK = "BLOCK";
    static final String ALLOW = "ALLOW";
    static final long WINDOW = 60L;
    static final long BLOCK_TIME = 600L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        // cardId -> blockedUntil(해제 시각)
        Map<String, Long> blockedUntil = new HashMap<>();

        // (cardId#amount) -> ALLOW된 시각들(초) 큐
        Map<String, Deque<Long>> timesByKey = new HashMap<>();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long now = LocalDateTime.parse(st.nextToken()).toEpochSecond(ZoneOffset.UTC);
            String cardId = st.nextToken();
            long amount = Long.parseLong(st.nextToken());

            // 1) 차단 여부 확인 (now < blockedUntil 이면 차단)
            Long until = blockedUntil.get(cardId);
            if (until != null) {
                if (now < until) {
                    sb.append(BLOCK).append('\n');
                    continue;
                } else {
                    // 차단 해제 시각이 지났으면 제거
                    blockedUntil.remove(cardId);
                }
            }

            // 2) (cardId, amount)별 60초 윈도우 체크
            String key = cardId + "#" + amount;
            Deque<Long> dq = timesByKey.get(key);
            if (dq == null) {
                dq = new ArrayDeque<>();
                timesByKey.put(key, dq);
            }

            // 직전 60초(포함) 유지 => time >= now-60 만 유지
            while (!dq.isEmpty() && dq.peekFirst() < now - WINDOW) {
                dq.removeFirst();
            }

            // 이미 2번 ALLOW가 있으면 이번이 3번째 시도 -> BLOCK + 10분 차단
            if (dq.size() >= 2) {
                sb.append(BLOCK).append('\n');
                blockedUntil.put(cardId, now + BLOCK_TIME);
            } else {
                sb.append(ALLOW).append('\n');
                dq.addLast(now); // ALLOW만 기록
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }
}

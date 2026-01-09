import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

/*
 INPUT
 6
2026-01-07T09:00:00 alice 1000 APPROVED
2026-01-07T09:01:00 bob 700 APPROVED
2026-01-07T09:02:00 alice 300 CANCELLED
2026-01-07T09:03:00 bob 200 CANCELLED
2026-01-07T09:04:00 carol 700 APPROVED
2026-01-07T09:05:00 bob 100 APPROVED

 */
public class problem1 {

    static final String APPROVED = "APPROVED";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().strip());

        // 유니크 userId가 최대 N개일 수 있으니 리사이즈를 줄이려는 초기 용량(선택 사항)
        Map<String, Long> map = new HashMap<>(Math.max(16, N * 2));

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            st.nextToken();                 // timestamp (요구사항상 미사용)
            String userId = st.nextToken(); // userId
            long amount = Long.parseLong(st.nextToken());
            String status = st.nextToken(); // APPROVED / CANCELLED

            long signed = status.equals(APPROVED) ? amount : -amount;
            map.put(userId, map.getOrDefault(userId, 0L) + signed);
        }

        // Entry 배열로 변환 후 정렬 (stream보다 보통 오버헤드 적음)
        List<Map.Entry<String, Long>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> {
            int cmp = Long.compare(b.getValue(), a.getValue());
            if (cmp != 0) return cmp;
            return a.getKey().compareTo(b.getKey());
        });

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Long> e : list) {
            long total = e.getValue();
            if (total == 0L) continue; // 누적 0은 출력하지 않음
            sb.append(e.getKey()).append(' ').append(total).append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }
}

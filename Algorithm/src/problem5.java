import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/*
maxConcurrent 내림차순
동점이면 merchantId 오름차순
maxConcurrent가 0인 건 없음(세션이 있으면 최소 1)

startTimestamp endTimestamp merchantId

INPUT
6
2026-01-07T09:00:00 2026-01-07T09:10:00 m1
2026-01-07T09:05:00 2026-01-07T09:12:00 m1
2026-01-07T09:10:00 2026-01-07T09:20:00 m1
2026-01-07T09:00:00 2026-01-07T09:30:00 m2
2026-01-07T09:15:00 2026-01-07T09:25:00 m2
2026-01-07T09:25:00 2026-01-07T09:40:00 m2

OUTPUT
m1 2
m2 2

 */
public class problem5 {

    static long toSec(String ts) {
        return LocalDateTime.parse(ts).toEpochSecond(ZoneOffset.UTC);
    }

    static int maxConcurrent(List<long[]> sessions) {
        int m = sessions.size();
        long[] starts = new long[m];
        long[] ends = new long[m];

        for (int i = 0; i < m; i++) {
            long[] s = sessions.get(i);
            starts[i] = s[0];
            ends[i] = s[1];
        }

        Arrays.sort(starts);
        Arrays.sort(ends);

        int i = 0, j = 0;
        int cur = 0, max = 0;

        // [start, end) 이므로 start == end면 "겹치지 않음" -> end를 먼저 처리해야 함
        while (i < m) {
            if (starts[i] < ends[j]) {
                cur++;
                if (cur > max) max = cur;
                i++;
            } else {
                cur--;
                j++;
            }
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        Map<String, List<long[]>> byMerchant = new HashMap<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long start = toSec(st.nextToken());
            long end = toSec(st.nextToken());
            String merchantId = st.nextToken();

            byMerchant.computeIfAbsent(merchantId, k -> new ArrayList<>()).add(new long[]{start, end});
        }

        List<Map.Entry<String, Integer>> result = new ArrayList<>();
        for (Map.Entry<String, List<long[]>> e : byMerchant.entrySet()) {
            int mx = maxConcurrent(e.getValue());
            result.add(new AbstractMap.SimpleEntry<>(e.getKey(), mx));
        }

        result.sort((a, b) -> {
            int cmp = Integer.compare(b.getValue(), a.getValue()); // maxConcurrent 내림차순
            if (cmp != 0) return cmp;
            return a.getKey().compareTo(b.getKey());               // 동점이면 merchantId 오름차순
        });

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> e : result) {
            sb.append(e.getKey()).append(' ').append(e.getValue()).append('\n');
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
    }
}

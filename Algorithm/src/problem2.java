import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/*
INPUT

7 10000
2026-01-07T09:00:00 alice 4000
2026-01-07T09:02:00 alice 3000
2026-01-07T09:04:59 alice 3500
2026-01-07T09:05:00 alice 500
2026-01-07T09:06:00 bob 9000
2026-01-07T09:07:00 bob 2000
2026-01-07T09:12:01 alice 7000

 */

/*
OUTPUT

ALLOW
ALLOW
BLOCK
ALLOW
ALLOW
BLOCK
ALLOW
 */
public class problem2 {

    static final String BLOCK = "BLOCK";
    static final String ALLOW = "ALLOW";

    static class Node {
        long time;
        String userId;
        long amount;

        Node(long time, String userId, long amount) {
            this.time = time;
            this.userId = userId;
            this.amount = amount;
        }
    }
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().strip());
        int N = Integer.parseInt(st.nextToken());
        long limit = Long.parseLong(st.nextToken());

        Map<String, Long> map = new HashMap<>(); // userId -> balance
        Deque<Node> queue = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer log  = new StringTokenizer(br.readLine().strip());
            LocalDateTime time = LocalDateTime.parse(log.nextToken());

            long sec = time.toEpochSecond(ZoneOffset.UTC);
            String userId = log.nextToken();
            long amount = Long.parseLong(log.nextToken());

            while(!queue.isEmpty() && queue.peekFirst().time < sec - 300){
                Node node = queue.removeFirst();
                map.put(node.userId, map.getOrDefault(node.userId, 0L) - node.amount);
            }
            Long balance = map.getOrDefault(userId, 0L);
            if(balance + amount > limit){
                sb.append(BLOCK).append('\n');
            }else{
                sb.append(ALLOW).append('\n');
                map.put(userId, balance + amount);
                queue.add(new Node(sec, userId, amount));
            }
        }
        System.out.print(sb);
    }
}

import java.util.Arrays;

public class pro_dfs_여행경로_251210 {
    public static void main(String[] args) {
        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        String[] result = solution(tickets);
        System.out.println("result = " + Arrays.toString(result));
    }

    private static final String START = "ICN";
    static int N;
    static String[] answer;
    static boolean found = false;
    public static String[] solution(String[][] tickets) {
        N = tickets.length;
        answer = new String[N+1];
        Arrays.sort(tickets, (a, b) -> a[1].compareTo(b[1]));
        boolean[] used = new boolean[N];
        dfs(tickets,used, START, 0);
        return answer;
    }

    static void dfs(String[][] tickets,boolean[] used, String now, int idx){
        answer[idx] = now;
        if(idx == N){
            found = true;
            return;
        }

        for(int i=0;i<N;i++){
            // 현재 위치에서 쓸 수 있고, 아직 안 쓴 티켓
            if(tickets[i][0].equals(now) && !used[i]){
                used[i] = true;
                dfs(tickets, used, tickets[i][1], idx+1);
                if (found) return;
                used[i] = false;
            }
        }
    }
}

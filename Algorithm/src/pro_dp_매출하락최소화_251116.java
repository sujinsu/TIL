import java.util.ArrayList;
import java.util.List;

public class pro_dp_매출하락최소화_251116 {
    public static void main(String[] args) {
        int[] sales = {14, 17, 15, 18, 19, 14, 13, 16, 28, 17};
        int[][] links = {{10, 8}, {1, 9}, {9, 7}, {5, 4}, {1, 5}, {5, 10}, {10, 6}, {1, 3}, {10, 2}};
        System.out.println(solution(sales,links));
    }
    public static int solution(int[] sales, int[][] links) {
        int n = sales.length;
        List<Integer>[] children = new ArrayList[n+1];
        for(int i=1;i<=n;i++){
            children[i] = new ArrayList<>();
        }
        for(int[] l:links){
            children[l[0]].add(l[1]);
        }

        int[][] dp = new int[n+1][2]; // [node][0] : node를 참석시키지 않았을 때의 최소 손실 / [node][1] 참석시켰을 때의 최소 손실
        dfs(1,sales,children,dp);

        int answer = Math.min(dp[1][0],dp[1][1]);
        return answer;
    }

    static void dfs(int node,int[] sales, List<Integer>[] children, int[][] dp){

        if (children[node].isEmpty()) {
            dp[node][0] = 0;
            dp[node][1] = sales[node - 1];
            return;
        }
        // 자식 먼저
        for(int c: children[node]){
            dfs(c,sales, children, dp);
        }

        // 참석
        int cost = sales[node-1];
        for(int c: children[node]){
            cost += Math.min(dp[c][0],dp[c][1]);
        }
        dp[node][1] = cost;

        // 불참
        int sum = 0;
        int increase = Integer.MAX_VALUE;
        boolean hasForced = false;
        for(int c: children[node]){
            sum += Math.min(dp[c][0], dp[c][1]);
            if(dp[c][1] <= dp[c][0]){
                hasForced = true;
            } else{
                increase = Math.min(increase, dp[c][1] -dp[c][0]);
            }
        }
        dp[node][0] = hasForced? sum : sum + increase;
    }
}

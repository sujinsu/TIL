import java.util.Arrays;

/***
 * (m,n) 주의 ! 코드에선 [n][m]
 */
public class pro_dp_등굣길_251114 {
    public static void main(String[] args) {
        int m=6, n=4;
        int[][] puddles = {{1,4},{2,2},{4,1}};
        solution(m, n, puddles);
    }

    public static int solution(int m, int n, int[][] puddles) {
        long MOD = 1_000_000_007;

        int[][] dp = new int[n][m];
        for(int[] p:puddles) dp[p[1]-1][p[0]-1] = -1;

        dp[0][0] = dp[0][0] == -1 ? 0 : 1;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }

                if(i==0 && j ==0) continue;

                int up = i == 0 ? 0 : dp[i-1][j];
                int left = j == 0 ? 0 : dp[i][j-1];
                dp[i][j] = (int) ((up + left) % MOD);

            }
        }

        return dp[n-1][m-1];
    }
}

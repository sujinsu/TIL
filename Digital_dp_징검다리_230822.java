import java.util.Scanner;
public class Digital_dp_징검다리_230822 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] stones = new int[N];
        for (int i = 0; i < N; i++) {
            stones[i] = sc.nextInt();
        }

        int answer  = 1;
        int[] dp = new int[N]; // 가짓수를 담는 배열, 1이 연속될 시에, 2 3 5 8 13 21 로 커짐
        dp[0] = 1;
        dp[N-1] = 1;
        if(stones[0] == 0 || stones[N-1]== 0){
            answer = 0;
        }else{
            for(int i=1; i<N-1;i++){
                if(stones[i-1] == 0 || stones[i] == 0 || stones[i+1] == 0){
                    dp[i] = 1;
                    continue;
                }
                if(dp[i-1] != 1){
                    dp[i] = dp[i-1] + dp[i-2];
                }else{
                    dp[i] = 2;
                }

            }
        }
        if(answer != 0){
            for(int i=1; i<N-1;i++){
                if(dp[i+1] == 1){
                    answer *= dp[i];
                }
            }
        }
        System.out.println(answer);
    }

}

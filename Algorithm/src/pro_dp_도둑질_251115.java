public class pro_dp_도둑질_251115 {
    public static void main(String[] args) {
        int[] money = {1, 2, 3, 1};
        System.out.println(solution(money));
    }
    public static int solution(int[] money) {
        int n = money.length;
        // 1. 첫번째 집을 털 경우 >> 마지막 집은 X
        // 2. 마지막 집을 털 경우
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        dp1[0] = money[0]; dp1[1] = dp1[0];
        dp2[0] = 0; dp2[1]=money[1];
        for(int i=2;i<n-1;i++){
            dp1[i] = Math.max(dp1[i-1], dp1[i-2]+money[i]);
        }
        for(int i=2;i<n;i++){
            dp2[i] = Math.max(dp2[i-1], dp2[i-2]+money[i]);
        }

        return Math.max(dp1[n-2],dp2[n-1]);
    }
}

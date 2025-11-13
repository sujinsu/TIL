import java.util.Scanner;
public class Digital_조합_재귀_대표 {

    public static void main(String args[]) {
        int x;
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int R = sc.nextInt();

        // 1. 계산으로 조합 푸는 방법
         int answer = 1;
         for(int i=N; i>R; i--){
             answer *= i;
         }

         for(int i=N-R;i>0;i--){
             answer /= i;
         }
//        int answer = calculateCombination(N, R);

         System.out.println(answer);
    }

    // 2. 재귀를 사용하여 조합을 푸는 방법
    private static int calculateCombination(int n, int r) {
        if (r == 0 || r == n) {
            return 1;
        }

        return calculateCombination(n - 1, r - 1) + calculateCombination(n - 1, r);
    }
}

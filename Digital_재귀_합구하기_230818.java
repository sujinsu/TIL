import java.util.Scanner;

/**
 * 1부터 주어진 수까지 합을 출력하라
 */
public class Digital_재귀_합구하기_230818 {
    public static void main(String args[]) {
        int x;
        Scanner sc = new Scanner(System.in);

        x = sc.nextInt();

        int result = plus(x, 0);

        System.out.println(result);
    }

    static int plus(int n, int result){
        result += n;
        if(n == 1){
            return result;
        }else{
            return plus(n-1, result);
        }
    }
}

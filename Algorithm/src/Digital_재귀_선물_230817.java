/**
 * 산타 할아버지는 반 학생들을 위해 서로 다른 선물을 준비했어요.
 * 반 학생이 선물을 가질 수 있는 경우의 수를 출력하는 프로그램을 만들어보세요.
 *
 * 1 <= N <= 20
 */
import java.util.Scanner;
public class Digital_재귀_선물_230817 {
    /**
     * 주의사항 : 자료형이 int 일 때, 약 21억이 넘어가면 음수가 나옴 (N = 20일 때)
     * -> 더 큰 숫자를 다룰 수 있는 데이터 타입 사용
     * -> 오버플로우 방지 방법 적용
     *
     * @param args
     */
    public static void main(String args[]) {
        long x;
        Scanner sc = new Scanner(System.in);

        x = sc.nextInt();
        long result = factorial(x);
        System.out.println(result);
    }

    public static long factorial(long n){
        if(n  <= 1){
            return 1;
        }else{
            return n * factorial(n-1);
        }
    }
}

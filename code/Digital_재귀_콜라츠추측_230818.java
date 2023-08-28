/**
 * 짝수일 때는 2로 나누어요.
 * 홀수일 때는 3을 곱하고 1을 더해요.
 * 숫작 1이 될 때까지 이 과정을 반복해요.
 * 1이 되기 위하여 적용시켜야 하는 콜라츠 추측의 횟수를 출력하는 프로그램
 */

import java.util.Scanner;
public class Digital_재귀_콜라츠추측_230818 {
    public static void main(String args[]) {
        int x;
        Scanner sc = new Scanner(System.in);

        x = sc.nextInt();

        System.out.println(compute(x,0));
    }

    static int compute(int n, int count){
        if(n ==1){
            return count;
        }

        boolean check = n%2 == 1 ? true : false;
        if(check){
            n = n * 3 + 1;
        }else{
            n /= 2;
        }
        return compute(n,++count);
    }
}

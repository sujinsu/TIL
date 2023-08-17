/**
 * 1 ≤ b < a ≤ n ≤ 1,000,000
 *
 * 콜라를 받기 위해 마트에 주어야 하는 병 수 a, 빈 병 a개를 가져다 주면 마트가 주는 콜라 병 수 b, 상빈이가 가지고 있는 빈 병의 개수 n이 매개변수로 주어집니다. 상빈이가 받을 수 있는 콜라의 병 수를 return 하도록 solution 함수를 작성해주세요.
 */
public class pro_콜라문제_230724 {
    public int solution(int a, int b, int n) {
        int answer = 0;

        while(n >= 2 && n >= a){
            // cnt : 추가로 받을 병
            int cnt = (n / a) * b;
            n = cnt + (n % a);
            answer += cnt;
        }
        return answer;
    }
}

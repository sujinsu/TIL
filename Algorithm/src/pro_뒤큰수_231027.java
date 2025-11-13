import java.util.*;

/**
 * TODO 다시 읽고 생각해보기
 */
public class pro_뒤큰수_231027 {
    public int[] solution(int[] numbers) {
        int N = numbers.length;
        int[] answer = new int[N];
        Stack<Integer> stack = new Stack<>();

        Arrays.fill(answer, -1);  // 처음에는 모든 원소를 -1로 초기화

        for (int i = 0; i < N; i++) {
            // 현재 원소가 스택의 top 원소보다 큰 동안 stack에서 pop
            while (!stack.isEmpty() && numbers[stack.peek()] < numbers[i]) {
                answer[stack.pop()] = numbers[i];
            }
            stack.push(i);  // 현재 인덱스를 스택에 push
        }

        return answer;
    }
}

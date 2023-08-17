/**
 * 입력
 * 용사와 악당의 결투 내용을 나타내는 괄호 문자열을 한 줄에 입력합니다.하나의 괄호 문자열의 길이는 2 이상 50 이하의 정수입니다.
 * 출력
 * 방어에 성공했으면 “YES”, 아니면 “NO”를 출력합니다.
 */
import java.util.*;
public class Digital_stack_괄호_230731 {
    public static String checkDefense(String duel) {
        // TODO:
        Stack<Character> stack = new Stack<Character>();
        for(int i=0; i<duel.length();i++){
            char c = duel.charAt(i);

            // '(' 면 스택에 담기
            if(c == '('){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return "NO";
                }
                stack.pop();
            }
        }
        if(stack.isEmpty()){
            return "YES";
        }
        return "NO";
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String duel = scanner.nextLine();
        String result = checkDefense(duel);
        System.out.println(result);
    }
}

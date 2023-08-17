/**
 * Stack 컴퓨터 수식 계산 (후위표기식 -> 중위표기식)
 * 첫 번째 줄에 중위 표기식을 입력합니다. 식은 길이가 100을 넘지 않으며 100을 넘지 않는 양의 정수와 연산자 +, -, * , /, (, ) 로 구성된 완벽한 수식입니다.
 */



import java.util.*;

class Digital_stack_컴퓨터수식_230802 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String target = sc.nextLine();

        StringBuilder answer = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for(char t:target.toCharArray()){
            // 피연산자
            if(Character.isDigit(t)){
                answer.append(t);
            }
            // 괄호
            else if(t == '('){
                stack.push(t);
            }
            else if(t == ')'){
                while(!stack.isEmpty()  && stack.peek() != '('){
                    answer.append(stack.pop());
                }

                if(!stack.isEmpty()  && stack.peek() == '('){
                    stack.pop();
                }
            }
            // 연산자
            else{
                if(stack.isEmpty()){
                    stack.push(t);
                    continue;
                }
                
                while(!stack.isEmpty() && precedence(t) <= precedence(stack.peek())){
                    answer.append(stack.pop());
                }
                stack.push(t);
                
                
            }

        }
        while(!stack.isEmpty()){
            answer.append(stack.pop());
        }
        System.out.println(answer);
    }

    public static int precedence(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return 0;
    }
}

Stack 수열

```
수열에서도 큰 원소와 가장 작은 원소의 차이를 어사치라고 부르고, 어사치를 구하는 것
전체 수열에서만 어사치를 구하는 것이 아니라 모든 부분 수열에서도 구해보려고 합니다.
수열이 주어졌을 때, 모든 부분 수열의 어사치의 합을 출력하는 프로그램을 작성하세요
```


```java
import java.util.*;

class Main {
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
```
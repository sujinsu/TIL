queue 병정

```
카드 병정들은 간단한 게임을 통해 퇴근하는 순서를 정합니다. 
1번 병정부터 N번 병정까지 
N명의 병정이 번호 순서대로 원을 이루면서 앉아있고, 양의 정수 K를 정합니다. 그리고 1번 병정부터 숫자를 세기 시작하여 K번째 병정은 바로 퇴근합니다.

한 병정이 퇴근하면 남은 병정들로 이루어진 원을 따라 직전에 퇴근한 다음 순서의 병정부터 숫자를 세기 시작하여 이 과정을 반복합니다.

예를 들어, 병정이 7명이고 K가 3이면 출력은 <3, 6, 2, 7, 5, 1, 4>와 같습니다.
```


```java
import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        List<Integer> data = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        for (int i=1;i<N+1;i++){
            data.add(i);
        }

        int idx = 0;
        while(!data.isEmpty()){
            int num = data.size();
            idx = (idx + K-1) % num;
            result.add(data.remove(idx));
        }
        System.out.println(result);
    }
}
```










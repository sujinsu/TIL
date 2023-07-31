Stack 괄호

```
1번부터 N번까지 번호를 가진 N명의 사람들이 있습니다.

1번부터 순서대로 K번째 사람을 원에서 제거합니다.

원을 이루고 있는 사람들이 모두 제거되었을 때,

제거된 사람들의 번호를 순서대로 나열한 것을 요세푸스 순열이라고 합니다.

입력 예시
7 3

출력 예시
3 6 2 7 5 1 4
```


```java
import java.util.*;

class Main {
    public static List<Integer> josephusSequence(int N, int K) {
        List<Integer> result = new ArrayList<>();
        List<Integer> sequence = new ArrayList<>();
        for (int i=1;i<N+1;i++){
            sequence.add(i);
        }

        // 다음 K 번째 제거
        int idx = 0;
        while(!sequence.isEmpty()){
            idx = (idx + K-1) % sequence.size();
            result.add(sequence.get(idx));
            sequence.remove(idx);
        }   
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        
        List<Integer> sequence = josephusSequence(N, K);
        
        System.out.println(sequence);
    }
}

```
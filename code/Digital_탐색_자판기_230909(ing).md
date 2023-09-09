

```java
import java.util.*;
class Main {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Integer> times = new ArrayList<>();
        for(int i=0;i<m;i++){
            times.add(sc.nextInt());
        }
        System.out.println(compute(n,m,times));
        
    }

    public static int compute(int n, int m, List<Integer> times){
        int count = 0;
        if(n < m){
            return n; 
        }else{
            count += m;
        }

        int curtime = 1;
        // 매 분마다 이전 시간의 약수 였던 자판기를 카운트 -> 도중 만족하면 번호 출력
        while(true){
            for(int i=0;i<m;i++){
                if(curtime % times.get(i) == 0){
                    count += 1;
                    if(count == n){
                        return i+1;
                    }
                }
            }
            curtime += 1;
        }
    }
}
```
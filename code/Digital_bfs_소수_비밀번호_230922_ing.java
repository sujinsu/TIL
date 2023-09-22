package code;

import java.util.*;
import java.util.stream.Stream;

/**
 * 지시사항
 * 입력
 * 4자리 소수 두 개를 공백을 두고 입력합니다.
 * 출력
 * 두 소수 사이의 변환에 필요한 최소 횟수를 출력합니다.
 * 불가능한 경우 Impossible을 출력합니다.
 */
public class Digital_bfs_소수_비밀번호_230922_ing {

    static class data{
        int count;
        int pw;

        data(int count, int pw){
            this.count = count;
            this.pw = pw;
        }
    }

    static boolean[] isPrime;

    public static void main(String[] args) {
        isPrime = eratosthenes(9999);

        Scanner sc = new Scanner(System.in);
        int oldPw = sc.nextInt();
        int newPw = sc.nextInt();
        int answer = bfs(oldPw, newPw);

        if(answer == -1){
            System.out.println("Impossible");
        }else{
            System.out.println(answer);
        }
    }

    public static boolean[] eratosthenes(int n){
        boolean[] isPrime = new boolean[n+1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        for(int i=2;i * i <= n; i++){
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    public static int bfs(int oldPw, int newPw){
        boolean[] visited = new boolean[10000];
        Queue<data> queue = new LinkedList<>();

        if(oldPw == newPw) return 0;

        queue.add(new data(0, oldPw));
        visited[oldPw] = true;

        while(!queue.isEmpty()){
            data cur = queue.poll();
            // 네자리 수를 쪼개서 각 자리별로 0 ~ 9 본인 제외 ㄱㄱ

            int start = 1;
            int pwList[] = Stream.of(String.valueOf(cur.pw).split("")).mapToInt(Integer::parseInt).toArray();
            for(int i=0;i<4;i++){


                int temp = cur.pw - (int) (pwList[i] * Math.pow(10, 4-(i+1)));
                // System.out.println("BEFORE :::"+temp);
                for(int j=start;j<=9; j++){
                    int newTemp = temp + (int) (j * Math.pow(10, 4-(i+1)));
                    // System.out.println("After :::"+newTemp);
                    if(newTemp == newPw){
                        return cur.count +1 ;
                    }
                    // 아직 한 적없는 비밀번호 & 소수
                    if(!visited[newTemp] && isPrime[newTemp]){
                        visited[newTemp] = true;
                        queue.add(new data(cur.count+1, newTemp));
                        // System.out.println("Go :::"+newTemp);
                    }
                }
                start = 0;
            }

        }

        return -1;
    }
}

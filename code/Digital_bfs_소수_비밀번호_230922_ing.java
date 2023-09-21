package code;

import java.util.*;

/**
 * 지시사항
 * 입력
 * 4자리 소수 두 개를 공백을 두고 입력합니다.
 * 출력
 * 두 소수 사이의 변환에 필요한 최소 횟수를 출력합니다.
 * 불가능한 경우 Impossible을 출력합니다.
 */
public class Digital_bfs_소수_비밀번호_230922_ing {

    class data{
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


    }

    public static boolean[] eratosthenes(int n){
        boolean[] isPrime = new boolean[n];

        for(int i=2;i * i <= n; i++){
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }
        return isPrime;
    }

    public int bfs(int oldPw, int newPw){
        boolean[] visited = new boolean[9999];
        Queue<data> queue = new LinkedList<>();

        if(oldPw == newPw) return 0;

        queue.add(new data(0, oldPw));
        visited[oldPw] = true;

        while(!queue.isEmpty()){
            data cur = queue.poll();
            // 네자리 수를 쪼개서 각 자리별로 0 ~ 9 본인 제외 ㄱㄱ

            int start = 1;
            for(int i=0;i<4;i++){
                // 아직 한 적없는 비밀번호 & 소수
                int temp = (int) (oldPw - oldPw / Math.pow(10, 4-(i+1)));

                for(int j=start;j<=9; j++){
                    temp += j * Math.pow(10, 4-(i+1));

                    if(temp == newPw){
                        return cur.count +1 ;
                    }

                    if(!visited[temp] && isPrime[temp]){
                        visited[temp] = true;
                        queue.add(new data(cur.count+1, temp));
                    }
                }
                start = 0;
            }

        }

        return -1;
    }
}

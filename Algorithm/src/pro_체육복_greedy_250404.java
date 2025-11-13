import java.util.*;

class pro_체육복_greedy_250404 {
    public static void main(String[] args) {

    }
    public int solution(int n, int[] lost, int[] reserve) {

        int[] students = new int[n+1];
        for(int l:lost){
            students[l] -= 1;
        }
        for(int r:reserve){
            students[r] += 1;
        }
        for(int i=1; i<n+1;i++){
            if(students[i] < 0){
                int idx = i-1;
                if(students[i-1] > 0){
                    idx = i-1;
                }else if(i+1<= n && students[i+1] > 0){
                    idx = i+1;
                }else{
                    continue;
                }
                students[i] += 1;
                students[idx] -= 1;

            }
        }

        int answer = n - (int) Arrays.stream(students).filter(s -> s < 0).count();
        return answer;
    }
}
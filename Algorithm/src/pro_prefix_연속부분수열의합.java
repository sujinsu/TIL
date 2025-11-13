import java.util.*;

public class pro_prefix_연속부분수열의합 {
    public static void main(String[] args) {
        int[] elements = {7,9,1,1,4};
        System.out.println(Arrays.toString(elements));
        int n = elements.length;
        int[] arr = new int[2 * n];
        int[] prefix = new int[2 * n + 1];
        for(int i=0;i<2*n;i++){
            arr[i] = elements[i % n];
        }
        // 누적합
        for(int i=1;i<2*n+1;i++){
            prefix[i] = prefix[i-1] + arr[i-1];
        }

        Set<Integer> answer  = new HashSet<>();
        for(int len=1;len<=n;len++){
            for(int start=0;start<n;start++){
                int end = start + len;
                int temp = prefix[end] - prefix[start];
                answer.add(temp);
            }
        }
        // answer.size();

    }


}
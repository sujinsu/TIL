import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

public class hkr_PickingNumbers_251119 {
    public static void main(String[] args) throws IOException {

//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        int n = Integer.parseInt(bufferedReader.readLine().trim());

//        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
//                .map(Integer::parseInt)
//                .collect(toList());

        List<Integer> a = Arrays.asList(6, 4, 6, 5, 3, 3, 1);
        int result = Result.pickingNumbers(a);
        System.out.println(result);
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
    }
    class Result {

        /*
         * Complete the 'pickingNumbers' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts INTEGER_ARRAY a as parameter.
         */

        public static int pickingNumbers(List<Integer> a) {
            /**
             * 값 범위가 0~100이라 int[] count 로 바로 카운트 → HashMap보다 가볍고 빠름.
             *
             * 첫 for: 입력 배열 한 번 순회 → O(N)
             * 두 번째 for: i = 0..99 → O(100) → 상수
             *
             * 전체: O(N + 100) ≒ O(N)
             */

            final int maxN = 100;
            int longestLength = 0;
            int[] count = new int[maxN+1];
            for(int now:a){
                count[now]++;
            }
            for(int i=0;i<maxN;i++){
                longestLength = Math.max(count[i]+count[i+1],longestLength);
            }
            return longestLength;
        }

    }


}

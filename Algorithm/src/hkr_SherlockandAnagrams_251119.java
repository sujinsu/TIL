import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class hkr_SherlockandAnagrams_251119 {
    public static void main(String[] args) throws IOException {
        String s = "ifailuhkqq";
        int result = Result.sherlockAndAnagrams(s);
        System.out.println(result);
    }

    /**
     * 각 substring의 문자를 정렬한 결과를 key로 HashMap에 카운트
     * key별로 등장 횟수가 k일 때 조합 수 k * (k - 1) / 2
     *
     * substring 개수가 O(n²)이고, 각 substring 길이를 L이라 하면
     * 정렬 비용은 O(L log L)
     *
     * 전체 시간 복잡도는 O(n³ log n) 정도
     */
    class Result {

        /*
         * Complete the 'sherlockAndAnagrams' function below.
         *
         * The function is expected to return an INTEGER.
         * The function accepts STRING s as parameter.
         */

        public static int sherlockAndAnagrams(String s) {
            int n = s.length();
            Map<String, Integer> count = new HashMap<>();

            char[] target = s.toCharArray();
            for(int i=0;i<n;i++){
                for(int j=i;j<n;j++){
                    char[] temp = Arrays.copyOfRange(target,i,j+1);
                    Arrays.sort(temp);
                    count.merge(new String(temp),1,Integer::sum);
                }
            }
            int answer = 0;


            for(int c:count.values()){
                answer += c * (c-1) / 2;
            }
            return answer;
        }

    }
}

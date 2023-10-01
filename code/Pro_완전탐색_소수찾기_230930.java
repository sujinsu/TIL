import java.util.*;

/**
 * 순열 및 탐색 로직 더 공부하기
 */
public class Pro_완전탐색_소수찾기_230930 {
    boolean[] isPrime;

    public int solution(String numbers) {


        int[] nums = new int[numbers.length()];
        for (int i = 0; i < numbers.length(); i++) {
            nums[i] = Character.getNumericValue(numbers.charAt(i));
        }

        List<Integer> allCombinationsAndPermutations = generateAll(nums);

        int max = Collections.max(allCombinationsAndPermutations);
        isPrime(max);
        int answer = 0;
        for (Integer combo : allCombinationsAndPermutations) {
            if(isPrime[combo]){
                answer +=1;
            }
        }


        return answer;
    }
    public static List<Integer> generateAll(int[] nums) {
        Set<Integer> resultSet = new HashSet<>();
        generateCombinations(nums, "", 0, resultSet);
        return new ArrayList<>(resultSet);
    }

    private static void generateCombinations(int[] nums, String current, int start, Set<Integer> result) {
        if (!current.isEmpty()) {
            generatePermutations("", current, result);
        }
        for (int i = start; i < nums.length; i++) {
            generateCombinations(nums, current + nums[i], i + 1, result);
        }
    }

    private static void generatePermutations(String prefix, String remaining, Set<Integer> result) {
        if (remaining.length() == 0 && !prefix.isEmpty()) {
            result.add(Integer.parseInt(prefix));
        } else {
            for (int i = 0; i < remaining.length(); i++) {
                generatePermutations(
                        prefix + remaining.charAt(i),
                        remaining.substring(0, i) + remaining.substring(i + 1),
                        result
                );
            }
        }
    }

    // 소수를 판별하는 함수
    void isPrime(int num){
        isPrime =  new boolean[num+1];

        for(int i=2;i<=num;i++){
            isPrime[i] = true;
        }

        for(int i=2;i * i<=num;i++){
            if(isPrime[i]){
                for(int j=i * i; j <=num;j+=i){
                    isPrime[j] = false;
                }
            }
        }
    }
}

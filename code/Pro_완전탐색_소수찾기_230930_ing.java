import java.util.*;
public class Pro_완전탐색_소수찾기_230930_ing {
    public int solution(String numbers) {
        int answer = 0;

        int[] nums = new int[numbers.length()];
        for (int i = 0; i < numbers.length(); i++) {
            nums[i] = Character.getNumericValue(numbers.charAt(i));
        }

        List<Integer> allCombinationsAndPermutations = generateAll(nums);
        for (Integer combo : allCombinationsAndPermutations) {
            System.out.println(combo);
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
}

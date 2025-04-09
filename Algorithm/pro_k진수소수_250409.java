import java.util.*;
class Solution {

    public int solution(int n, int k) {
        String converted = getJinsu(n, k);

        return (int) Arrays.stream(converted.split("0"))
                .filter(s -> !s.isEmpty() && !s.equals("1")) // 빈 문자열, 1 제외
                .mapToLong(Long::parseLong)                 // int → long
                .filter(this::isPrime)                      // 소수 판별
                .count();                                   // 개수 세기
    }

    // 진수 변환
    String getJinsu(int n, int k) {
        StringBuilder sb = new StringBuilder();
        while (n >= k) {
            sb.append(n % k);
            n /= k;
        }
        sb.append(n);
        return sb.reverse().toString();
    }

    // 소수 판별 함수
    boolean isPrime(long num) {
        if (num < 2) return false;
        for (long i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
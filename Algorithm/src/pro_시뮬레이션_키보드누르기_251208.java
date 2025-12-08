import java.util.Map;
import java.util.Set;

public class pro_시뮬레이션_키보드누르기_251208 {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5};
        String hand = "right";
        String result = solution(numbers, hand);
        System.out.println("result = " + result);
    }


        private static final String LEFT = "L";
        private static final String RIGHT = "R";
        // 1, 3 차이면 거리 1
        // 2, 4, 6 차이면 거리 2
        // 5, 7, 9 차이면 거리 3
        // 8, 10 차이면 거리 4
        static final Map<Integer, Integer> distMap = Map.ofEntries(
                Map.entry(1, 1),
                Map.entry(3, 1),
                Map.entry(2, 2),
                Map.entry(4, 2),
                Map.entry(6, 2),
                Map.entry(5, 3),
                Map.entry(7, 3),
                Map.entry(9, 3),
                Map.entry(8, 4),
                Map.entry(10, 4),
                Map.entry(0, 0)
        );

    public static String solution(int[] numbers, String hand) {

        // * == 10, 0 = 11, # == `12 편의상 이렇게 정의.
        Set<Integer> leftSet = Set.of(1,4,7);
        Set<Integer> rightSet = Set.of(3,6,9);
        int left = 10, right =12; // 초기 문자 세팅
        StringBuilder sb  = new StringBuilder();
        for(int n:numbers){
            if(leftSet.contains(n)){
                sb.append(LEFT);
                left = n;
            }else if(rightSet.contains(n)){
                sb.append(RIGHT);
                right = n;
            }else{
                if(n == 0) n = 11;
                int leftDist = distMap.get(Math.abs(n-left));
                int rightDist = distMap.get(Math.abs(n-right));
                if(leftDist == rightDist){
                    if(hand.equals("right")){
                        sb.append(RIGHT);
                        right = n;
                    }else{
                        sb.append(LEFT);
                        left = n;
                    }

                }else if(leftDist < rightDist){
                    sb.append(LEFT);
                    left = n;
                }else{
                    sb.append(RIGHT);
                    right = n;
                }
            }
        }
        return sb.toString();
    }

}

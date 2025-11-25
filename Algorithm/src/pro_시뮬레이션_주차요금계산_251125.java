import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pro_시뮬레이션_주차요금계산_251125 {

    public static void main(String[] args) {
        int[] fees = {180, 5000, 10, 600}; // 기본시간, 기본요금, 단위시간, 단위요금
        String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};
        System.out.println(solution(fees, records));
    }

    public static List<Integer> solution(int[] fees, String[] records) {
        int baseTime = fees[0];
        int baseFee  = fees[1];
        int unitTime = fees[2];
        int unitFee  = fees[3];

        Map<Integer, List<Integer>> carMap = new HashMap<>();
        for(String r:records){
            String[] temp = r.split(" ");
            int carNum = Integer.parseInt(temp[1]);
            int timeMin = timeToMin(temp[0]);
            List<Integer> val = carMap.getOrDefault(carNum, new ArrayList<>());
            val.add(timeMin);
            carMap.put(carNum, val);
        }
        int[] carNums = carMap.keySet().stream().sorted().mapToInt(Integer::intValue).toArray(); // 작은 차 번호부터
        List<Integer> result = new ArrayList<>();
        int endOfDay = timeToMin("23:59");

        for(int c:carNums){
            List<Integer> times = carMap.get(c);
            if (times.size() % 2 == 1) {
                times.add(endOfDay);
            }
            // 총 주차 시간 계산 (짝수=IN, 홀수=OUT)
            int totalMin = 0;
            for (int i = 0; i < times.size(); i += 2) {
                int in  = times.get(i);
                int out = times.get(i + 1);
                totalMin += (out - in);
            }
            // 요금계산
            int fee;
            if (totalMin <= baseTime) {
                fee = baseFee;
            } else {
                int extra = totalMin - baseTime;
                int units = (extra + unitTime - 1) / unitTime; // 올림
                fee = baseFee + units * unitFee;
            }
            result.add(fee);
        }
        return result;
    }

    static int timeToMin(String time){
        String[] temp = time.split(":");
        return Integer.parseInt(temp[0]) * 60 + Integer.parseInt(temp[1]);
    }
}

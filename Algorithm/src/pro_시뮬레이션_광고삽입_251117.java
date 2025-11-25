import java.time.LocalTime;

public class pro_시뮬레이션_광고삽입_251117 {
    public static void main(String[] args) {
        String play_time="02:03:55";
        String adv_time="00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};
        System.out.println(solution(play_time, adv_time,logs));
    }


    public static String solution(String play_time, String adv_time, String[] logs) {
        if(play_time.equals(adv_time)) return "00:00:00";

        // 구간 세팅
        int playSec = computeTimeToSecond(play_time);
        int advSec = computeTimeToSecond(adv_time);
        int n = playSec + 2;
        
        int[] view = new int[n];
        int[] diff = new int[n];

        for(int i=0;i<logs.length;i++){
            String[] split = logs[i].split("-");
            diff[computeTimeToSecond(split[0])]++;
            diff[computeTimeToSecond(split[1])]--;
        }
        // 시청자 수 타임라인 만들기
        view[0] = diff[0];
        for (int i = 1; i < n; i++) {
            view[i] = view[i - 1] + diff[i];
        }
        // advSec짜리 구간 합이 최대인 start 찾기
        long[] acc = new long[n];
        acc[0] = view[0];
        for (int i = 1; i < n; i++) {
            acc[i] = acc[i - 1] + view[i];
        }

        // [0, advSec) 구간 먼저 계산
        long bestSum = acc[advSec - 1]; // view[0..advSec-1] 합
        int bestStart = 0;
        for (int start = 1; start + advSec <= playSec; start++) {
            int end = start + advSec;
            long cur = acc[end-1] - acc[start-1]; // [start, end) 구간 시청 시간 = acc[end-1] - acc[start-1] 이런 식
            if (cur > bestSum) {
                bestSum = cur;
                bestStart = start;
            }
        }
        return computeSecondToTime(bestStart);
    }

    static int computeTimeToSecond(String log){
        String[] logArr = log.split(":");
        int result = Integer.parseInt(logArr[0]) * 3600 +  Integer.parseInt(logArr[1]) * 60 +  Integer.parseInt(logArr[2]);
        return result;
    }

    static String computeSecondToTime(long second) {
        long h = second / 3600;
        second %= 3600;
        long m = second / 60;
        long s = second % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }


}

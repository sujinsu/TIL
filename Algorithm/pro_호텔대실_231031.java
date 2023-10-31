class Solution {
    public int solution(String[][] book_time) {
        int[] count = new int[1450]; // 하루는 1440분

        for (String[] bt : book_time) {
            int start = convertToIntMins(bt[0]);
            int end = convertToIntMins(bt[1]) + 10; // 청소시간을 포함
            count[start] += 1;
            count[end] -= 1;
        }

        int currentCount = 0; // 현재 시간대에 필요한 객실 수
        int maxCount = 0; // 필요한 최대 객실 수
        for (int i = 0; i < 1440; i++) {
            currentCount += count[i];
            maxCount = Math.max(maxCount, currentCount);
        }

        return maxCount;
    }

    int convertToIntMins(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int mins = Integer.parseInt(parts[1]);
        return hours * 60 + mins;  // 분으로 변환
    }
//     public int solution(String[][] book_time) {


//         int[] count = new int[2410];
//         for(String[] bt:book_time){
//             int start = convertInt(bt[0]);
//             int end = convertInt(bt[1]);
//             for(int i=start;i<end+10;i++){
//                 count[i] += 1;
//             }

//         }
//         int answer = 0;
//         for(int i=0;i<240;i++){

//             answer = Math.max(answer, count[i]);

//         }
//         return answer;
//     }

//     int convertInt(String time){

//         return Integer.parseInt(time.replace(":",""));
//     }
}
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
     public int myFirstSolution(String[][] book_time) {


         int[] count = new int[1450];
         for(String[] bt:book_time){
             int start = convertToIntMins(bt[0]);
             int end = convertToIntMins(bt[1]);
             for(int i=start;i<end+10;i++){
                 count[i] += 1;
             }

         }
         int answer = 0;
         for(int i=0;i<1440;i++){

             answer = Math.max(answer, count[i]);

         }
         return answer;
     }


//     int convertInt(String time){
//
//         return Integer.parseInt(time.replace(":",""));
//    }
    /**
     * ChatGPT : 우선순위 큐
     */
    public int chatGPTsolution(String[][] book_time) {
        // 각 예약의 시작 시간과 종료 시간을 분 단위의 숫자로 변환하여 저장할 2차원 배열을 선언합니다.
        int[][] times = new int[book_time.length][2];
        for (int i = 0; i < book_time.length; i++) {
            // 시작 시간을 분 단위로 변환하여 저장합니다.
            times[i][0] = convertToMinutes(book_time[i][0]);
            // 종료 시간을 분 단위로 변환하고, 10분(청소 시간)을 추가하여 저장합니다.
            times[i][1] = convertToMinutes(book_time[i][1]) + 10;
        }

        // 시작 시간을 기준으로 예약을 정렬합니다.
        Arrays.sort(times, (a, b) -> a[0] - b[0]);

        // 객실의 청소 완료 시간을 관리하기 위한 우선순위 큐를 선언합니다.
        PriorityQueue<Integer> endTimeQueue = new PriorityQueue<>();
        int rooms = 0; // 필요한 객실 수를 저장할 변수를 선언합니다.

        for (int[] time : times) {
            // 청소 완료 시간이 저장된 큐가 비어 있지 않고, 현재 예약의 시작 시간이 큐의 첫 번째 청소 완료 시간보다 늦다면,
            // 해당 객실을 사용할 수 있습니다.
            if (!endTimeQueue.isEmpty() && endTimeQueue.peek() <= time[0]) {
                endTimeQueue.poll(); // 큐에서 청소 완료 시간을 제거합니다.
            } else {
                // 사용 가능한 객실이 없다면 새로운 객실을 사용합니다.
                rooms++;
            }
            // 현재 예약의 청소 완료 시간을 큐에 추가합니다.
            endTimeQueue.add(time[1]);
        }

        return rooms; // 필요한 최소 객실 수를 반환합니다.
    }

    // "HH:MM" 형식의 문자열을 분 단위의 숫자로 변환하는 함수입니다.
    public int convertToMinutes(String time) {
        String[] split = time.split(":"); // 문자열을 ":"로 분리하여 시와 분을 구분합니다.
        return Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]); // 시를 60으로 곱하여 분으로 변환하고, 분을 더하여 결과를 반환합니다.
    }


}
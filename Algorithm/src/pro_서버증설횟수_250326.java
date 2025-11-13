class pro_서버증설횟수_250326 {
    public static void main(String[] args){

    }
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int time = players.length;
        // 각 시간대 별 서버의 수를 카운트할 배열
        int[] serverCnt = new int[time];

        for(int i=0;i<time;i++){
            int addServerCnt = (players[i] / m) - serverCnt[i];
            // System.out.println("Time : " + i + ", serverCnt : " + serverCnt[i] + ", players[i] / m : "+ players[i] / m);
            if(addServerCnt <= 0) continue;
            // System.out.println("addServerCnt : "+ addServerCnt);
            answer += addServerCnt;
            for (int j = i; j < i+k && j < time; j++) serverCnt[j] += addServerCnt;
        }
        return answer;
    }
}
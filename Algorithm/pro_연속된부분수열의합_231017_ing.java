// TODO 다시 풀기
public class pro_연속된부분수열의합_231017_ing {
    public int[] solution(int[] sequence, int k) {

        int startIdx=0;
        int endIdx=0;
        int len = sequence.length;
        int sum ;

        for(int i=0;i<sequence.length;i++){
            sum = 0;

            for(int j=i;j<sequence.length;j++){
                sum += sequence[j];
                if(sum == k && len > (j-i + 1)){
                    startIdx = i;
                    endIdx = j;
                    len = j-i+1;

                }else if(sum > k){
                    break;
                }
            }
        }
        int[] answer = {startIdx, endIdx};
        return answer;
    }
}

public class pro_카드뭉치_231016 {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        int idx1 = 0; // card1의 idx
        int idx2 = 0; // card2의 idx
        for(String g:goal){
            if(idx1 < cards1.length && cards1[idx1].equals(g)){
                idx1++;
                continue;
            }
            if(idx2 < cards2.length && cards2[idx2].equals(g)){
                idx2++;
                continue;
            }
            answer = "No";
            break;
        }
        return answer;
    }
}

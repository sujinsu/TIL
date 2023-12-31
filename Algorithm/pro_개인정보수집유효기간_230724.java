/**
 * <문제>
 *
 * 고객의 약관 동의를 얻어서 수집된 1~n번으로 분류되는 개인정보 n개가 있습니다. 약관 종류는 여러 가지 있으며 각 약관마다 개인정보 보관 유효기간이 정해져 있습니다. 당신은 각 개인정보가 어떤 약관으로 수집됐는지 알고 있습니다. 수집된 개인정보는 유효기간 전까지만 보관 가능하며, 유효기간이 지났다면 반드시 파기해야 합니다.
 *
 * 예를 들어, A라는 약관의 유효기간이 12 달이고, 2021년 1월 5일에 수집된 개인정보가 A약관으로 수집되었다면 해당 개인정보는 2022년 1월 4일까지 보관 가능하며 2022년 1월 5일부터 파기해야 할 개인정보입니다.
 * 당신은 오늘 날짜로 파기해야 할 개인정보 번호들을 구하려 합니다.
 *
 * 모든 달은 28일까지 있다고 가정합니다.
 *
 * 다음은 오늘 날짜가 2022.05.19일 때의 예시입니다.
 *
 * 약관 종류	유효기간
 * A	6 달
 * B	12 달
 * C	3 달
 * 번호	개인정보 수집 일자	약관 종류
 * 1	2021.05.02	A
 * 2	2021.07.01	B
 * 3	2022.02.19	C
 * 4	2022.02.20	C
 *
 * 첫 번째 개인정보는 A약관에 의해 2021년 11월 1일까지 보관 가능하며, 유효기간이 지났으므로 파기해야 할 개인정보입니다.
 * 두 번째 개인정보는 B약관에 의해 2022년 6월 28일까지 보관 가능하며, 유효기간이 지나지 않았으므로 아직 보관 가능합니다.
 * 세 번째 개인정보는 C약관에 의해 2022년 5월 18일까지 보관 가능하며, 유효기간이 지났으므로 파기해야 할 개인정보입니다.
 * 네 번째 개인정보는 C약관에 의해 2022년 5월 19일까지 보관 가능하며, 유효기간이 지나지 않았으므로 아직 보관 가능합니다.
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 년 월 일 -> 일로 모두 변환 혹은 LocalDate 이용하여 간단한 풀이 가능
 */
public class pro_개인정보수집유효기간_230724 {

    public List<Integer> solution(String today, String[] terms, String[] privacies) {
        // int[] answer = {};
        List<Integer> answer = new ArrayList<>();

        Map<String,Integer> term = new HashMap<>();
        for (int i =0 ;i < terms.length;i++){
            String[] temp = terms[i].split(" ");
            term.put(temp[0],Integer.parseInt(temp[1]));
        }

        int p_len = privacies.length;
        for (int i=0;i<p_len;i++){
            String key = Character.toString(privacies[i].charAt(privacies[i].length() -1));

            int month = 0;
            String p = null;
            if(term.containsKey(key)){
                month = term.get(key);
                p = privacies[i].substring(0, privacies[i].length() - 2);
            }
            System.out.println("key = "+key+" month = "+ month);
            Boolean check = false;
            check = compare(month, p, today);

            if(check){
                answer.add(i+1);
            }
        }

        return answer;
    }

    boolean compare(int month, String pv, String today){
        if (pv == null || today == null){
            return false;
        }

        // p 에서 month 만큼 더하며today와 비교

        int[] p = Arrays.stream(pv.split("\\.")).mapToInt(Integer::parseInt)
                .toArray();
        int[] t = Arrays.stream(today.split("\\.")).mapToInt(Integer::parseInt)
                .toArray();

        p[2] -= 1;
        if((p[1]+month) >= 12){

            p[0] += (p[1]+month)/12;
            p[1] = (p[1]+month)%12;
        }else{
            p[1] += month;
        }

        if(p[2] == 0){
            p[1] -= 1;
            p[2] = 28;
        }
        if(p[1] == 0){
            p[0] -= 1;
            p[1] = 12;
        }

        // year 비교
        if(t[0]>p[0]){
            return true; // 파기해야함
        }else if(t[0]<p[0]){
            return false; // 파기 안함
        }
        // month 비교
        if(t[1] > p[1]){
            return true;
        }else if(t[1] < p[1]){
            return false;
        }

        // day 비교 , 28일까지라고 가정
        if(t[2]>p[2]){
            return true;
        }else{
            return false;
        }
    }
}

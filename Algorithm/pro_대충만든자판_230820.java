import java.util.*;
public class pro_대충만든자판_230820 {
    public List<Integer> solution(String[] keymap, String[] targets) {
        List<Integer> answer = new ArrayList<>();
        Map<Character,Integer> count = new HashMap<>();

        // count Map에 있는지, 확인하고 최소값 업데이트
        for(int i=0; i<keymap.length; i++){
            String target = keymap[i];
            for(int j=0; j< target.length();j++){
                char character = target.charAt(j);
                if (!count.containsKey(character)){
                    count.put(character, j+1);
                }else{
                    int cnt = count.get(character);
                    if(cnt > (j + 1)){
                        count.put(character, j+1);
                    }
                }
            }
        }
        for(int i=0; i<targets.length; i++){
            String target = targets[i];
            int sum = 0;
            for(int j=0; j< target.length();j++){
                char character = target.charAt(j);
                if (!count.containsKey(character)){
                    sum = -1;
                    break;
                }else{
                    sum += count.get(character);
                }
            }
            answer.add(sum);
        }
        System.out.println(count);

        return answer;
    }
}

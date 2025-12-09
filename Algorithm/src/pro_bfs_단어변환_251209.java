/**
 * 나는 dfs로 풀었지만 최단변환 횟수이니만큼 bfs가 더 적절
 */
public class pro_bfs_단어변환_251209 {
    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";
        String[] words= {"hot", "dot", "dog", "lot", "log", "cog"};
        int solution = solution(begin, target, words);
        System.out.println("solution = " + solution);
    }
    static int ans = Integer.MAX_VALUE;
    static boolean canMakeAns = false;
    public static int solution(String begin, String target, String[] words) {
        // 엣지케이스
        boolean isPossible = false;
        for(String w:words){
            if(w.equals(target)){
                isPossible = true;
                break;
            }
        }
        if(!isPossible) return 0;

        int N = words.length;
        dfs(new boolean[N], words, begin, target, 0);
        return canMakeAns ? ans : 0;
    }
    static void dfs(boolean[] visited, String[] words, String now, String target, int cnt){
        if (cnt >= ans) return;   // 가지치기

        if(now.equals(target)){
            canMakeAns = true;
            ans = Math.min(ans, cnt);
        }
        for(int i=0;i<visited.length;i++){
            if(!visited[i] && canChange(now, words[i])){
                visited[i] = true;
                dfs(visited, words,words[i], target, cnt+1);
                visited[i] = false;
            }
        }
    }

    // 글자 하나만 다를 때 true 반환
    static boolean canChange(String now, String target){
        boolean notMatch = false;
        for(int i=0;i<target.length();i++){
            if(now.charAt(i) != target.charAt(i)){
                if(notMatch){ // 이미 다른 문자 나온 적 있음
                    return false;
                }else{
                    notMatch = true;
                }
            }
        }
        return true;
    }

}

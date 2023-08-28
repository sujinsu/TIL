import java.util.*;

/**
 * 지도를 나타내는 문자열 배열 maps가 매개변수로 주어질 때,
 * 각 섬에서 최대 며칠씩 머무를 수 있는지 배열에 오름차순으로 담아 return 하는 solution 함수를 완성해주세요.
 * 만약 지낼 수 있는 무인도가 없다면 -1을 배열에 담아 return 해주세요.
 */
public class pro_무인도여행_230820 {
    boolean[][] visited;
    String[][] data;
    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    static int total_row;
    static int total_col;

    public List<Integer> solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();

        total_row = maps.length;
        total_col = maps[0].length();

        data = new String[total_row][total_col];
        visited = new boolean[total_row][total_col];

        // 초기 값 세팅
        for(int i=0;i<total_row;i++){
            String target = maps[i];
            for(int j=0;j<total_col;j++){
                data[i][j] = String.valueOf(target.charAt(j));
            }
        }

        // visited = false 인 곳 중, X가 아닌 곳을 만나면 bfs 순회한다.
        for(int i=0;i<total_row;i++){
            for(int j=0;j<total_col;j++){
                if(!data[i][j].equals("X") && !visited[i][j]){
                    answer.add(bfs(i,j));
                };
            }
        }

        if(answer.isEmpty()){
            answer.add(-1);
        }
        Collections.sort(answer);
        return answer;
    }

    int bfs(int row, int col){
        int sum = Integer.parseInt(data[row][col]);
        visited[row][col] = true;

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(row);
        queue.offer(col);

        while(!queue.isEmpty()){
            int cul_row = queue.poll();
            int cul_col = queue.poll();

            for(int i=0;i<4;i++){
                int next_row = cul_row + dr[i];
                int next_col = cul_col + dc[i];
                // System.out.println("다음 위치는  (" + next_row + ", " + next_col + ")");
                // 범위 안, 'X' 아닌 곳, 아직 방문하지 않은 곳
                if(0 <= next_row && next_row < total_row
                        && 0 <= next_col && next_col < total_col
                        && !data[next_row][next_col].equals("X")
                        && !visited[next_row][next_col]){

                    visited[next_row][next_col] = true;
                    sum += Integer.parseInt(data[next_row][next_col]);
                    queue.offer(next_row);
                    queue.offer(next_col);
                }
            }

        }

        return sum;
    }
}

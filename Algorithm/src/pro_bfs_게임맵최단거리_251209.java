import java.util.ArrayDeque;
import java.util.Deque;

public class pro_bfs_게임맵최단거리_251209 {
    public static void main(String[] args) {
        int[][] maps = {{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}};
        int solution = solution(maps);
        System.out.println("solution = " + solution);
    }
    static final int[] DR = {-1,0,1,0};
    static final int[] DC = {0,1,0,-1};
    public static int solution(int[][] maps) {
        int R = maps.length;
        int C = maps[0].length;
        boolean[][] visited = new boolean[R][C];
        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0,1));
        visited[0][0] = true;

        while(!q.isEmpty()){
            Node cur  = q.poll();
            if(cur.row == R-1 && cur.col == C-1){
                return cur.dist;
            }
            for(int i=0;i<4;i++){
                int nr = cur.row + DR[i];
                int nc = cur.col + DC[i];
                if(nr < 0 || nr >= R || nc < 0 || nc >= C
                        || maps[nr][nc] == 0 || visited[nr][nc] ) continue;
                visited[nr][nc] = true;
                q.add(new Node(nr,nc,cur.dist+1));
            }
        }
        return -1;
    }

    static class Node{
        int row;
        int col;
        int dist;
        Node(int row, int col, int dist){
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }
}

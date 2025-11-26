import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class pro_하이브리드탐색_경주로건설_251126 {

    public static void main(String[] args) {
        int[][] board = {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}};
        System.out.println(solution(board));
    }
    static final int[] dr = {-1,0,1,0};
    static final int[] dc = {0,1,0,-1};

    static final int STRAIGHT_COST = 100;
    static final int CURVE_COST = 500;

    public static int solution(int[][] board) {
        int n = board.length;

        int[][][] cost = new int[n][n][4]; // [row][col][dir] 에 도착할 때의 최소 비용
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                Arrays.fill(cost[r][c], Integer.MAX_VALUE);
            }
        }
        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(0,0,-1,0));

        int minCost = Integer.MAX_VALUE;

        while(!q.isEmpty()){
            Node cur = q.pollFirst();
            if (cur.dir != -1 && cur.cost > cost[cur.row][cur.col][cur.dir]) continue;
            if(cur.row == n-1 && cur.col == n-1){
                minCost = Math.min(minCost, cur.cost);
            }
            for(int i=0;i<4;i++){
                int nr = cur.row + dr[i];
                int nc = cur.col + dc[i];
                if(nr>=0 && nr < n && nc >= 0  && nc < n && board[nr][nc] == 0 ){
                    int nextCost = cur.cost + computeCost(cur.dir, i);
                    if(nextCost < cost[nr][nc][i]) {
                        cost[nr][nc][i] = nextCost;
                        q.add(new Node(nr,nc,i,nextCost));
                    }
                }
            }
        }


        return minCost;
    }

    /**
     * // 0 상 1 우 2 하 3 좌 -> 방향 차이가 짝수면 직선도로
     * @param preDir
     * @param nextDir
     * @return
     */
    static int computeCost(int preDir, int nextDir){
        if(preDir == -1 || preDir == nextDir) return STRAIGHT_COST;
        return CURVE_COST + STRAIGHT_COST;
    }

    static class Node{
        int row;
        int col;
        int dir; // 0 상 1 우 2 하 3 좌

        int cost;

        Node(int row, int col, int dir, int cost){
            this.row =row;
            this.col =col;
            this.dir = dir;
            this.cost = cost;
        }
    }

}

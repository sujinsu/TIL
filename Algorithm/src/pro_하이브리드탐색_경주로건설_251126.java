import java.util.*;

/**
 * 3차원 cost로 상태관리
 * BUT 다익스트라로 접근하는 것이 정석
 * PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
 */
public class pro_하이브리드탐색_경주로건설_251126 {

    public static void main(String[] args) {
        int[][] board = {{0, 0, 0, 0, 0, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 0, 1}, {0, 0, 1, 0, 0, 0, 1, 0}, {0, 1, 0, 0, 0, 1, 0, 0}, {1, 0, 0, 0, 0, 0, 0, 0}};
        System.out.println(solution(board));
    }

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};

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
        q.add(new Node(0, 0, -1, 0));

        int minCost = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            Node cur = q.pollFirst();
            if (cur.dir != -1 && cur.cost > cost[cur.row][cur.col][cur.dir]) continue;
            if (cur.row == n - 1 && cur.col == n - 1) {
                minCost = Math.min(minCost, cur.cost);
            }
            for (int i = 0; i < 4; i++) {
                int nr = cur.row + dr[i];
                int nc = cur.col + dc[i];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && board[nr][nc] == 0) {
                    int nextCost = cur.cost + computeCost(cur.dir, i);
                    if (nextCost < cost[nr][nc][i]) {
                        cost[nr][nc][i] = nextCost;
                        q.add(new Node(nr, nc, i, nextCost));
                    }
                }
            }
        }


        return minCost;
    }

    /**
     * // 0 상 1 우 2 하 3 좌 -> 방향 차이가 짝수면 직선도로
     *
     * @param preDir
     * @param nextDir
     * @return
     */
    static int computeCost(int preDir, int nextDir) {
        if (preDir == -1 || preDir == nextDir) return STRAIGHT_COST;
        return CURVE_COST + STRAIGHT_COST;
    }

    static class Node {
        int row;
        int col;
        int dir; // 0 상 1 우 2 하 3 좌

        int cost;

        Node(int row, int col, int dir, int cost) {
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.cost = cost;
        }
    }
}
//        private static final int INF = 1_000_000_000;
//    public int solution(int[][] board) {
//        int n = board.length;
//
//        // cost[r][c][dir] = (r,c)에 dir 방향으로 도착했을 때의 최소 비용
//        int[][][] cost = new int[n][n][4];
//        for (int r = 0; r < n; r++) {
//            for (int c = 0; c < n; c++) {
//                Arrays.fill(cost[r][c], INF);
//            }
//        }
//
//        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
//
//        // 시작점 (0,0) 에서 출발: 오른쪽/아래로만 첫 도로를 놓을 수 있음
//        for (int dir = 1; dir <= 2; dir++) { // 1: 우, 2: 하
//            int nr = 0 +  dr[dir];
//            int nc = 0 + dc[dir];
//            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
//            if (board[nr][nc] == 1) continue; // 벽이면 못 감
//            cost[nr][nc][dir] = STRAIGHT_COST;
//            pq.add(new Node(nr, nc, dir, STRAIGHT_COST));
//        }
//
//        int answer = INF;
//
//        while (!pq.isEmpty()) {
//            Node cur = pq.poll();
//
//            // 이미 더 싼 비용으로 처리된 상태면 스킵 (stale 노드)
//            if (cur.cost > cost[cur.row][cur.col][cur.dir]) continue;
//
//            // 도착점에 도달하면 현재 비용이 그 방향으로의 최단 비용
//            if (cur.row == n - 1 && cur.col == n - 1) {
//                answer = Math.min(answer, cur.cost);
//                // 다익스트라 특성상 여기서 바로 return 해도 되지만
//                // 혹시 더 싼 방향이 남아있을 수도 있으니 min만 갱신하고 계속 진행해도 OK
//            }
//
//            for (int nd = 0; nd < 4; nd++) {
//                int nr = cur.row + dr[nd];
//                int nc = cur.col + dc[nd];
//                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
//                if (board[nr][nc] == 1) continue; // 벽
//
//                int nextCost = cur.cost + getMoveCost(cur.dir, nd);
//
//                if (nextCost < cost[nr][nc][nd]) {
//                    cost[nr][nc][nd] = nextCost;
//                    pq.add(new Node(nr, nc, nd, nextCost));
//                }
//            }
//        }
//
//        // 도착점에 대해 4방향 중 최소 비용 선택
//        for (int d = 0; d < 4; d++) {
//            answer = Math.min(answer, cost[n - 1][n - 1][d]);
//        }
//
//        return answer;
//    }
//
//    // 직선 vs 코너 비용 계산
//    private int getMoveCost(int prevDir, int nextDir) {
//        if (prevDir == nextDir) {
//            return STRAIGHT_COST;
//        } else {
//            return STRAIGHT_COST + CURVE_COST;
//        }
//    }}
//



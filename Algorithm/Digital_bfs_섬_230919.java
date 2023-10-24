import java.util.*;
public class Digital_bfs_섬_230919 {
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        boolean[][] island = new boolean[N][M];
        visited = new boolean[N][M];
        for(int i=0;i<K;i++){
            int r = sc.nextInt();
            int c = sc.nextInt();
            island[r][c] = true;
        }

        int count = 0;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(island[i][j] && !visited[i][j]){
                    bfs(i, j, N, M, island);
                    count += 1;
                }
            }
        }
        System.out.println(count);
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Point{
        int row;
        int col;

        Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    static void bfs(int row, int col, int N, int M, boolean[][] island){
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(row,col));
        visited[row][col] = true;

        while(!queue.isEmpty()){
            Point current = queue.poll();
            int curRow = current.row;
            int curCol = current.col;

            for(int i=0;i<4;i++){
                int tempRow = curRow + dr[i];
                int tempCol = curCol + dc[i];

                // 범위 안, 방문하지 않은 곳, 구멍 뚫린 곳
                if(tempRow >= 0 && tempRow < N && tempCol >= 0 && tempCol < M && !visited[tempRow][tempCol] && island[tempRow][tempCol]){
                    visited[tempRow][tempCol] = true;
                    queue.add(new Point(tempRow,tempCol));
                }
            }
        }
    }
}

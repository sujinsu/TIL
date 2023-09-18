import java.util.*;
public class Digital_dfs_지진_230919 {
    static boolean[][] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        boolean[][] hole = new boolean[N][M];
        visited = new boolean[N][M];
        for(int i=0;i<K;i++){
            int r = sc.nextInt()-1;
            int c = sc.nextInt()-1;
            hole[r][c] = true;
        }

        int bigHole = 0;

        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(hole[i][j] && !visited[i][j]){
                    int temp = dfs(i, j, N, M, hole);
                    if(bigHole < temp) bigHole = temp;
                }

                // System.out.print(hole[i][j]+" ");
            }
            // System.out.println();
        }
        System.out.println(bigHole);
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

    static int dfs(int row, int col, int N, int M, boolean[][] hole){
        int answer = 0;


        Stack<Point> stack = new Stack<>();
        stack.push(new Point(row,col));
        visited[row][col] = true;
        answer += 1;

        while(!stack.isEmpty()){
            Point current = stack.pop();
            int curRow = current.row;
            int curCol = current.col;

            for(int i=0;i<4;i++){
                int tempRow = curRow + dr[i];
                int tempCol = curCol + dc[i];

                // 범위 안, 방문하지 않은 곳, 구멍 뚫린 곳
                if(tempRow >= 0 && tempRow < N && tempCol >= 0 && tempCol < M && !visited[tempRow][tempCol] && hole[tempRow][tempCol]){
                    visited[tempRow][tempCol] = true;
                    stack.push(new Point(tempRow,tempCol));
                    answer += 1;
                }
            }
        }
        return answer;
    }
}

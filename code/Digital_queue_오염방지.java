import java.util.*;
public class Digital_queue_오염방지 {

    static int[] dc = {-1, 1, 0, 0}; // 상하좌우 이동
    static int[] dr = {0, 0, -1, 1};

    static class Point{
        int row;
        int col;

        Point(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        sc.nextLine();

        // 2차월 배열 생성
        char[][] grid = new char[N][M];

        // 그 다음 N 줄 동안 2차원 배열의 내용을 입력 받음
        for (int i = 0; i < N; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < M; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        int answer = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(grid[i][j] == '.'){
                    int tmp = bfs(grid,N,M,i,j);
                    if(answer < tmp){
                        answer = tmp;
                    }
                }
            }
        }

        System.out.println(answer);

        // start 지점을 바꿔가며 아래의 과정 반복 (1 ~3) 하며 o개수 체크 ->  최대 o 개수 구하기
        // 1. queue 에 오염수, 방지제의 인덱스를 넣어두기
        // 2. 매시간 상하좌우 돌려서 visited 배열에 표시 && queue에 넣기
        // 2-1. 오염수 x, 오염방지제 o 로 표시
        // 3. queue 가 빌때까지 반복
    }

    static int bfs(char[][] grid, int N, int M, int startRow, int startCol){


        Queue<Point> q = new LinkedList<>();

        // 오염방지제 0, 오염수 1
        Integer[][] visited = new Integer[N][M];


        visited[startRow][startCol] = 0;
        q.add(new Point(startRow, startCol));


        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(grid[i][j] == 'x'){
                    q.add(new Point(i, j));
                    visited[i][j] =  1;
                }
            }
        }


        while(!q.isEmpty()){
            Point cur = q.poll();

            for(int i=0; i<4; i++){
                int curRow = cur.row + dr[i];
                int curCol = cur.col + dc[i];

                // 아직 방문한 적 없고, 오염물 혹은 오염방지제가 들어가지 않은 칸
                if(curRow >=0 && curRow < N && curCol >=0 && curCol < M
                        && visited[curRow][curCol] == null){
                    visited[curRow][curCol] = visited[cur.row][cur.col];
                    q.add(new Point(curRow, curCol));
                }
            }
        }
        int result = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(visited[i][j] == 0) result++;
            }
        }

        return result;
    }
}

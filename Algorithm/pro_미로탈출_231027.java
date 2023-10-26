import java.util.*;
public class pro_미로탈출_231027 {
    public int solution(String[] maps) {
        int answer = 0;
        int height = maps.length;
        int width = maps[0].length();

        Point start = null, lever = null, exit = null;

        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(maps[i].charAt(j) == 'S'){
                    start = new Point(i,j,0);
                }else if(maps[i].charAt(j) == 'L'){
                    lever = new Point(i,j,0);
                }else if(maps[i].charAt(j) == 'E'){
                    exit = new Point(i,j,0);
                }
            }
        }

        int fromStart = bfs(maps, height,width, start, lever);
        if(fromStart == -1)return -1;
        int fromLever = bfs(maps, height,width, lever, exit);
        if(fromLever == -1)return -1;
        return fromStart + fromLever;
    }
    class Point{
        int row;
        int col;
        int cnt;

        Point(int row, int col, int cnt){
            this.row = row;
            this.col = col;
            this.cnt = cnt;
        }
    }
    int[] dr = {-1,0,1,0};
    int[] dc = {0,1,0,-1};

    int bfs(String[] maps, int N, int M, Point start, Point target){
        boolean[][] visited = new boolean[N][M];
        visited[start.row][start.col] = true;

        Queue<Point> queue =  new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            Point cur = queue.poll();

            if(cur.row == target.row && cur.col == target.col){
                return cur.cnt;
            }
            for(int i=0;i<4;i++){
                int row  = cur.row + dr[i];
                int col = cur.col + dc[i];

                if(row >= 0 && row < N && col >= 0 && col < M
                        && !visited[row][col] && maps[row].charAt(col) !='X'){
                    queue.add(new Point(row, col, cur.cnt + 1));
                    visited[row][col] = true;
                }
            }
        }
        return -1;
    }
}

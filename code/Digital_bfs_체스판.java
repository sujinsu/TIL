package code;
import java.util.*;
import java.util.Scanner;
public class Digital_bfs_체스판 {

    static class Point{
        int row;
        int col;
        int count;

        Point(int row, int col, int count){
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }

    static int[] dr = {-2,-2,-1,1,2,2,1,-1};
    static int[] dc = {-1,1,2,2,1,-1,-2,-2};

    static int shortestDistance(int N,int M,int startX,int startY,int targetX,int targetY){

        // bfs 로 풀기
        boolean[][] visited = new boolean[N][M]; // 이거 왜 구분해서 안알려줌?;

        Queue<Point> queue = new LinkedList<>();

        visited[startX][startY] = true;
        queue.add(new Point(startX,startY,0));

        while(!queue.isEmpty()){
            Point current = queue.poll();
            for(int i=0;i<8;i++){
                int row = current.row + dr[i];
                int col = current.col + dc[i];
                if(row >= 0 && row < N && col>=0 && col < M && !visited[row][col]){
                    if(row==targetX && col ==targetY){
                        return ++current.count;
                    }
                    visited[row][col] = true;
                    queue.add(new Point(row,col,current.count + 1));
                }
            }

        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 체스판 크기 입력 받기
        int N = scanner.nextInt();
        int M = scanner.nextInt();

        // 시작 좌표 입력 받기
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();

        // 목적지 좌표 입력 받기
        int targetX = scanner.nextInt();
        int targetY = scanner.nextInt();

        // 최단 거리 계산
        int distance = shortestDistance(N, M, startX, startY, targetX, targetY);

        // 결과 출력
        System.out.println(distance);

        scanner.close();
    }


}

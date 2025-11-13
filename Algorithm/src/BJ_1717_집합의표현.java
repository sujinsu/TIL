import java.util.Scanner;

public class BJ_1717_집합의표현 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        boolean[][] grid = new boolean[N][N];

        for(int i=0;i<M;i++){
            int operation = sc.nextInt();  // 연산 종류 (0 또는 1)
            int x = sc.nextInt();
            int y = sc.nextInt();
            if(operation == 0){
                grid[x][y] = true;
                grid[y][x] = true;
                addZipHap(grid,x,y);
            }else{
                checkZipHap(grid,x,y);
            }
        }
    }

    static void addZipHap(boolean[][] grid, int x, int y){
        for(int i=0;i<grid.length;i++){
            if(grid[x][i] || grid[y][i]){
                grid[i][x] = true;
                grid[i][y] = true;
            }
        }
    }

    static void checkZipHap(boolean[][] grid, int x, int y){
        if(grid[x][y]){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }
}

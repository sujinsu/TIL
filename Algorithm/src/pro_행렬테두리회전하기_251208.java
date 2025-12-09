import java.util.ArrayList;
import java.util.List;

public class pro_행렬테두리회전하기_251208 {
    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
        List<Integer> result = solution(rows, columns, queries);
        System.out.println("result = " + result);
    }
    static int[][] data;
    static final int[] DR = {0,1,0,-1};
    static final int[] DC = {1,0,-1,0};

    // x = row, y = col
    public static List<Integer> solution(int rows, int columns, int[][] queries) {
        setting(rows, columns);
        List<Integer> ans =  new ArrayList<>();
        for(int[] q:queries){
            int x1 = q[0]-1;
            int y1 = q[1]-1;
            int x2 = q[2]-1;
            int y2 = q[3]-1;
            int result = rotateValAndFindMinVal(x1, y1, x2, y2, rows, columns);
            ans.add(result);
        }
        return ans;

    }



    private static int rotateValAndFindMinVal(int x1,int y1, int x2, int y2, int row, int col){
        int prev = data[x1][y1];
        int min = prev;

        for(int c=y1+1;c<=y2;c++){
            int temp = data[x1][c];
            data[x1][c] = prev;
            prev = temp;
            min = Math.min(prev,min);
        }

        for(int r=x1+1;r<=x2;r++){
            int temp = data[r][y2];
            data[r][y2] = prev;
            prev = temp;
            min = Math.min(prev,min);
        }

        for(int c=y2-1;c>=y1;c--){
            int temp = data[x2][c];
            data[x2][c] = prev;
            prev = temp;
            min = Math.min(prev,min);
        }

        for(int r=x2-1;r>=x1;r--){
            int temp = data[r][y1];
            data[r][y1] = prev;
            prev = temp;
            min = Math.min(prev,min);
        }
        return min;
    }

    /**
     * 초기 행렬 값 세팅
     */
    private static void setting(int row, int col){
        data = new int[row][col];
        int val = 1;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                data[i][j] = val++;
            }
        }
    }
}

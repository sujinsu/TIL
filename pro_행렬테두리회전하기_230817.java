import java.util.*;
public class pro_행렬테두리회전하기_230817 {

    static int[][] data;

    public List<Integer> solution(int rows, int columns, int[][] queries) {
        List<Integer> answer = new ArrayList<>();
        data = new int[rows][columns];

        int num = 0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                data[i][j] = ++num;
            }
        }

        // 회전 수행 및 최솟값 구하기
        for(int i=0;i<queries.length;i++){
            answer.add(turn(queries[i], rows, columns));
        }
        return answer;
    }

    // 주어진 int 배열 안의 값으로 회전을 수행하고 해당 회전의 최솟값 반환
    int turn(int[] condition, int rows, int columns){

        int start_row = condition[0]-1;
        int end_row = condition[2]-1;
        int start_col = condition[1]-1;
        int end_col = condition[3]-1;

        // row, col : 현재 위치
        int row = condition[0]-1;
        int col = condition[1]-1;

        // next : 다음 값
        int next;

        // 0,1,2,3 (우 하 좌 상)
        int direction = 0;

        int remember = data[start_row][start_col];
        int temp = remember;
        int min = remember;
        boolean check = false;
        while(!check){
            switch (direction) {
                case 0:
                    if(col == end_col){
                        direction += 1;
                        row += 1;
                    }else{
                        col += 1;
                    }
                    break;
                case 1:
                    if(row == end_row){
                        direction += 1;
                        col -= 1;
                    }else{
                        row += 1;
                    }
                    break;
                case 2:
                    if(col == start_col){
                        direction += 1;
                        row -= 1;
                    }else{
                        col -= 1;
                    }
                    break;
                case 3:
                    if(row == start_row){
                        check = true;
                    }else{
                        row -= 1;
                    }
                    break;
            }
            if(check) break;

            if(temp < min) min = temp;

            next = data[row][col];
            data[row][col] = temp;
            temp = next;
        }

        return min;
    }
}

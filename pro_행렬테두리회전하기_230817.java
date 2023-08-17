import java.util.*;

/**
 * rows x columns 크기인 행렬이 있습니다. 행렬에는 1부터 rows x columns까지의 숫자가 한 줄씩 순서대로 적혀있습니다.
 * 이 행렬에서 직사각형 모양의 범위를 여러 번 선택해, 테두리 부분에 있는 숫자들을 시계방향으로 회전시키려 합니다.
 * 각 회전은 (x1, y1, x2, y2)인 정수 4개로 표현하며, 그 의미는 다음과 같습니다.
 *
 * x1 행 y1 열부터 x2 행 y2 열까지의 영역에 해당하는 직사각형에서 테두리에 있는 숫자들을 한 칸씩 시계방향으로 회전합니다.
 *
 * 행렬의 세로 길이(행 개수) rows, 가로 길이(열 개수) columns,
 * 그리고 회전들의 목록 queries가 주어질 때,
 * 각 회전들을 배열에 적용한 뒤, 그 회전에 의해 위치가 바뀐 숫자들 중 가장 작은 숫자들을 순서대로 배열에 담아 return 하도록 solution 함수를 완성해주세요
 */
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

import java.util.*;

public class pro_시뮬레이션_크레인인형뽑기 {
    public static void main(String[] args) {
        int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        int[] moves = {1,5,3,5,1,2,1,4};
        int result = solution(board, moves);
        System.out.println("result = " + result);
    }

    
    public static int solution(int[][] board, int[] moves) {
        int answer = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for(int m:moves){

            for(int i=0;i<board.length;i++){
                if(board[i][m-1] != 0){

                    int now = board[i][m-1];
                    board[i][m-1]  = 0;
                    if(!stack.isEmpty() && stack.peekLast() == now){
                        answer += 2;
                        stack.pollLast();

                    }else{
                        stack.addLast(now);
                    }
                    break;
                }
            }
        }
        return answer;
    }
}

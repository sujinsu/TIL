public class pro_공원산책_231007 {
    public int[] solution(String[] park, String[] routes) {
        int[] answer = new int[2];
        int width = park[0].length();
        int height = park.length;
        String[][] map = new String[height][width];
        for(int i=0;i<height;i++){
            map[i] = park[i].split("");
        }

        // 시작위치 찾기
        int row = 0;
        int col = 0;
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(map[i][j].equals("S")){
                    row = i;
                    col = j;
                }
            }
        }

        for(String r:routes){
            char dir = r.charAt(0);
            int distance = r.charAt(2) - '0';
            int tempCol = col;
            int tempRow = row;

            outerLoop:
            switch(dir){
                case 'E':
                    tempCol = col + distance;
                    if(tempCol < 0 || tempCol >= width) continue;
                    for(int i=1;i<=distance;i++){
                        if(map[row][col + i].equals("X")) break outerLoop;
                    }
                    col = tempCol;
                    break;
                case 'W':
                    tempCol = col - distance;
                    if(tempCol < 0 || tempCol >= width) continue;
                    for(int i=1;i<=distance;i++){
                        if(map[row][col - i].equals("X"))  break outerLoop;
                    }
                    col = tempCol;
                    break;
                case 'S':
                    tempRow = row + distance;
                    if(tempRow < 0 || tempRow >= height) continue;
                    for(int i=1;i<=distance;i++){
                        if(map[row + i][col].equals("X"))  break outerLoop;
                    }
                    row = tempRow;
                    break;
                case 'N':
                    tempRow = row - distance;
                    if(tempRow < 0 || tempRow >= height) continue;
                    for(int i=1;i<=distance;i++){
                        if(map[row - i][col].equals("X"))  break outerLoop;
                    }
                    row = tempRow;
                    break;

            }

        }
        answer[0] = row;
        answer[1] = col;

        return answer;
    }
}

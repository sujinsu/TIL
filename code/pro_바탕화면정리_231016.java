package code;
import java.util.*;
public class pro_바탕화면정리_231016 {
    public int[] solution(String[] wallpaper) {


        int width = wallpaper[0].length();
        int height = wallpaper.length;

        int startR = height;
        int startC = width;
        int endR = 0;
        int endC = 0;

        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                if(wallpaper[i].charAt(j) == '#'){
                    startR = Math.min(startR, i);
                    endR = Math.max(endR, i);
                    startC = Math.min(startC, j);
                    endC = Math.max(endC, j);
                }
            }
        }
        int[] answer = {startR,startC, endR+1,endC+1};
        return answer;
    }
}

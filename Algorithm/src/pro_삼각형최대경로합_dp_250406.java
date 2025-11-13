class pro_삼각형최대경로합_dp_250406 {
    public static void main(String[] args){

    }
    public int solution(int[][] triangle) {
        int row =triangle.length;
        for(int i=1;i<row;i++){
            for(int j=0;j<triangle[i].length;j++){
                if(j==0) {
                    triangle[i][j] += triangle[i-1][0];
                }else if(j==triangle[i].length-1){
                    triangle[i][j] += triangle[i-1][j-1];
                }else{
                    triangle[i][j] += triangle[i-1][j-1] > triangle[i-1][j] ? triangle[i-1][j-1] : triangle[i-1][j];
                }
            }
        }
        int maxNum = triangle[row-1][0];
        for(int i=1;i<triangle[row-1].length;i++){
            if(maxNum < triangle[row-1][i]) maxNum = triangle[row-1][i];
        }
        return maxNum;
    }
}

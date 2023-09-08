import java.util.*;
public class DIgital_탐색_체셔_230908 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int result = 0;
        List<Integer> num = new ArrayList<>();
        List<Integer> other = new ArrayList<>();
        for(int i=0;i<n;i++){
            int temp = sc.nextInt();
            result += temp;
            num.add(temp);
            for(int j=0;j<m;j++){
                int sero =  (temp / (int)Math.pow(10, m-j-1)) ;
                temp -= sero * (int)Math.pow(10, m-j-1);
                // System.out.println(sero);
                if(i==0){
                    other.add(sero * (int)Math.pow(10, n-(i+1)));
                }else{
                    other.set(j, other.get(j) + sero * (int)Math.pow(10, n-(i+1)));
                }
            }
        }

        int sum = other.stream().mapToInt(Integer::intValue).sum();

        if(result<sum) result = sum;
        System.out.println(result);
    }
}

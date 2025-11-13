
import java.util.*;
public class Digital_트리_트리그리기_230904 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();


        // 루트 노드에 리프 노드를 붙임
        for (int i = 1; i <= m; i++) {
            System.out.println("0 " + i);
        }

        // 나머지 리프 노드가 아닌 노드를 붙임
        for (int i = m + 1; i < n; i++) {
            System.out.println(i - m + " " + i);
        }
    }
}

import java.util.*;

public class Digital_조합_카드_230821 {
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        // N : 전체카드 수, M : 뽑을 카드 수, G: 목표 숫자
        int N = sc.nextInt();
        int M = sc.nextInt();
        int G = sc.nextInt();

        List<Integer> card = new ArrayList<>();
        for(int i=0;i<N;i++){
            card.add(sc.nextInt());
        }

        int answer = choose(card,M,G,0,0);

        System.out.println(answer);
    }

    static int choose(List<Integer> card,int M, int G, int idx, int sum){

        if(M == 0){
            if(sum <= G){
                return sum;
            }
            return 0;
        }

        if(idx >= card.size()){
            return 0;
        }

        int notChoose = choose(card,M,G, idx + 1, sum);
        int choose = choose(card,M-1,G,idx +1, sum + card.get(idx));

        // 선택한 경우와 선택하지 않은 경우 중 더 큰 값 반환
        return Math.max(notChoose, choose);
    }
}

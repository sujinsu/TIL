/**
 * 친구들은 1번부터 N번까지 시계방향으로 원형으로 앉았습니다.
 * 1번부터 한 명씩 시계방향으로 1,2,...,K까지 셉니다.
 * K를 말하는 사람은 원에서 나갑니다. 그 후에는 다음 자리에 앉아있는 사람이 1부터 다시 셉니다. 하트 여왕도 이 게임에 M 번으로 참가합니다.
 */

import java.util.*;

//class Main {
//    public static void main(String[] args) {
//
//
//        Scanner sc = new Scanner(System.in);
//        String[] data = sc.nextLine().split(" ");
//        int N = Integer.parseInt(data[0]);
//        int K = Integer.parseInt(data[1]);
//        int M = Integer.parseInt(data[2]);
//
//        Queue<Integer> queue = new LinkedList<>();
//        for(int i=1;i<=N;i++){
//            queue.add(i);
//        }
//        // System.out.println(queue);
//        int count = 0;
//
//        while(!queue.isEmpty()){
//            for(int i=1;i<K;i++){
//                queue.add(queue.poll());
//            }
//
//            int temp = queue.poll();
//            count += 1;
//            if(temp == M){
//                System.out.println(count);
//                break;
//            }
//        }
//
//        // System.out.println(queue);
//    }
//}


// 👉 시간초과 !


/**
 * 큐 쓰지않는 방법
  */




class Digital_queue_파자마_230809 {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);
        String[] data = sc.nextLine().split(" ");
        int N = Integer.parseInt(data[0]);
        int K = Integer.parseInt(data[1]);
        int M = Integer.parseInt(data[2]);

        List<Integer> freinds = new ArrayList<>();
        // Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            freinds.add(i);
        }

        int count = 0;
        int current = 0; // 인덱스

        while (!freinds.isEmpty()) {
            current = (current + K - 1) % N;
            int who = freinds.get(current);
            N -= 1;
            count += 1;
            freinds.remove(current);

            if (who == M) {
                System.out.println(count);
                break;
            }
        }
    }
}


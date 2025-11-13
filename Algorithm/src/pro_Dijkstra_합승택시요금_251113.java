import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class pro_Dijkstra_합승택시요금_251113 {
    public static void main(String[] args) {
        int n=6, s=4,a=6,b=2;
        int[][] fares = {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}};
        solution(n,s,a,b,fares);
    }

    /***
     * @param n 지점 개수
     * @param s 출발점
     * @param a a의 도착지
     * @param b b의 도착지
     * @param fares 요금
     * @return
     */
    public static int solution(int n, int s, int a, int b, int[][] fares) {
        List<List<Node>> graph = new ArrayList<>();
        for(int i=0;i<=n;i++) graph.add(new ArrayList<>());

        for(int[] f:fares){
            int u=f[0], v=f[1], val=f[2];
            graph.get(u).add(new Node(v, val));
            graph.get(v).add(new Node(u, val));
        }



        int[] distFromS = dijkstra(s, graph);
        int[] distFromA = dijkstra(a, graph);
        int[] distFromB = dijkstra(b, graph);

        // i까지 합승 가정
        int answer = Integer.MAX_VALUE;
        for(int i=1;i<=n;i++){
            answer = Math.min(distFromS[i] + distFromA[i] + distFromB[i], answer);
        }
        System.out.println(answer);
        return answer;
    }

    static int[] dijkstra(int start, List<List<Node>> graph){
        int[] distArr = new int[graph.size()];
        Arrays.fill(distArr,Integer.MAX_VALUE);
        distArr[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.dist - b.dist); // 요금이 싼 곳 부터
        pq.add(new Node(start,0));

        while(!pq.isEmpty()){
            Node now = pq.poll();
            for(Node next:graph.get(now.v)){
                int nextDist = next.dist + now.dist;
                if(nextDist > distArr[next.v]) continue;
                distArr[next.v] = nextDist;
                pq.add(new Node(next.v, nextDist));
            }
        }
        return distArr;
    }

    static class Node{
        int v;
        int dist;
        Node(int v, int dist){
            this.v = v;
            this.dist = dist;
        }
    }
}

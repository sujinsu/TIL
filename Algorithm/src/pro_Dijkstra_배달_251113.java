// 마을이 많고, 간선이 적을 때는 인접 리스트가 성능상 유리 << 필요한 간선만 돈다

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class pro_Dijkstra_배달_251113 {
    public static void main(String[] args) {
        int N = 5;
        int[][] road = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int K = 3;
        System.out.println(solution(N, road, K));
    }

    public static int solution(int N, int[][] road, int K) {
        // 인접리스트 간선 세팅
        List<List<Node>> graph = new ArrayList<>();
        for(int i=0;i<=N;i++){
            graph.add(new ArrayList<>());
        }

        for(int[] r:road){
            int a=r[0], b=r[1], c=r[2];
            boolean found = false;
            for(Node n:graph.get(a)){
                if(n.v == b){
                    n.dist = Math.min(n.dist, c);
                    found = true;
                    break;
                }
            }
            if(!found) graph.get(a).add(new Node(b,c));

            found = false;
            for(Node n:graph.get(b)){
                if(n.v == a){
                    n.dist = Math.min(n.dist, c);
                    found = true;
                    break;
                }
            }
            if(!found) graph.get(b).add(new Node(a,c));
        }


        int[] distArr = new int[N+1];
        Arrays.fill(distArr,Integer.MAX_VALUE);
        distArr[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.dist - b.dist);
        pq.add(new Node(1,0));

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.dist > distArr[now.v]) continue;
            for(Node n: graph.get(now.v)){
                int nextDist = n.dist + now.dist;
                if(nextDist < distArr[n.v]){
                    distArr[n.v] = nextDist;
                    pq.add(new Node(n.v, nextDist));
                }

            }
        }

        int answer = 0;
        for(int i=1;i<=N;i++){
            if(distArr[i] <= K) answer++;
        }
        return answer;
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

import java.util.*;

public class pro_Dijkstra_가장먼노드_251113 {
    public static void main(String[] args) {
        int n = 6;
        int[][] edge = {{3,6}, {4,3},{3,2},{1,3},{1,2},{2,4},{5,2}};

        solution(n, edge);
    }

    public static int solution(int n, int[][] edge) {

        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<=n;i++) graph.add(new ArrayList<>());

        for(int[] e:edge){
            int u=e[0], v=e[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] dist = new int[n+1];
        Arrays.fill(dist, -1);
        dist[1] = 0;

        Deque<Integer> q = new ArrayDeque<>();
        q.add(1);
        while(!q.isEmpty()){
            int now = q.pollFirst();
            for(int next:graph.get(now)){
                if(dist[next] != -1) continue;
                dist[next] = dist[now] + 1;
                q.add(next);
            }
        }

        int answer = 0;
        int maxV = 0;
        for(int i=1;i<=n;i++){
            if(maxV < dist[i]){
                maxV = dist[i];
                answer = 1;
            } else if(maxV == dist[i]){
                answer ++;
            }
        }
        //System.out.println(answer);
        return answer;
    }
}

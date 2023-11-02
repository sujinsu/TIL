import java.util.*;

/**
 * BFS가 어떨 때 쓰이는지, 기억해두기
 */
class Solution {
    public int solution(int x, int y, int n) {
        int answer = bfs( x,  y,  n);

        return answer;
    }
    static class Pair {
        int value;
        int steps;

        Pair(int value, int steps) {
            this.value = value;
            this.steps = steps;
        }
    }

    public int bfs(int x, int y, int n) {
        Queue<Pair> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.add(new Pair(x, 0));
        visited.add(x);

        while (!queue.isEmpty()) {
            Pair current = queue.poll();

            if (current.value == y) {
                return current.steps;
            }

            int[] nextValues = {
                    current.value + n,
                    current.value * 2,
                    current.value * 3
            };

            for (int nextValue : nextValues) {
                if (!visited.contains(nextValue) && nextValue <= y) {
                    visited.add(nextValue);
                    queue.add(new Pair(nextValue, current.steps + 1));
                }
            }
        }

        return -1;
    }

//     void getPrimeFactors(int y){
//         ArrayList<Integer> factorsList = new ArrayList<>();
//         for(int i=2;i<=y;i++){
//             while(y % i == 0){
//                 factorsList.add(i);
//                 y /= i;
//             }
//         }

//         System.out.println(factorsList);
//     }
}
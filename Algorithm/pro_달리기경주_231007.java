import java.util.*;
public class pro_달리기경주_231007 {
    public List<String> solution(String[] players, String[] callings) {
        List<String> answer = new ArrayList<>(Collections.nCopies(players.length, null));

        Map<String, Integer> playerToPosition = new HashMap<>();
        String[] positionToPlayer = new String[players.length];

        // 초기 위치 설정
        for (int i = 0; i < players.length; i++) {
            playerToPosition.put(players[i], i);
            positionToPlayer[i] = players[i];
        }

        for (String calling : callings) {
            int currentPosition = playerToPosition.get(calling);
            if (currentPosition > 0) { // 첫 번째 위치가 아니라면
                // 위치 교환
                String otherPlayer = positionToPlayer[currentPosition - 1];
                positionToPlayer[currentPosition] = otherPlayer;
                positionToPlayer[currentPosition - 1] = calling;

                playerToPosition.put(calling, currentPosition - 1);
                playerToPosition.put(otherPlayer, currentPosition);
            }
        }

        // 결과 리스트에 추가
        for (int i = 0; i < players.length; i++) {
            answer.set(i, positionToPlayer[i]);
        }

        return answer;
    }
}

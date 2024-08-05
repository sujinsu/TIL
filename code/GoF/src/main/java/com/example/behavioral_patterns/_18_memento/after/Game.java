package com.example.behavioral_patterns._18_memento.after;

public class Game {

    private int redTeamScore;

    private int blueTeamScore;

    public int getRedTeamScore() {
        return redTeamScore;
    }

    public void setRedTeamScore(int redTeamScore) {
        this.redTeamScore = redTeamScore;
    }

    public int getBlueTeamScore() {
        return blueTeamScore;
    }

    public void setBlueTeamScore(int blueTeamScore) {
        this.blueTeamScore = blueTeamScore;
    }

    /**
     * memento 저장 메서드
     * @return
     */
    public GameSave save() {
        return new GameSave(this.blueTeamScore, this.redTeamScore);
    }

    /**
     * memento 복원 메서드
     * @param gameSave
     */
    public void restore(GameSave gameSave) {
        this.blueTeamScore = gameSave.getBlueTeamScore();
        this.redTeamScore = gameSave.getRedTeamScore();
    }

}

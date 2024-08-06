package com.example.behavioral_patterns._18_memento.java;


import com.example.behavioral_patterns._18_memento.before.Game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 아래 예제를 위해서는
 * care taker : byte stream을 가지고 있어야 함.
 * originator  : Serializable, 직렬화, 역직렬화 지원 가능해야 함.
 */
public class MementoInJava {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // TODO Serializable
        Game game = new Game();
        game.setRedTeamScore(10);
        game.setBlueTeamScore(20);

        /* 직렬화 역직렬화 원하는 클래스는 Serializable 인터페이스 구현 필요 */
        /* 직렬화 : 바이트 스트림으로 바꿔줌 >> 파일 저장, 네트워크 전송 및 여러 활용 가능 */
        // TODO 직렬화
        try(FileOutputStream fileOut = new FileOutputStream("GameSave.hex"); // 다른 확장자도 상관없음. 의미 없음. 읽기 좋게 하려고 hex 지정
        ObjectOutputStream out = new ObjectOutputStream(fileOut))
        {
            out.writeObject(game);
        }

        game.setBlueTeamScore(25);
        game.setRedTeamScore(15);

        /* 역직렬화 : 바이트 스트림으로 기존 객체 복원 */
        // TODO 역직렬화
        try(FileInputStream fileIn = new FileInputStream("GameSave.hex");
            ObjectInputStream in = new ObjectInputStream(fileIn))
        {
            game = (Game) in.readObject();
            System.out.println(game.getBlueTeamScore());
            System.out.println(game.getRedTeamScore());
        }
    }
}

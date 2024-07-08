package com.example.structural_pattern._8_composite.java;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * 서로 다른 노드, primitive type 과 그걸 감싸는 wrapper type 이 동일한 인터페이스 구현한 경우
 *
 * - Java swing :  JFrame(컴포짓 객체), component (component interface)
 * - Java server 라이브러리
 */
public class SwingExample {

    public static void main(String[] args) {
        JFrame frame = new JFrame(); // JFrame : TextField, JButton 추가 가능 (프레임이란 공간에 텍스트, 버튼 추가 하는 것)

        JTextField textField = new JTextField();
        textField.setBounds(200, 200, 200, 40);
        frame.add(textField);

        JButton button = new JButton("click");
        button.setBounds(200, 100, 60, 40);
        button.addActionListener(e -> textField.setText("Hello Swing"));
        frame.add(button);

        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setVisible(true); // JFrame, JTextField, JButton 상위상위 ~~ 클래스 컴포넌트에서 만남
        // component가 가진 setVisible을 하면 그 안의 모든 element도 보여줌
        //  JTextField, JButton (leaf)
    }


}

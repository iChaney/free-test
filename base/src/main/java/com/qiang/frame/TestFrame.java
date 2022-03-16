package com.qiang.frame;

import java.awt.*;

/**
 * @author liq
 * @date 2021/6/10 9:43
 */
public class TestFrame extends Frame {
    public void lunch() {
        Button button = new Button("press me");
        button.setBounds(20, 20, 20, 20);
        button.setBackground(Color.MAGENTA);
        this.add(button);
        this.setBackground(Color.MAGENTA);
        this.setBounds(400, 400, 400, 400);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new TestFrame().lunch();
    }
}

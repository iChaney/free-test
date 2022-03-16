package com.qiang.design.template;

/**
 * 模板模式
 *
 * @author liq
 * @date 2021/6/16 11:41
 */
public class Main {
    public static void main(String[] args) {
        Game game = new Basketball();
        game.Play();
    }
}

abstract class Game {
    abstract void playStart();

    abstract void playEnd();

    public final void Play() {
        playStart();
        playEnd();
    }
}

class Basketball extends Game {
    @Override
    void playStart() {
        System.out.println("Basketball start ...");
    }

    @Override
    void playEnd() {
        System.out.println("Basketball end ...");
    }
}

class Football extends Game {
    @Override
    void playStart() {
        System.out.println("football start ...");
    }

    @Override
    void playEnd() {
        System.out.println("football end ...");
    }
}

package com.qiang.design.decorator;

/**
 * 装饰者模式 现在要给一个英雄带上皮肤和翅膀
 *
 * @author liq
 * @date 2021/6/9 15:13
 */
public class DecoratorTest {

    public static void main(String[] args) {
        HeroDecorator heroDecorator = new HeroDecorator(new Hero());
        heroDecorator.hello();
    }

    private static class Hero {
        public void hello() {
            System.out.println("你好, 我是艾希");
        }
    }

    private static class HeroDecorator {
        private Hero hero;

        public HeroDecorator(Hero hero) {
            this.hero = hero;
        }

        public void hello() {
            System.out.println("你好, 我是艾希");
            System.out.println("我有皮肤");
            System.out.println("我有翅膀");
        }
    }


}

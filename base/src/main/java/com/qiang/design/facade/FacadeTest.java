package com.qiang.design.facade;

/**
 * 门面模式 将做饭的流程交给厨师, 吃饭的时候只需要喊厨师就可以了
 *
 * @author liq
 * @date 2021/6/9 15:02
 */
public class FacadeTest {
    public static void main(String[] args) {
        /*Vegetables vegetables = new Vegetables();
        vegetables.cut();
        Rice rice = new Rice();
        rice.cook();
        Bowl bowl = new Bowl();
        bowl.serve();*/
        Cooker cooker = new Cooker(new Vegetables(), new Rice(), new Bowl());
        cooker.cook();
    }

    private static class Cooker {
        Vegetables vegetables;
        Rice rice;
        Bowl bowl;

        public Cooker(Vegetables vegetables, Rice rice, Bowl bowl) {
            this.vegetables = vegetables;
            this.rice = rice;
            this.bowl = bowl;
        }

        public void cook() {
            vegetables.cut();
            rice.cook();
            bowl.serve();
        }
    }


    private static class Vegetables {
        public void cut() {
            System.out.println("切菜...");
        }
    }

    private static class Rice {
        public void cook() {
            System.out.println("烧米...");
        }
    }

    private static class Bowl {
        public void serve() {
            System.out.println("盛饭...");
        }
    }
}

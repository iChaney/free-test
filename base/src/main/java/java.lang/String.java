package java.lang;

/**
 * @author liq
 * @date 2022/3/7 18:19
 */
public class String {
    static {
        System.out.println("自定义string static");
    }
    public String() {
        System.out.println("自定义string构造器");
    }

    public static void main(String[] args) {
        String s = new String();
    }
}

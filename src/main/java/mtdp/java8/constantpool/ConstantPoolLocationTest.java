package mtdp.java8.constantpool;

/**
 * @author wangte
 * @date created at 2018/8/13
 */
public class ConstantPoolLocationTest {

    public static void main(String[] args) {
        testAppend();
        testNoAppend();
    }

    private static void testAppend(){
        String s = new StringBuilder("111").append("111").toString();

        s.intern();

        String s1 = "111111";

        System.out.println(s == s1);
    }

    private static void testNoAppend(){
        String s = new StringBuilder("222").toString();

        s.intern();

        String s1 = "222";

        System.out.println(s == s1);
    }
}

package mtdp.java8.combinefunction;

import java.util.function.IntUnaryOperator;

/**
 * @author wangte
 * @date created at 2018/9/23
 */
public class T1 {

    public static void main(String[] args) {

    }

    private static int test(int num) {
        IntUnaryOperator fx = x -> x + 1;
        IntUnaryOperator gx = x -> x * 2;

        return fx.andThen(gx).applyAsInt(num);
    }
}

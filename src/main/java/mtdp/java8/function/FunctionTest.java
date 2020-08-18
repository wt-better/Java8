package mtdp.java8.function;

import java.util.function.Function;

/**
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/6/10
 */
public class FunctionTest {

    public static void main(String[] args) {
        Function<Integer, Integer> times2 = i -> i * 2;
        Function<Integer, Integer> squared = i -> i * i;

        System.out.println(times2.apply(4));

        System.out.println(squared.apply(4));

        //32                先4×4然后16×2,先执行apply(4)，在times2的apply(16),先执行参数，再执行调用者。
        System.out.println(times2.compose(squared).apply(4));

        //64               先4×2,然后8×8,先执行times2的函数，在执行squared的函数。
        System.out.println(times2.andThen(squared).apply(4));

        //16
        System.out.println(Function.identity().compose(squared).apply(4));

        //64
        System.out.println(Function.identity().compose(times2.andThen(squared)).apply(4));
    }

}

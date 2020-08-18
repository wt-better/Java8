package mtdp.java8.optional;

import java.util.Optional;

/**
 * @author wangte
 * @date created at 2018/8/11
 */
public class Main {

    public static void main(String[] args) {
        Object obj = null;
        System.out.println(Optional.ofNullable(obj).isPresent());

        Optional<Integer> optional = Optional.of(2);
        System.out.println(optional.orElse(1));

        String str = "hhhhh";
        System.out.println(optional.orElseGet(str::length));
    }
}

package mtdp.java8.stream;

import java.util.Arrays;
import java.util.List;

/**
 * 测试Integer sum reduce的性能，会不会因为自动装箱而影响
 *
 * @author wangte
 * @date created at 2018/11/15
 */
public class ReducePerformanceTest {

    public static void main(String[] args) {
        int[] numberArray = new int[10000000];
        Arrays.setAll(numberArray, i -> i);

        reduceAsIntArray(numberArray);

        reduceAsStreamTmpInt(numberArray);

        //这种方式在数据量大的时候性能最好，相比reduceAsStreamTmpInt不会声明庞大的新的临时变量，指针地址频繁发生改变
        //TODO why performance better
        reduceAsStreamInt(numberArray);
        //errorReduce(numberArray);
        //List<Integer> numberList = Arrays.stream(numberArray).boxed().collect(toList());
        //reduceAsInteger(numberList);
    }

    private static void reduceAsIntArray(int[] numberArray) {
        long startTime = System.currentTimeMillis();
        int result = 0;
        for (int i : numberArray) {
            result += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("elapsed time = " + (endTime - startTime) + "ms");
    }

    /**
     * ❌❌
     */
    public static void reduceAsInteger(List<Integer> numberList) {
        long startTime = System.currentTimeMillis();
        System.out.println(numberList.parallelStream().reduce(0, (a, b) -> a + b));
        long endTime = System.currentTimeMillis();
        System.out.println("elapsed time = " + (endTime - startTime) + "ms");
    }

    public static void reduceAsStreamTmpInt(int[] numberArray) {
        long startTime = System.currentTimeMillis();
        Arrays.stream(numberArray).reduce(0, (a, b) -> a + b);
        long endTime = System.currentTimeMillis();
        System.out.println("elapsed time = " + (endTime - startTime) + "ms");
    }

    /**
     * stream api会进行优化，不会进行频繁的拆箱装箱
     *
     * @param numberArray
     */
    public static void reduceAsStreamInt(int[] numberArray) {
        int result = 0;
        long startTime = System.currentTimeMillis();
        Arrays.stream(numberArray).reduce(result, (a, b) -> a + b);
        long endTime = System.currentTimeMillis();
        System.out.println("elapsed time = " + (endTime - startTime) + "ms");
    }


    /**
     * ❌❌
     */
    public static void errorReduce(int[] numberArray) {
        Integer result = 0;
        long startTime = System.currentTimeMillis();
        for (int i : numberArray) {
            result += i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("elapsed time = " + (endTime - startTime) + "ms");
    }
}

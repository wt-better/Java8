package mtdp.java8.apple;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangte
 * @date created at 2018/8/4
 */
public class Filter {

    private static List<Apple> filterGreenApple(List<Apple> apples) {
        // 以卫语句返回
        if (CollectionUtils.isEmpty(apples)) {
            return null;
        }

        List<Apple> resultList = Lists.newArrayList();
        for (Apple apple : apples) {
            if ("green".equalsIgnoreCase(apple.getColor())) {
                resultList.add(apple);
            }
        }

        return resultList;
    }

    /**
     * 为了应对变化，将color抽出来，以参数的形式进行传递
     */
    private static List<Apple> filter(List<Apple> apples, String color) {
        if (CollectionUtils.isEmpty(apples)) {
            return null;
        }

        List<Apple> resultList = Lists.newArrayList();
        for (Apple apple : apples) {
            if (color.equalsIgnoreCase(apple.getColor())) {
                resultList.add(apple);
            }
        }

        return resultList;
    }

    public interface FilterExample {
        boolean filter(Apple apple);
    }

    public abstract class ColorFilter implements FilterExample {

    }

    public class RedColorFilter extends ColorFilter {
        @Override
        public boolean filter(Apple apple) {
            return "RED".equals(apple.getColor());
        }
    }

    public static List<Apple> filter(List<Apple> apples, FilterExample example) {
        if (CollectionUtils.isEmpty(apples)) {
            return null;
        }

        List<Apple> list = Lists.newArrayList();
        for (Apple apple : apples) {
            if (example != null && example.filter(apple)) {
                list.add(apple);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("RED", 160),
                new Apple("YELLOW", 100), new Apple("GREEN", 50));

//        List<Apple> result1 = filterGreenApple(apples);
//        // 这里要进行Empty判断，否则可能导致NPE异常
//        if (CollectionUtils.isNotEmpty(result1)) {
//            result1.forEach(System.out::println);
//        }

//        filter(apples, null);
    }
}

package mtdp.java8.combinefunction;

import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author wangte
 * @date created at 2018/9/23
 */
public class T2 {

    public static void main(String[] args) {
        List<Apple> appleList = Lists.newArrayList(new Apple("RED", 1), new Apple("RED", 2),
                new Apple("GREEN", 2), new Apple("YELLOW", 3));

        List<Apple> redAppleList = filter(appleList, apple -> apple.getColor().equalsIgnoreCase("RED"));
        if (CollectionUtils.isNotEmpty(redAppleList)) {
            redAppleList.forEach(System.out::println);
        }

        Predicate<Apple> redApple = apple -> apple.getColor().equalsIgnoreCase("red");
        Predicate<Apple> combineAppleFilter = redApple.and(apple -> apple.getWeight() > 1);
        List<Apple> combineAppleList = filter(appleList, combineAppleFilter);
        if (CollectionUtils.isNotEmpty(combineAppleList)) {
            combineAppleList.forEach(System.out::println);
        }

    }

    @SuppressWarnings(value = "unchecked")
    private static <T> List<T> filter(List<? super T> list, Predicate<? super T> predicate) {
        List<T> resultList = new ArrayList<>();
        for (Object o : list) {
            T t = (T) o;
            if (predicate.test(t)) {
                resultList.add(t);
            }
        }
        return resultList;
    }

    interface Fruit {

    }

    abstract static class AbstractFruit implements Fruit {
        abstract String getName();
    }

    static class Apple extends AbstractFruit {

        String color;

        int weight;

        public Apple(String color, int weight) {
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }

        @Override
        String getName() {
            return "Apple";
        }
    }
}

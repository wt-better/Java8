package mtdp.java8.stream;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * JAVA7和JAVA8集合遍历方式的对比
 *
 * @author wangte
 * @date created at 2018/8/17
 */
public class CompareTraversalTest {

    public static void main(String[] args) {
        ArrayList<Dish> dishes = Lists.newArrayList(new Dish(100, "麻婆豆腐"), new Dish(200, "鸡蛋"), new Dish(300, "牛奶"),
                new Dish(400, "花生豆浆"), new Dish(500, "糖醋里脊"));

        //getLowDishesNameInJava7(dishes).forEach(System.out::println);
        //getLowDishesNameInJava8(dishes, d -> d.getCalories() < 400).forEach(System.out::println);
        List<Dish> list = dishes.stream().filter(d -> d.getCalories() < 0).collect(toList());
        System.out.println(list.size());
        System.out.println(list);
    }


    private static List<String> getLowDishesNameInJava8(List<Dish> dishes, Predicate<Dish> predicate) {
        return dishes.stream().filter(predicate).sorted(comparing(Dish::getCalories)).map(Dish::getName).collect(toList());
    }

    private static List<String> getLowDishesNameInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });

        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish lowCaloricDish : lowCaloricDishes) {
            lowCaloricDishesName.add(lowCaloricDish.getName());
        }
        return lowCaloricDishesName;
    }

    private static class Dish {

        private int calories;
        private String name;

        public Dish(int calories, String name) {
            this.calories = calories;
            this.name = name;
        }

        public int getCalories() {
            return calories;
        }

        public String getName() {
            return name;
        }
    }
}

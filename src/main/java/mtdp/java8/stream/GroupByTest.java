package mtdp.java8.stream;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Collectors.groupBy test class
 *
 * @author wangte
 * @date created at 2018/8/17
 */
public class GroupByTest {

    public static void main(String[] args) {
        ArrayList<Dish> dishes = Lists.newArrayList(new Dish(100, "麻婆豆腐", 1), new Dish(200, "鸡蛋", 1), new Dish(300, "牛奶", 2),
                new Dish(400, "花生豆浆", null), new Dish(500, "糖醋里脊", 2));

//        ArrayList<Dish> dishes = Lists.newArrayList(new Dish(100, "麻婆豆腐", 1), new Dish(200, "鸡蛋", 1), new Dish(300, "牛奶", 2),
//                new Dish(400, "花生豆浆", 2), new Dish(500, "糖醋里脊", 2));


        //在groupBy方法里by的选项不能为null，否则会抛出NPE异常
        Map<Integer, List<Dish>> map = dishes.stream().collect(Collectors.groupingBy(Dish::getType));
        map.forEach((k, v) ->
                System.out.println(k + " - > " + v)
        );
    }

    private static class Dish {

        private int calories;
        private String name;
        private Integer type;

        private Dish(int calories, String name, Integer type) {
            this.calories = calories;
            this.name = name;
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public int getCalories() {
            return calories;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Dish{" +
                    "calories=" + calories +
                    ", name='" + name + '\'' +
                    ", type=" + type +
                    '}';
        }
    }
}

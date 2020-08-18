package mtdp.java8.future.action1;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wangte
 * @date created at 2018/8/11
 */
public class Main {

    public static void main(String[] args) {
        Future<String> future = invoke(() -> {
            Thread.sleep(3000);
            return "OK";
        });
        System.out.println(future.get());
//        while (!future.isDone()){
//            //
//        }
//        System.out.println(future.get());

    }

    private static <T> Future<T> invoke(final Callable<T> callable) {
        AtomicReference<T> reference = new AtomicReference<>();
        AtomicBoolean isDone = new AtomicBoolean(false);

        new Thread(() -> {
            try {
                T result = callable.call();
                reference.set(result);
                isDone.set(true);
            } catch (Exception e) {
                // nothing to do
            }
        }).start();

        return new Future<T>() {
            @Override
            public boolean isDone() {
                return isDone.get();
            }

            @Override
            public T get() {
                //循环拿取isDone，直到获取成功再返回
                while (!isDone()) {

                }
                return reference.get();
            }
        };
    }
}

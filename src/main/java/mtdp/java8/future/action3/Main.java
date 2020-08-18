package mtdp.java8.future.action3;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wangte
 * @date created at 2018/8/12
 */
public class Main {

    public static void main(String[] args) {

        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(3000);
                return "I AM FINISHED";
            } catch (InterruptedException e) {
                return "I AM ERROR";
            }
        });

        future.setCompletable(new Completable<String>() {

            @Override
            public void complete(String s) {
                System.out.println(s);
            }

            @Override
            public void exception(Throwable ex) {
                ex.printStackTrace();
            }
        });

    }

    private static <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> reference = new AtomicReference<>();
        AtomicBoolean isDone = new AtomicBoolean(false);

        Future<T> future = new Future<T>() {

            private Completable completable;

            @Override
            public boolean isDone() {
                return isDone.get();
            }

            @Override
            public T get() {
                return reference.get();
            }

            @Override
            public void setCompletable(Completable completable) {
                this.completable = completable;
            }

            @Override
            public Completable getCompletable() {
                return this.completable;
            }
        };

        new Thread(() -> {

            //Completable completable = future.getCompletable() ❌ 因为这里获取的completable可能为null，需要实时获取
            try {
                T result = callable.call();
                reference.set(result);
                isDone.set(true);

                if (future.getCompletable() != null) {
                    future.getCompletable().complete(result);
                }
            } catch (Exception e) {
                if (future.getCompletable() != null) {
                    future.getCompletable().exception(e);
                }
            }
        }).start();

        return future;
    }
}

package mtdp.java8.concurrent.java8;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 * @author <a href="wangte@meitaun.com">Te</a>
 * @date created at 2019/4/14
 */
public class CompletableFutureTest {

    @SuppressWarnings(value = "all")
    public static class ThreadPerTaskExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }


    /**
     * 直接new出completable，测试
     */
    private static void test1() {
        final CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignore) {

            }
            completableFuture.complete(10.0);
        }).start();

        completableFuture.whenComplete((v, t) -> Optional.of(v).ifPresent(System.out::println));
    }

    /**
     * 使用工厂方法 -> supplyAsync
     * 入参：Supplier接口，Executor
     * <p>
     * java.lang.NullPointerException
     * -> Optional.of(t).ifPresent(Throwable::printStackTrace);
     * why ？ not print double value 主线程结束了，导致子线程还未打印
     * why ？ add -  Optional.of(null); not exit  Optional.of(null)会抛异常，遇到异常，whenComplete不会往下执行
     * 使用：Optional.ofNullable(t).ifPresent(Throwable::printStackTrace);
     * 或者try -catch
     */
    private static void test2() {
        CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 10.0;
                },
                new ThreadPerTaskExecutor()).whenComplete((v, t) -> {
            Optional.of(v).ifPresent(System.out::println);
            Optional.of(t).ifPresent(Throwable::printStackTrace);
        }).join();
    }

    /**
     * 使用工厂方法 -> supplyAsync
     * 入参：Supplier接口
     */
    private static void test3() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10.0;
        }).whenComplete((v, t) -> {
            // this generate write with try catch
            // or occur exception  will not execute next
            Optional.of(v).ifPresent(System.out::println);
            try {
                Optional.of(t).ifPresent(Throwable::printStackTrace);
            } catch (Exception ignore) {

            }

        }).join();
    }

    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();

        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello world!";
        }).whenComplete((t,e)->{
            System.out.println(t);
        }).join();
    }
}

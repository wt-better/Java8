package mtdp.java8.concurrent.java5;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wangte
 * @date created at 2018/9/23
 */
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //执行器服务
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Future<String> future = executorService.submit(() -> "Hello World");

        //synchronized get
        String value = future.get();
        System.out.println(value);

        executorService.shutdown();
    }
}

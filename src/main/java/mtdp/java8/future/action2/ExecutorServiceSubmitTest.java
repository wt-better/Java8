package mtdp.java8.future.action2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author wangte
 * @date created at 2018/8/12
 */
public class ExecutorServiceSubmitTest {

    public static void main(String[] args) {

        final String[] result = new String[1];
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String[]> future = executorService.submit(() -> {
            try {
                Thread.sleep(3000);
                result[0] = "Hello";
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, result);


        try {
            System.out.println(future.get()[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }

        executorService.shutdown();
    }
}

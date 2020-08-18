package mtdp.java8.concurrent.before5;

/**
 * @author wangte
 * @date created at 2018/9/23
 */
public class CompletableRunnableMain {

    public static void main(String[] args) throws InterruptedException {
        CompletableRunnable completableRunnable = new CompletableRunnable();
        Thread thread = new Thread(completableRunnable, "sub thread");

        thread.start();
        //等待线程terminate  并行 -》 串行
        thread.join();

        System.out.println("Main Thread Starting ...");
        System.out.println(completableRunnable.isCompleted());

    }

    private static class CompletableRunnable implements Runnable {
        private boolean completed;

        @Override
        public void run() {
            System.out.printf("Hello World! %s \n", "every body");
            completed = true;
        }

        public boolean isCompleted() {
            return completed;
        }
    }
}

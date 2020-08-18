package mtdp.java8.concurrent.java7;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Fork Join Demo
 *
 * @author wangte
 * @date created at 2018/9/23
 */
public class ForkJoinDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //生成一个任务
        CountTask countTask = new CountTask(1, 50);
        ForkJoinTask<Integer> future = forkJoinPool.submit(countTask);

        System.out.println(future.get());
    }

    private static class CountTask extends RecursiveTask<Integer> {

        /**
         * 分隔阀值
         */
        private static final int THRESHOLD = 2;

        private int begin;
        private int end;

        public CountTask() {
        }

        public CountTask(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            //判断是否小于阀值
            if (end - begin < THRESHOLD) {
                for (int i = begin; i <= end; i++) {
                    sum += i;
                }
                return sum;
            } else {
                int middle = (begin + end) / 2;
                CountTask leftTask = new CountTask(begin, middle);
                CountTask rightTask = new CountTask(middle + 1, end);

                leftTask.fork();
                rightTask.fork();

                Integer leftSum = leftTask.join();
                Integer rightSum = rightTask.join();
                return leftSum + rightSum;
            }
        }
    }
}

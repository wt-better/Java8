package mtdp.java8.future.action1;

/**
 * @author wangte
 * @date created at 2018/8/11
 */
@FunctionalInterface
public interface Callable<T> {

    /**
     * compute a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    T call() throws Exception;
}

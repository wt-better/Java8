package mtdp.java8.future.action3;

/**
 * @author wangte
 * @date created at 2018/8/11
 * <p>
 * Future 接口
 */
public interface Future<T> {

    /**
     * 判断当前task是否完成
     *
     * @return 完成返回true，反之返回false
     */
    boolean isDone();

    /**
     * 返回task的执行结果
     *
     * @return GroupByTest
     */
    T get();

    void setCompletable(Completable completable);

    Completable getCompletable();
}

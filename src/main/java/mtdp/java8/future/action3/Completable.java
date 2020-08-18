package mtdp.java8.future.action3;

/**
 * @author wangte
 * @date created at 2018/8/12
 */
public interface Completable <T>{

    void complete(T t);

    void exception(Throwable ex);
}

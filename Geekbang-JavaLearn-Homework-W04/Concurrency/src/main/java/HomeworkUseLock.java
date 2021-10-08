import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class HomeworkUseLock {

    static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        ThreadTask task = new ThreadTask(lock);
        Thread t1 = new Thread(task);
        t1.start();
        try {
            Thread.sleep(5);
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            lock.lock();

            // 确保  拿到result 并输出
            System.out.println("异步计算结果为：" + ThreadTask.result);

            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        }finally {
            lock.unlock();
        }
        // 然后退出main线程
    }

}

class ThreadTask implements Runnable{

    static int result;

    public Lock lock;
    public ThreadTask(Lock lock){
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            result = sum();
        }finally {
            lock.unlock();
        }
    }

    private int sum() {
        return fibo(36);
    }

    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}

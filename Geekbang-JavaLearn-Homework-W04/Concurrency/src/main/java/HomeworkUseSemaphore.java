import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class HomeworkUseSemaphore {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        Semaphore semaphore = new Semaphore(1);

        SemaphoreTask task = new SemaphoreTask(semaphore);
        Thread t1 = new Thread(task);
        t1.start();
        try {
            Thread.sleep(3);
        } catch (Exception e){
            e.printStackTrace();
        }

        // 确保  拿到result 并输出
        semaphore.acquireUninterruptibly();
        System.out.println("异步计算结果为：" + SemaphoreTask.result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        semaphore.release();
        // 然后退出main线程
    }
}

class SemaphoreTask implements Runnable{

    static int result;

    private Semaphore semaphore;
    public SemaphoreTask(Semaphore semaphore){
        this.semaphore = semaphore;
    }
    @Override
    public void run() {
        semaphore.acquireUninterruptibly();
        result = sum();
        semaphore.release();
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
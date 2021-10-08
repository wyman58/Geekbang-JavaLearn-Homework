import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class HomeworkUseCyclicBarrier {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(1, () -> {
            System.out.println("异步计算结果为：" + CyclicBarrierTask.result);
            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        });

        CyclicBarrierTask task = new CyclicBarrierTask(cyclicBarrier);
        Thread t1 = new Thread(task);
        t1.start();

        // 然后退出main线程
    }
}

class CyclicBarrierTask implements Runnable{

    static int result;

    private CyclicBarrier cyclicBarrier;
    public CyclicBarrierTask(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }
    @Override
    public void run() {

        result = sum();
        try {
            cyclicBarrier.await();
        }catch (BrokenBarrierException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
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

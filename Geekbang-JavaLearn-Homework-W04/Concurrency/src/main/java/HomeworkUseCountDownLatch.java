import java.util.concurrent.CountDownLatch;

public class HomeworkUseCountDownLatch {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(1);

        CountdownLatchTask task = new CountdownLatchTask(countDownLatch);
        Thread t1 = new Thread(task);
        t1.start();
//        try {
//            Thread.sleep(3);
//        } catch (Exception e){
//            e.printStackTrace();
//        }

        // 确保  拿到result 并输出
        try {
            countDownLatch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("异步计算结果为：" + CountdownLatchTask.result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        // 然后退出main线程
    }
}

class CountdownLatchTask implements Runnable{

    static int result;

    private CountDownLatch countDownLatch;
    public CountdownLatchTask(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }
    @Override
    public void run() {

        result = sum();
        countDownLatch.countDown();
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

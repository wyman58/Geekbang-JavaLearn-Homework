

public class HomeworkUseSync {
    public static void main(String[] args) {

        long start = System.currentTimeMillis();


        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法
        CalculationTask task = new CalculationTask();
        Thread t1 = new Thread(task);

        t1.start();
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }

        // 确保  拿到result 并输出
        synchronized (task) {
            System.out.println("异步计算结果为：" + CalculationTask.result);

            System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");
        }
        // 然后退出main线程
    }
}

class CalculationTask implements Runnable{

    static int result;
    @Override
    public void run() {
        result = sum();
    }

    private synchronized int sum() {
        return fibo(48);
    }

    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
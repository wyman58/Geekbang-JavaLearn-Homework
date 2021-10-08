import java.util.concurrent.*;

public class HomeworkUseThreadPool {

    public static void main(String[] args){

        long start = System.currentTimeMillis();

        ExecutorService service = Executors.newFixedThreadPool(5);

        Callable<Integer> calculation = new Calculation();
        Future<Integer> task = service.submit(calculation);
        try {
            Integer result = task.get();
            System.out.println("异步计算结果为：" +  result);
            System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        service.shutdown();


    }

}

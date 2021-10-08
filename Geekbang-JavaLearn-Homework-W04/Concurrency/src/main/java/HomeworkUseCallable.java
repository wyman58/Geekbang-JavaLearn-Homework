import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class HomeworkUseCallable {

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        Calculation calculation = new Calculation();
        FutureTask<Integer>  task = new FutureTask<Integer>(calculation);

        new Thread(task).start();

        try {
            Integer result = task.get();
            System.out.println("异步计算结果为：" +  result);
            System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}



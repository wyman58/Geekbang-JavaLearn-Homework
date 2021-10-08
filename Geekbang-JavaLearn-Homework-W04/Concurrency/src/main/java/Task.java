public class Task implements Runnable{

    static int result;
    @Override
    public void run() {
        result = sum();
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

import java.util.concurrent.RecursiveAction;

public class Test extends RecursiveAction {
    int n;
    long sleepMs;

    public Test(int n, long sleepMs) {
        this.n = n;
        this.sleepMs = sleepMs;
    }

    @Override
    protected void compute() {
        for (int i = 0; i < n; i++) {
            try {
                Thread.sleep(sleepMs);
            } catch (InterruptedException e) {
            }
        }
    }

}

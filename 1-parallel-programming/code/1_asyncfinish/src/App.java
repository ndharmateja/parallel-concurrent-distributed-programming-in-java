import static edu.rice.pcdp.PCDP.finish;
import static edu.rice.pcdp.PCDP.async;

public class App {
    public static void main(String[] args) throws Exception {
        int n = 10;
        long sleepMs = 10;
        int numProcesses = 64;
        long start, end;

        Test t1 = new Test(n, sleepMs);
        start = System.currentTimeMillis();
        finish(() -> {
            for (int j = 0; j < numProcesses; j++)
                async(() -> t1.compute());
        });
        end = System.currentTimeMillis();
        System.out.println(String.format("Async-Finish\texpected: %dms\t\tactual %dms", n * sleepMs, end - start));

        Test[] tests = new Test[numProcesses];
        for (int i = 0; i < numProcesses; i++) {
            tests[i] = new Test(n, sleepMs);
        }
        start = System.currentTimeMillis();
        for (Test t : tests)
            t.fork();
        for (Test t : tests)
            t.join();
        end = System.currentTimeMillis();
        System.out.println(String.format("Fork-Join\texpected: %dms\t\tactual %dms", n * sleepMs, end - start));

        start = System.currentTimeMillis();
        Test t = new Test(numProcesses * n, sleepMs);
        t.compute();
        end = System.currentTimeMillis();
        System.out.println(
                String.format("Sequential\texpected: %dms\tactual %dms", numProcesses * n * sleepMs, end - start));
    }
}

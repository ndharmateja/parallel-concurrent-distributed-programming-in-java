
import static edu.rice.pcdp.PCDP.finish;
import static edu.rice.pcdp.PCDP.async;

public class ASumAsyncFinish {
    int[] array;
    int lo;
    int hi;
    long sum;

    public ASumAsyncFinish(int[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    public ASumAsyncFinish(int[] array) {
        this(array, 0, array.length - 1);
    }

    public void compute() {
        // System.out.println(String.format("compute(lo=%d, hi=%d)", lo, hi));
        if (hi == lo) { 
            sum = array[lo];
        } else if (hi < lo) {
            sum = 0;
        } else {
            int mid = (lo + hi) / 2;
            ASumAsyncFinish left = new ASumAsyncFinish(array, lo, mid);
            ASumAsyncFinish right = new ASumAsyncFinish(array, mid + 1, hi);

            finish(() -> {
                async(() -> {
                    left.compute();
                });
                right.compute();
            });

            this.sum = left.sum + right.sum;
        }
    }
}

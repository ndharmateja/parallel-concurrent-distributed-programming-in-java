import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ASumForkJoin extends RecursiveAction {
    int[] array;
    int lo;
    int hi;
    long sum;

    public ASumForkJoin(int[] array, int lo, int hi) {
        this.array = array;
        this.lo = lo;
        this.hi = hi;
    }

    public ASumForkJoin(int[] array) {
        this(array, 0, array.length - 1);
    }

    public void compute() {
        if (hi == lo) {
            sum = array[lo];
        } else if (hi < lo) {
            sum = 0;
        } else if (hi - lo <= 1000) {
            for (int i = lo; i <= hi; i++) {
                sum += array[i];
            }
        } else {
            int mid = (lo + hi) / 2;
            ASumForkJoin left = new ASumForkJoin(array, lo, mid);
            ASumForkJoin right = new ASumForkJoin(array, mid + 1, hi);
            left.fork();
            right.compute();
            left.join();
            this.sum = left.sum + right.sum;
        }
    }
}

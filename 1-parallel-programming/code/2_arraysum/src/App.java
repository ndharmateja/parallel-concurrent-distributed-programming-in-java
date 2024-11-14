import static edu.rice.pcdp.PCDP.async;
import static edu.rice.pcdp.PCDP.finish;

public class App {
    private static int[] createArray(int n) {
        int[] array = new int[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }

    public static void main(String[] args) throws Exception {
        int[] array = createArray(100000000);

        ASumForkJoin asum1 = new ASumForkJoin(array);
        long start1 = System.currentTimeMillis();
        asum1.compute();
        long end1 = System.currentTimeMillis();
        System.out.println("Total execution time: " + (end1 - start1));
        System.out.println("Sum1: " + asum1.sum);

        ASumVanilla asum2 = new ASumVanilla(array);
        long start2 = System.currentTimeMillis();
        asum2.compute();
        long end2 = System.currentTimeMillis();
        System.out.println("Total execution time: " + (end2 - start2));
        System.out.println("Sum2: " + asum2.sum);
    }
}

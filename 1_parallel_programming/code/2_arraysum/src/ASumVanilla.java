public class ASumVanilla {
    int[] array;
    long sum;

    public ASumVanilla(int[] array) {
        this.array = array;
    }

    public void compute() {
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
    }
}

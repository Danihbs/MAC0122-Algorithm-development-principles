public class CollatzSequence {
    public static void collatz(int N){
        System.out.print(N + " ");
        if (N == 1) return;
        if (N % 2 == 0) collatz(N / 2);
        else collatz(3 * N + 1);
    }

    public static void main (String [] args) {
        int N = Integer.parseInt(args [0]);
        collatz(N);
    }
}
import java.util.HashMap;
import java.util.Map;

/**
 * Memoized recursive Fibonacci implementation.
 */
public class FibonacciMemoization {
    private final Map<Integer, Long> memo = new HashMap<>();

    public FibonacciMemoization() {
        memo.put(0, 0L);
        memo.put(1, 1L);
    }

    public long fib(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative.");
        }
        if (n > 92) {
            throw new IllegalArgumentException("n must be <= 92 to fit in long.");
        }

        Long known = memo.get(n);
        if (known != null) {
            return known;
        }

        long value = fib(n - 1) + fib(n - 2);
        memo.put(n, value);
        return value;
    }

    public static void main(String[] args) {
        int n = args.length > 0 ? Integer.parseInt(args[0]) : 40;
        FibonacciMemoization fibonacci = new FibonacciMemoization();
        System.out.println("F(" + n + ") = " + fibonacci.fib(n));
    }
}

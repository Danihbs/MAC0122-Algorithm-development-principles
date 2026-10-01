public class FibonacciMemoization{
    static long[] memo = new long[100];
    
    public static long fibonacci(int N){
        if (N == 0) return 0;
        if (N == 1) return 1;

        if (memo[N] == 0){
            memo[N] = fibonacci(N-1) + fibonacci(N-2);
        }

        return memo[N];
    }

    public static void main(String[] args){
        int N = Integer.parseInt(args[0]);
        System.out.println(fibonacci(N));
    }
}
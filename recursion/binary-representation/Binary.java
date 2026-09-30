/**
 * Educational recursive conversion of a non-negative integer to binary.
 */
public class Binary {
    public static String toBinary(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative.");
        }
        if (n == 0) {
            return "0";
        }
        return toBinaryRecursive(n);
    }

    private static String toBinaryRecursive(int n) {
        if (n == 0) {
            return "";
        }
        return toBinaryRecursive(n / 2) + (n % 2);
    }

    public static void main(String[] args) {
        int value = args.length > 0 ? Integer.parseInt(args[0]) : 13;
        System.out.println(value + " in binary = " + toBinary(value));
    }
}

import java.util.concurrent.ThreadLocalRandom;

/**
 * Uniform random integer generation in an inclusive range [min, max].
 */
public class RandomInt {
    public static int uniform(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min must be <= max.");
        }

        long range = (long) max - min + 1L;
        long value = ThreadLocalRandom.current().nextLong(range) + min;
        return (int) value;
    }

    public static void main(String[] args) {
        int min = args.length > 0 ? Integer.parseInt(args[0]) : 1;
        int max = args.length > 1 ? Integer.parseInt(args[1]) : 6;
        int samples = args.length > 2 ? Integer.parseInt(args[2]) : 20;

        for (int i = 0; i < samples; i++) {
            System.out.println(uniform(min, max));
        }
    }
}

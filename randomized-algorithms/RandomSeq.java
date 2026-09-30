import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Generates a sequence of uniformly distributed random integers.
 */
public class RandomSeq {
    public static List<Integer> generate(int length, int min, int max) {
        if (length < 0) {
            throw new IllegalArgumentException("length must be non-negative.");
        }
        if (min > max) {
            throw new IllegalArgumentException("min must be <= max.");
        }

        List<Integer> sequence = new ArrayList<>(length);
        long range = (long) max - min + 1L;
        for (int i = 0; i < length; i++) {
            int value = (int) (ThreadLocalRandom.current().nextLong(range) + min);
            sequence.add(value);
        }
        return sequence;
    }

    public static void main(String[] args) {
        int length = args.length > 0 ? Integer.parseInt(args[0]) : 15;
        int min = args.length > 1 ? Integer.parseInt(args[1]) : 0;
        int max = args.length > 2 ? Integer.parseInt(args[2]) : 99;

        List<Integer> sequence = generate(length, min, max);
        System.out.println(sequence);
    }
}

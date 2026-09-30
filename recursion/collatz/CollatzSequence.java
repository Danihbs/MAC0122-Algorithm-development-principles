import java.util.ArrayList;
import java.util.List;

/**
 * Recursive Collatz sequence generation with a safe maximum number of steps.
 */
public class CollatzSequence {
    public static List<Long> generate(long start, int maxSteps) {
        if (start <= 0) {
            throw new IllegalArgumentException("Start must be positive.");
        }
        if (maxSteps < 1) {
            throw new IllegalArgumentException("maxSteps must be at least 1.");
        }

        List<Long> sequence = new ArrayList<>();
        generateRecursive(start, maxSteps, sequence);
        return sequence;
    }

    private static void generateRecursive(long current, int stepsRemaining, List<Long> sequence) {
        sequence.add(current);
        if (current == 1 || stepsRemaining == 1) {
            return;
        }

        long next = (current % 2 == 0) ? (current / 2) : (3 * current + 1);
        generateRecursive(next, stepsRemaining - 1, sequence);
    }

    public static void main(String[] args) {
        long start = args.length > 0 ? Long.parseLong(args[0]) : 27;
        int maxSteps = args.length > 1 ? Integer.parseInt(args[1]) : 200;
        List<Long> sequence = generate(start, maxSteps);
        System.out.println("Collatz sequence from " + start + ": " + sequence);
        if (sequence.get(sequence.size() - 1) != 1) {
            System.out.println("Stopped before reaching 1 due to maxSteps limit.");
        }
    }
}

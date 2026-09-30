import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Recursive permutation generation using character counts.
 * Repeated characters are handled by producing unique permutations only.
 */
public class Permutations {
    public static List<String> generateUnique(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null.");
        }

        Map<Character, Integer> counts = new LinkedHashMap<>();
        for (char c : input.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        List<String> result = new ArrayList<>();
        backtrack(counts, new StringBuilder(), input.length(), result);
        return result;
    }

    private static void backtrack(
            Map<Character, Integer> counts,
            StringBuilder current,
            int targetLength,
            List<String> result) {
        if (current.length() == targetLength) {
            result.add(current.toString());
            return;
        }

        for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
            int remaining = entry.getValue();
            if (remaining == 0) {
                continue;
            }

            char c = entry.getKey();
            entry.setValue(remaining - 1);
            current.append(c);

            backtrack(counts, current, targetLength, result);

            current.deleteCharAt(current.length() - 1);
            entry.setValue(remaining);
        }
    }

    public static void main(String[] args) {
        String input = args.length > 0 ? args[0] : "aba";
        List<String> permutations = generateUnique(input);
        System.out.println("Unique permutations of '" + input + "': " + permutations);
        System.out.println("Total: " + permutations.size());
    }
}

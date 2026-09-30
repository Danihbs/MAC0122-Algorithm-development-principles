import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Four-peg Tower of Hanoi using a Frame-Stewart-style recursive strategy.
 * For n disks, choose k that minimizes: 2*T4(k) + (2^(n-k)-1), then recurse.
 */
public class HanoiFourPegs {
    private final Map<Integer, Long> movesMemo = new HashMap<>();
    private final Map<Integer, Integer> bestKMemo = new HashMap<>();

    public HanoiFourPegs() {
        movesMemo.put(0, 0L);
        movesMemo.put(1, 1L);
    }

    public List<String> solve(int disks, String from, String to, String aux1, String aux2) {
        if (disks < 1 || disks > 30) {
            throw new IllegalArgumentException("disks must be between 1 and 30.");
        }
        List<String> moves = new ArrayList<>();
        solveFour(disks, from, to, aux1, aux2, moves);
        return moves;
    }

    public long minimalMoveCount(int disks) {
        if (disks < 0 || disks > 30) {
            throw new IllegalArgumentException("disks must be between 0 and 30.");
        }
        return minMovesFour(disks);
    }

    private void solveFour(int n, String from, String to, String aux1, String aux2, List<String> moves) {
        if (n == 0) {
            return;
        }
        if (n == 1) {
            moves.add("Move disk 1 from " + from + " to " + to);
            return;
        }

        int k = bestK(n);
        solveFour(k, from, aux1, to, aux2, moves);
        solveThree(n - k, from, to, aux2, moves);
        solveFour(k, aux1, to, from, aux2, moves);
    }

    private void solveThree(int n, String from, String to, String aux, List<String> moves) {
        if (n == 0) {
            return;
        }
        if (n == 1) {
            moves.add("Move disk 1 from " + from + " to " + to);
            return;
        }
        solveThree(n - 1, from, aux, to, moves);
        moves.add("Move disk " + n + " from " + from + " to " + to);
        solveThree(n - 1, aux, to, from, moves);
    }

    private int bestK(int n) {
        minMovesFour(n);
        return bestKMemo.get(n);
    }

    private long minMovesFour(int n) {
        Long known = movesMemo.get(n);
        if (known != null) {
            return known;
        }

        long best = Long.MAX_VALUE;
        int bestK = 1;
        for (int k = 1; k < n; k++) {
            long candidate = 2L * minMovesFour(k) + minMovesThree(n - k);
            if (candidate < best) {
                best = candidate;
                bestK = k;
            }
        }

        movesMemo.put(n, best);
        bestKMemo.put(n, bestK);
        return best;
    }

    private long minMovesThree(int n) {
        return (1L << n) - 1L;
    }

    public static void main(String[] args) {
        int disks = args.length > 0 ? Integer.parseInt(args[0]) : 5;
        HanoiFourPegs solver = new HanoiFourPegs();
        List<String> moves = solver.solve(disks, "A", "D", "B", "C");

        for (String move : moves) {
            System.out.println(move);
        }
        System.out.println("Total moves: " + moves.size());
        System.out.println("Expected minimal moves: " + solver.minimalMoveCount(disks));
    }
}

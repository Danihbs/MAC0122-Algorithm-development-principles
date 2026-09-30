import java.util.ArrayList;
import java.util.List;

/**
 * Classic recursive Tower of Hanoi with three pegs.
 */
public class Hanoi {
    public static List<String> solve(int disks, String from, String to, String aux) {
        if (disks < 1) {
            throw new IllegalArgumentException("disks must be at least 1.");
        }
        List<String> moves = new ArrayList<>();
        solveRecursive(disks, from, to, aux, moves);
        return moves;
    }

    private static void solveRecursive(int disks, String from, String to, String aux, List<String> moves) {
        if (disks == 1) {
            moves.add("Move disk 1 from " + from + " to " + to);
            return;
        }

        solveRecursive(disks - 1, from, aux, to, moves);
        moves.add("Move disk " + disks + " from " + from + " to " + to);
        solveRecursive(disks - 1, aux, to, from, moves);
    }

    public static void main(String[] args) {
        int disks = args.length > 0 ? Integer.parseInt(args[0]) : 3;
        List<String> moves = solve(disks, "A", "C", "B");
        for (String move : moves) {
            System.out.println(move);
        }
        System.out.println("Total moves: " + moves.size());
    }
}

import java.awt.BorderLayout;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * Simple Tower of Hanoi animation demo.
 * In headless environments, prints moves with short delays.
 */
public class AnimatedHanoi {
    public static List<String> solve(int disks) {
        if (disks < 1 || disks > 12) {
            throw new IllegalArgumentException("disks must be between 1 and 12.");
        }
        List<String> moves = new ArrayList<>();
        solveRecursive(disks, "A", "C", "B", moves);
        return moves;
    }

    private static void solveRecursive(int n, String from, String to, String aux, List<String> moves) {
        if (n == 1) {
            moves.add("Move disk 1 from " + from + " to " + to);
            return;
        }
        solveRecursive(n - 1, from, aux, to, moves);
        moves.add("Move disk " + n + " from " + from + " to " + to);
        solveRecursive(n - 1, aux, to, from, moves);
    }

    public static void main(String[] args) {
        int disks = args.length > 0 ? Integer.parseInt(args[0]) : 4;
        List<String> moves = solve(disks);

        if (GraphicsEnvironment.isHeadless()) {
            for (String move : moves) {
                System.out.println(move);
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
            System.out.println("Total moves: " + moves.size());
            return;
        }

        SwingUtilities.invokeLater(() -> showSwingAnimation(moves));
    }

    private static void showSwingAnimation(List<String> moves) {
        JFrame frame = new JFrame("Animated Hanoi (Text Demo)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea = new JTextArea(18, 50);
        textArea.setEditable(false);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        final int[] index = {0};
        Timer timer = new Timer(250, event -> {
            if (index[0] >= moves.size()) {
                textArea.append("\nTotal moves: " + moves.size() + "\n");
                ((Timer) event.getSource()).stop();
                return;
            }
            textArea.append(moves.get(index[0]) + "\n");
            index[0]++;
        });
        timer.start();
    }
}

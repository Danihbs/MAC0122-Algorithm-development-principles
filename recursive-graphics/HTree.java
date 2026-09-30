import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Recursive H-tree drawing demonstration with headless-safe fallback.
 */
public class HTree {
    private static final int SIZE = 700;

    public static List<String> generateSegments(double centerX, double centerY, double length, int depth) {
        if (length <= 0) {
            throw new IllegalArgumentException("length must be positive.");
        }
        if (depth < 0) {
            throw new IllegalArgumentException("depth must be non-negative.");
        }

        List<String> segments = new ArrayList<>();
        collectSegments(centerX, centerY, length, depth, segments);
        return segments;
    }

    private static void collectSegments(double x, double y, double length, int depth, List<String> segments) {
        if (depth == 0) {
            return;
        }

        double half = length / 2.0;
        double x0 = x - half;
        double x1 = x + half;
        double y0 = y - half;
        double y1 = y + half;

        segments.add(String.format("(%.1f, %.1f) -> (%.1f, %.1f)", x0, y0, x0, y1));
        segments.add(String.format("(%.1f, %.1f) -> (%.1f, %.1f)", x1, y0, x1, y1));
        segments.add(String.format("(%.1f, %.1f) -> (%.1f, %.1f)", x0, y, x1, y));

        double next = length / Math.sqrt(2.0);
        collectSegments(x0, y0, next, depth - 1, segments);
        collectSegments(x0, y1, next, depth - 1, segments);
        collectSegments(x1, y0, next, depth - 1, segments);
        collectSegments(x1, y1, next, depth - 1, segments);
    }

    public static void main(String[] args) {
        int depth = args.length > 0 ? Integer.parseInt(args[0]) : 5;
        double length = args.length > 1 ? Double.parseDouble(args[1]) : 280.0;
        if (depth < 0) {
            throw new IllegalArgumentException("depth must be non-negative.");
        }

        if (GraphicsEnvironment.isHeadless()) {
            List<String> segments = generateSegments(SIZE / 2.0, SIZE / 2.0, length, depth);
            System.out.println("Generated segments: " + segments.size());
            for (int i = 0; i < Math.min(12, segments.size()); i++) {
                System.out.println(segments.get(i));
            }
            return;
        }

        SwingUtilities.invokeLater(() -> show(depth, length));
    }

    private static void show(int depth, double length) {
        JFrame frame = new JFrame("Recursive H-Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new HTreePanel(depth, length));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static class HTreePanel extends JPanel {
        private final int depth;
        private final double length;

        HTreePanel(int depth, double length) {
            this.depth = depth;
            this.length = length;
            setPreferredSize(new Dimension(SIZE, SIZE));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLACK);
            drawHTree(g2, SIZE / 2.0, SIZE / 2.0, length, depth);
        }

        private void drawHTree(Graphics2D g2, double x, double y, double length, int depth) {
            if (depth == 0) {
                return;
            }

            double half = length / 2.0;
            double x0 = x - half;
            double x1 = x + half;
            double y0 = y - half;
            double y1 = y + half;

            g2.drawLine((int) Math.round(x0), (int) Math.round(y0), (int) Math.round(x0), (int) Math.round(y1));
            g2.drawLine((int) Math.round(x1), (int) Math.round(y0), (int) Math.round(x1), (int) Math.round(y1));
            g2.drawLine((int) Math.round(x0), (int) Math.round(y), (int) Math.round(x1), (int) Math.round(y));

            double next = length / Math.sqrt(2.0);
            drawHTree(g2, x0, y0, next, depth - 1);
            drawHTree(g2, x0, y1, next, depth - 1);
            drawHTree(g2, x1, y0, next, depth - 1);
            drawHTree(g2, x1, y1, next, depth - 1);
        }
    }
}

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Chaos game demonstration for the Sierpinski triangle.
 * Uses recursive point generation and supports headless output.
 */
public class ChaosGame {
    private static final int SIZE = 640;

    public static List<Point> generatePoints(int iterations, Random random) {
        if (iterations < 1) {
            throw new IllegalArgumentException("iterations must be at least 1.");
        }
        if (random == null) {
            throw new IllegalArgumentException("random must not be null.");
        }

        Point[] vertices = {
            new Point(SIZE / 2, 40),
            new Point(40, SIZE - 40),
            new Point(SIZE - 40, SIZE - 40)
        };

        List<Point> points = new ArrayList<>(iterations);
        Point start = new Point(SIZE / 2, SIZE / 2);
        generateRecursive(start, vertices, iterations, random, points);
        return points;
    }

    private static void generateRecursive(
            Point current,
            Point[] vertices,
            int remaining,
            Random random,
            List<Point> points) {
        if (remaining == 0) {
            return;
        }

        Point target = vertices[random.nextInt(vertices.length)];
        Point next = new Point((current.x + target.x) / 2, (current.y + target.y) / 2);
        points.add(next);
        generateRecursive(next, vertices, remaining - 1, random, points);
    }

    public static void main(String[] args) {
        int iterations = args.length > 0 ? Integer.parseInt(args[0]) : 30_000;
        List<Point> points = generatePoints(iterations, new Random());

        if (GraphicsEnvironment.isHeadless()) {
            System.out.println("Generated " + points.size() + " chaos-game points.");
            for (int i = 0; i < Math.min(10, points.size()); i++) {
                Point p = points.get(i);
                System.out.println("Point " + i + ": (" + p.x + ", " + p.y + ")");
            }
            return;
        }

        SwingUtilities.invokeLater(() -> show(points));
    }

    private static void show(List<Point> points) {
        JFrame frame = new JFrame("Chaos Game - Sierpinski Triangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ChaosPanel(points));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static class ChaosPanel extends JPanel {
        private final List<Point> points;

        ChaosPanel(List<Point> points) {
            this.points = points;
            setPreferredSize(new Dimension(SIZE, SIZE));
            setBackground(Color.WHITE);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.BLUE);
            for (Point point : points) {
                g2.fillRect(point.x, point.y, 1, 1);
            }
        }
    }
}

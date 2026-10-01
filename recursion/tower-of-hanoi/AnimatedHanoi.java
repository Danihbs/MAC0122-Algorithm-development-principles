import java.awt.Color;

public class AnimatedHanoi {

    public static void draw(int[] pole) {

        int N = pole.length - 1;

        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.005);
        for (int i = 0; i < 3; i++)
            StdDraw.line(i, -.4, i, N);

        int[] discs = new int[3];
        for (int i = N; i >= 1; i--) {
            Color color = Color.getHSBColor(1.0f * i / N, .7f, .7f);
            StdDraw.setPenColor(color);
            StdDraw.setPenRadius(0.035);
            double size = 0.5 * i / N;
            int p = pole[i];
            StdDraw.line(p-size/2, discs[p], p + size/2, discs[p]);
            ++discs[p];
        }

        StdDraw.show();
        StdDraw.pause(500);
    }

    public static void hanoi(int N) {
        int[] pole = new int[N+1];
        draw(pole);
        hanoi(N, 0, 2, 1, pole);
    }

    public static void hanoi(int n, int from, int temp, int to, int[] pole) {
        if (n == 0) return;
        hanoi(n-1, from, to, temp, pole);
        System.out.println("Move disc " + n + " from pole " + from + " to pole " + to);
        pole[n] = to;
        draw(pole);
        hanoi(n-1, temp, from, to, pole);
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int WIDTH  = 200;
        int HEIGHT = 20;

        StdDraw.setCanvasSize(4*WIDTH, (N+3)*HEIGHT);
        StdDraw.setXscale(-1, 3);
        StdDraw.setYscale(-1, N+3);
        StdDraw.enableDoubleBuffering();

        hanoi(N);
    }
}

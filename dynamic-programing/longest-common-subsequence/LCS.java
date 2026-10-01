import java.util.Scanner;

public class LCS {

    public static String lcs(String x, String y, int i, int j, int[][] opt) {
        if (i == x.length() || j == y.length()) {
            return "";
        }

        if (x.charAt(i) == y.charAt(j)) {
            return x.charAt(i) + lcs(x, y, i + 1, j + 1, opt);
        }

        if (opt[i + 1][j] >= opt[i][j + 1]) {
            return lcs(x, y, i + 1, j, opt);
        } else {
            return lcs(x, y, i, j + 1, opt);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String s = input.next();
        String t = input.next();

        int N = s.length();
        int M = t.length();
        int[][] opt = new int[N + 1][M + 1];

        for (int i = N - 1; i >= 0; i--) {
            for (int j = M - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    opt[i][j] = opt[i + 1][j + 1] + 1;
                } else {
                    opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);
                }
            }
        }

        System.out.println("LCS length:" + opt[0][0]);

        String l = lcs(s, t, 0, 0, opt);
        System.out.println("An LCS:" + l);
        System.out.println("[Sanity check: " + l.length() + "]");
    }
}

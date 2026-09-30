/**
 * Dynamic programming solution for longest common subsequence (LCS),
 * including reconstruction of one optimal subsequence.
 */
public class LCS {
    public static String lcs(String a, String b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Inputs must not be null.");
        }

        int[][] dp = buildTable(a, b);
        return reconstruct(a, b, dp);
    }

    public static int[][] buildTable(String a, String b) {
        int[][] dp = new int[a.length() + 1][b.length() + 1];

        for (int i = 1; i <= a.length(); i++) {
            for (int j = 1; j <= b.length(); j++) {
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp;
    }

    private static String reconstruct(String a, String b, int[][] dp) {
        StringBuilder reversed = new StringBuilder();
        int i = a.length();
        int j = b.length();

        while (i > 0 && j > 0) {
            if (a.charAt(i - 1) == b.charAt(j - 1)) {
                reversed.append(a.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return reversed.reverse().toString();
    }

    public static void main(String[] args) {
        String a = args.length > 0 ? args[0] : "algorithm";
        String b = args.length > 1 ? args[1] : "alligator";
        String lcs = lcs(a, b);
        System.out.println("A: " + a);
        System.out.println("B: " + b);
        System.out.println("LCS: " + lcs + " (length=" + lcs.length() + ")");
    }
}

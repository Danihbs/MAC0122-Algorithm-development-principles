import java.util.Scanner;

public class Permutations {
    private static int count = 0;

    public static void perm1(String s) {
        perm1("", s);
    }

    private static void perm1(String prefix, String s) {
        int n = s.length();
        boolean[] h = new boolean['Z' - 'A' + 1];

        if (n == 0) {
            count++;
        } else {
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);

                if ('a' <= c && c <= 'z') {
                    if (!h[c - 'a']) {
                        h[c - 'a'] = true;
                        perm1(prefix + c, s.substring(0, i) + s.substring(i + 1, n));
                    }
                }

                if ('A' <= c && c <= 'Z') {
                    if (!h[c - 'A']) {
                        h[c - 'A'] = true;
                        perm1(prefix + c, s.substring(0, i) + s.substring(i + 1, n));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String elements = scanner.next();

        perm1(elements);

        System.out.println(count);
        scanner.close();
    }
}
public class HanoiFourPegs {

    static int count = 0;

    public static int k_otimo(int B) {
        int a = 0, somatorio = 0;
        while (somatorio <= B) {
            a++;
            somatorio += a;
        }
        return a - 1;
    }

    public static void hanoi3(int n, int from, int temp, int to, int k) {
        if (n <= 0) {
            return;
        }

        hanoi3(n - 1, from, to, temp, k);
        System.out.print((n + k) + " " + to + "  ");
        count++;
        hanoi3(n - 1, temp, from, to, k);
    }

    public static void hanoi4(int n, int from, int temp1, int temp2, int to) {
        if (n <= 0) {
            return;
        }
        if (n == 1) {
            System.out.print(n + " " + to + "  ");
            count++;
            return;
        }
        if (n == 2) {
            System.out.print((n - 1) + " " + temp1 + "  "
                    + n + " " + to + "  " + (n - 1) + " " + to + "  ");
            count += 3;
            return;
        }

        int k = k_otimo(n);

        hanoi4(n - k, from, temp1, to, temp2);
        // The n - k little discs have already been moved to temp2.
        hanoi3(k, from, temp1, to, n - k);
        hanoi4(n - k, temp2, from, temp1, to);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java HanoiFourPegs <numero de discos> [contar]");
            return;
        }

        int N = Integer.parseInt(args[0]);
        hanoi4(N, 0, 2, 3, 1);
        System.out.println();

        if (args.length >= 2) {
            System.out.println("Moves:" + count);
        }
    }
}

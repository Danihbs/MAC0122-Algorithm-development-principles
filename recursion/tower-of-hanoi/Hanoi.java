public class Hanoi {

    public static String hanoi(int n, boolean left) {
        if (n == 0) {
            return " ";
        }

        String move;
        if (left) {
            move = n + "L";
        } else {
            move = n + "R";
        }

        return hanoi(n - 1, !left) + move + hanoi(n - 1, !left);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Uso: java Hanoi <numero de discos>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        if (n < 0) {
            System.out.println("O numero de discos deve ser nao negativo.");
            return;
        }

        System.out.println(hanoi(n, false));
    }
}

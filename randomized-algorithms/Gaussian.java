import java.util.Random;

/**
 * Gaussian random number generation using java.util.Random.nextGaussian().
 */
public class Gaussian {
    public static double sample(double mean, double standardDeviation, Random random) {
        if (random == null) {
            throw new IllegalArgumentException("random must not be null.");
        }
        if (!(standardDeviation > 0.0)) {
            throw new IllegalArgumentException("standardDeviation must be > 0.");
        }
        return mean + standardDeviation * random.nextGaussian();
    }

    public static void main(String[] args) {
        double mean = args.length > 0 ? Double.parseDouble(args[0]) : 0.0;
        double stdDev = args.length > 1 ? Double.parseDouble(args[1]) : 1.0;
        int count = args.length > 2 ? Integer.parseInt(args[2]) : 10;

        Random random = new Random();
        for (int i = 0; i < count; i++) {
            System.out.printf("%.5f%n", sample(mean, stdDev, random));
        }
    }
}

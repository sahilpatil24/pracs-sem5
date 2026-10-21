
public class HebbianLearningBipolar {

    public static int activation(double net) {
        return net >= 0 ? 1 : -1;
    }

    public static void main(String[] args) {

        double X[][] = {
            {1, -2},
            {0, 1},
            {2, 3},
            {1, -1}
        };

        double W[] = {1, -1};
        double eta = 1.0;

        for (int k = 0; k < X.length; k++) {

            // Calculate net input
            double net = 0;

            for (int j = 0; j < W.length; j++) {
                net += W[j] * X[k][j];
            }

            // Bipolar activation
            int o = activation(net);

            // Hebbian learning signal
            int r = o;

            // Update weights
            for (int j = 0; j < W.length; j++) {
                W[j] = W[j] + eta * r * X[k][j];
            }

            System.out.println(
                "Step " + (k + 1) +
                ": net = " + net + ", output = " + o
            );

            System.out.print("Updated weights: ");
            for (double weight : W) {
                System.out.print(weight + " ");
            }
            System.out.println("\n");
        }

        System.out.print("Final weights: ");
        for (double weight : W) {
            System.out.print(weight + " ");
        }
    }
}

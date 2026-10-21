public class HebbianLearning {

    public static int activation(double net) {
        return net >= 0 ? 1 : 0;
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

            double net = 0;

            for (int j = 0; j < W.length; j++) {
                net += W[j] * X[k][j];
            }

            int o = activation(net);
            int r = o;

            for (int j = 0; j < W.length; j++) {
                W[j] = W[j] + eta * r * X[k][j];
            }

            System.out.println(
                "Step " + (k + 1) +
                ": net = " + net +
                ", o = " + o
            );

            System.out.print("Updated W: ");
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

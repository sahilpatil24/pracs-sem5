
public class ContinuousPerceptronBipolar {

    public static double activation(double net, double lambda) {
        return (2.0 / (1.0 + Math.exp(-lambda * net))) - 1.0;
    }

    public static double derivative(double o, double lambda) {
        return (lambda / 2.0) * (1.0 - o * o);
    }

    public static void main(String[] args) {

        // Input patterns with bias
        double X[][] = {
            {0, 0, 1},
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };

        // Desired bipolar outputs for AND gate
        double desired[] = {-1, -1, -1, 1};

        // Initial weights
        double W[] = {0.1, 0.2, -0.1};

        double eta = 0.5;
        double lambda = 1.0;
        int epochs = 1000;

        for (int epoch = 0; epoch < epochs; epoch++) {

            double totalError = 0;

            for (int k = 0; k < X.length; k++) {

                // Calculate net input
                double net = 0;

                for (int j = 0; j < W.length; j++) {
                    net += W[j] * X[k][j];
                }

                // Calculate output using bipolar sigmoid
                double o = activation(net, lambda);

                // Calculate error
                double r = desired[k] - o;

                // Calculate derivative
                double fPrime = derivative(o, lambda);

                // Calculate delta
                double delta = r * fPrime;

                // Update weights
                for (int j = 0; j < W.length; j++) {
                    W[j] = W[j] + eta * delta * X[k][j];
                }

                // Calculate squared error
                totalError += 0.5 * r * r;
            }

            // Print error after every 100 epochs
            if ((epoch + 1) % 100 == 0) {
                System.out.println(
                    "Epoch: " + (epoch + 1) +
                    " Error: " + totalError
                );
            }
        }

        // Display final weights
        System.out.println("\nFinal Weights:");

        for (int j = 0; j < W.length; j++) {
            System.out.println("W[" + j + "] = " + W[j]);
        }

        // Test final outputs
        System.out.println("\nFinal Outputs:");

        for (int k = 0; k < X.length; k++) {

            double net = 0;

            for (int j = 0; j < W.length; j++) {
                net += W[j] * X[k][j];
            }

            double o = activation(net, lambda);

            System.out.println(
                "Input: " + X[k][0] + ", " + X[k][1] +
                " Desired: " + desired[k] +
                " Output: " + o
            );
        }
    }
}

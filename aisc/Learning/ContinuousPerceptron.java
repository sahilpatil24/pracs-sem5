public class ContinuousPerceptron {

    // CHANGE 1: Use the sigmoid activation function instead of a threshold.
    public static double activation(double net, double lambda) {
        return 1.0 / (1.0 + Math.exp(-lambda * net));
    }

    public static void main(String[] args) {

        // Initialize the 2-input AND gate patterns.
        // The last value in every row is the bias input (always 1).
        double[][] X = {
            {0, 0, 1},
            {0, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };

        double[] desired = {0, 0, 0, 1};

        // Initial weights, learning rate, and sigmoid steepness.
        double[] W = {0.1, 0.2, -0.1};
        double eta = 0.5;
        double lambda = 1.0;

        // CHANGE 2: Train for a fixed number of epochs.
        // Unlike the discrete perceptron, we don't stop at zero mistakes.
        int epochs = 1000;

        for (int epoch = 1; epoch <= epochs; epoch++) {

            double totalError = 0;

            for (int k = 0; k < X.length; k++) {

                // Calculate the weighted sum.
                double net = 0;
                for (int j = 0; j < W.length; j++) {
                    net += W[j] * X[k][j];
                }

                // Calculate the continuous output.
                double o = activation(net, lambda);

                // CHANGE 3: Calculate the derivative of sigmoid.
                double derivative = lambda * o * (1 - o);

                // CHANGE 4: Delta learning signal.
                double r = (desired[k] - o) * derivative;

                // Update weights using the delta rule.
                for (int j = 0; j < W.length; j++) {
                    W[j] = W[j] + eta * r * X[k][j];
                }

                // Accumulate squared error for this pattern.
                double error = desired[k] - o;
                totalError += 0.5 * error * error;
            }

            // Display error occasionally to track learning.
            if (epoch == 1 || epoch % 100 == 0) {
                System.out.printf(
                    "Epoch %d: Error = %.4f%n",
                    epoch, totalError
                );
            }
        }

        // Display the final learned weights.
        System.out.println("\nFinal weights: "
                + java.util.Arrays.toString(W));

        // Test all patterns using the final weights.
        System.out.println("\nFinal predictions:");

        for (int k = 0; k < X.length; k++) {

            double net = 0;
            for (int j = 0; j < W.length; j++) {
                net += W[j] * X[k][j];
            }

            double o = activation(net, lambda);

            // Convert the continuous output into a binary class.
            int predicted = o >= 0.5 ? 1 : 0;

            System.out.printf(
                "%d %d -> o = %.3f -> class %d (desired = %.0f)%n",
                (int) X[k][0],
                (int) X[k][1],
                o,
                predicted,
                desired[k]
            );
        }
    }
}

/*
changes -
1. activation function
2. change in formula for r -- calculate the derivative = lambda * o * (1-o)
3. limited epochs
4. calculate error = desired - o, total_error = 0.5 * square of error
5. 
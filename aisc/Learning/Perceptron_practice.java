public class Perceptron_practice {
    public static int activation(double net) {
        return net >= 0? 1:0;
    }
    public static void main(String[] args) {
        //1 - intialize the input vectors and weight vectors
        double X[][] = {
            {0,0,1},
            {0,1,1},
            {1,0,1},
            {1,1,1}
        };
        double W[] = {1,1,1};
        double eta = 0.1;
        int desired[] = {0,1,1,1};

        int epoch = 0;
        int corrections = 0;
        while(true) {
            System.out.println("\n\nIteration -> " + epoch);
            epoch++;
            corrections = 0;

            //6 - do it every iteration of X
            for(int k = 0; k < X.length; k++) {
                System.out.println("\nIteration(in input vector) -> " + k);
                //3 - calculate the net input
                double net = 0;
                for(int j = 0; j < W.length; j++) {
                    net += W[j] * X[k][j];
                }
                System.out.println("net -> " + net);

                //4 - net input to activation function
                int o = activation(net);
                int r = desired[k] - o;
                System.out.println("o -> " + o);
                System.out.println("d -> " + desired[k]);
                System.out.println("r -> " + r);
                corrections = r!=0? corrections+1: corrections;
                System.out.println("corrections -> " + corrections);
                //5 - update the weights
                System.out.println("Updated Weights -> ");
                for(int j = 0; j < W.length; j++) {
                    W[j] = W[j] + r * eta * X[k][j];
                    System.out.println("W[" + j + "] -> " + W[j]);
                }
            }

            if(corrections==0) break;

        }

    }
}
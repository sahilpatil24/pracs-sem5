public class Perceptron_example {

    //step 2 - write the activation function
    public static int activation(double net) {
        return net >= 0? 1:-1;
    }

    public static void main(String[] args) {

        //step 1 - intialize X and W and the eta(learning rate) and desired output
        double X[][] = {
            {1,-2,0,-1},
            {0,1.5,-0.5,-1},
            {-1,1,0.5,-1}
        };

        int desired[] = {-1,-1,1};

        double eta = 0.1;

        //initialize the weights
        double W[] = {1,-1,0,0.5};

        //step 3 - calculate net and pass it to the activation function
        double net = 0;
        int epoch = 0;
        int corrections = 0;
        while(true) {
            epoch++;
            corrections = 0;
            //Step 5 - calculate for all the input vectors
            for(int k = 0; k < X.length; k++) {
                
                net = 0;

                for(int j = 0; j < W.length; j++) {
                    net += W[j] * X[k][j]; //we will later on place an outer loop so our k will replace 0(it will run every iteration for every input vector)
                }//update 1(in step 5)

                System.out.println("\n\nnet -> " + net);
                int o = activation(net);
                int r = desired[k] - o;//update 3(in step 5)
                corrections = r != 0? corrections + 1: corrections;

                System.out.println("The desired output for X[" + k + "]: " + desired[k]); //update 2(in step 5)
                System.out.println("The actual output for X[" + k + "]: " + o);
                System.out.println("error is -> " + r);

                //step 4 - update the weights
                for(int j = 0; j < W.length; j++) {
                    W[j] = W[j] + eta * r * X[k][j];
                }
            }
            System.out.println("\n\n***********************");
            System.out.println("***********************");
            System.out.println("***********************");
            if(corrections == 0) break;
        }
    }
}
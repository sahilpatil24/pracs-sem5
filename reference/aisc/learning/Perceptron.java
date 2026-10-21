public class Perceptron {
    //step 2
    public static int activation(double net) {
        return net >= 0? 1:0;
    }

    public static void main(String[] args) {

        int epochs = 0;
        int corrections = 0;
        while(true) {
            epochs++;
            corrections = 0;
        //step 1 - initialize the input vectors
        double X[][] = {
            {0,0,1},
            {0,1,1},
            {1,0,1},
            {1,1,1}
        };

        ////////////////////////////////////// IMPORTANTTTTTTTTTTTT
        double W[] = {1,1,-1.5}; 
        int desired[] = {0,0,0,1};

        double eta = 0.1;

        //step 3
        for(int k = 0; k < X.length; k++) {
            double net = 0;
            for(int j = 0; j < W.length; j++) {
                net += W[j] * X[k][j];
            }

            //step 4
            int o = activation(net);
            int r = desired[k] - o;
            if(r != 0) corrections++;

            //step 5
            for(int j = 0; j < W.length; j++) {
                W[j] = W[j] + eta * r * X[k][j];
            }
        }
        if(corrections==0) break;
        }
    }
}
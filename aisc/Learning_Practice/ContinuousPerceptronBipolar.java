    import java.util.*;
    //activation formula change
    //0 -> -1

    public class ContinuousPerceptronBipolar {
        //step 2
        public static double activation(double net, double lambda) {
            // return 2.0/(1.0 + Math.exp(-lambda * net)) + 1; //this should be -1
            return 2.0/(1.0 + Math.exp(-lambda * net)) - 1; //this should be -1
        }

        public static void main(String[] args) {

            int epochs = 0;
            int corrections = 0;
            while(epochs < 1000) {
                epochs++;
                corrections = 0;
            //step 1 - initialize the input vectors
            double X[][] = {
                {-1,-1,1},
                {-1,1,1},
                {1,-1,1},
                {1,1,1}
            };
            double W[] = {1,1,-1.5};
            int desired[] = {-1,-1,-1,1};

            double eta = 0.1;
            double lambda = 1;

            //step 3
            for(int k = 0; k < X.length; k++) {
                double net = 0;
                for(int j = 0; j < W.length; j++) {
                    net += W[j] * X[k][j];
                }

                //step 4
                double o = activation(net,lambda);
                double r = desired[k] - o;
                // double derivative = lambda * (1 - o) * o;
                double derivative = lambda/2 * (1 - o*o);
                // if(r != 0) corrections++;
                if (Math.abs(r) > 0.01) corrections++;

                //step 5
                for(int j = 0; j < W.length; j++) {
                    W[j] = W[j] + eta * r * derivative * X[k][j];
                }
            }
            if(corrections==0) break;
            epochs++;
            }
        }
    }
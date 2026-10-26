public class LQV {
    public static void main(String[] args) {
        double X[][] = {
            {0.4, 0.2},
            {0.12, 0.91},
            {0.67, 0.22},
            {0.33, 0.7}
        };

        double[] T = {1,2,1,2};

        double[][] W = {X[0].clone(), X[1].clone()};

        double[] C = {1,2};

        //algo
        //calculate the difference in the input and the weights
        //sqaure it -- lower diff is the winner

        double alpha = 1;
        for(int i = 0; i < X.length; i++) {
            double[] x = X[i];

            double d1 = 0, d0 = 0;
            for(int k = 0; k < x.length; k++) {
                // d0 += alpha * (x[k] - W[0][k]); 
                // d1 += alpha * (x[k] - W[1][k]);
                d0 += Math.pow(x[k] - W[0][k],2); 
                d1 += Math.pow(x[k] - W[1][k],2);
            }

            int winner = d1 > d0? 0:1;

            //if winner matches the target then move towards the input 
            //else move away from input

            for(int k = 0; k < x.length; k++) {
                if(C[winner] == T[i]) {
                    System.out.println("Winner = Target Winner");
                    W[winner][k] += alpha * (x[k] - W[winner][k]);
                }
                else W[winner][k] -= alpha * (x[k] - W[winner][k]);
            }
            alpha *= 0.5;
        }


    }
}
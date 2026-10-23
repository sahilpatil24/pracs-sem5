public class WTA {
    public static void main(String[] args) {
        double[][] X = {
            {0.4, 0.6},
            {0.45, 0.23},
            {0.12, 0.98},
            {0.22, 0.41}
        };

        double[][] W = {
            {0.23, 0.45},
            {0.91, 0.12}
        };

        //calculate the winner
        //train the winner using the formula W[winner][j] += alpha * (difference in x and W)
        //normalize by Math.sqrt(sum of squares of the vector input values)

        for(double[] x : X) {
            
            double[] net = new double[W.length];
            
            for(int i = 0; i < W.length; i++) {
                net[i] = (W[i][0] * x[0]) + (W[i][1] * x[1]);
            }

            int winner = net[0] > net[1]? 0:1;
            System.out.printf("\n\nBefore Normalizing Winner: [ %.2f %.2f ]",W[winner][0],W[winner][1]);
            double alpha = 0.5;
            //now apply the formula -- winner is calculated
            for(int i = 0; i < x.length; i++) {
                W[winner][i] += alpha * (x[i] - W[winner][i]);
            }

            //now normalize
            double normalized = Math.sqrt(W[winner][0] * W[winner][0] + W[winner][1] * W[winner][1]);

            for(int i = 0; i < x.length; i++) {
                W[winner][i] /= normalized;
            }

            // System.out.println("Winner: [" + W[winner][0] + " " + W[winner][1]);
            System.out.printf("\nAfter Normalizing Winner: [ %.2f %.2f ]",W[winner][0],W[winner][1]);
        }


    }
}
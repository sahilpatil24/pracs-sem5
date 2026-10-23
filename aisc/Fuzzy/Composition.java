public class Composition {
    public static void main(String[] args) {
        double[][] R = {
            {0.6, 0.3, 0.9},
            {0.2, 0.4, 0.8}
        };

        double[][] S = {
            {0.5, 0.1},
            {0.23, 0.7},
            {0.8, 0.42}
        };

        // System.out.println(R.length);
        // System.out.println(R[0].length);
        // System.out.println(S.length);
        // System.out.println(S[0].length);

        //composition of fuzzy relations
        // for(int i = 0; i < R[0].length; i++) {
        //     for(int j = 0; j < S.length; j++) {
        //         //okay i can take the minimum of R[i] and S[j]
        //         //okay i donot know how to program ts
        //         System.out.println(R[i][j]);
        //         System.out.println(S[j][i]);
        //     }
        // }

        //min max
        double T[][] = new double[R.length][S[0].length];

        for(int i = 0; i < R.length; i++) {
            for(int k = 0; k < S[0].length; k++) {
                double max = 0;
                for(int j = 0; j < R[0].length; j++) {
                    // double min = Math.min(R[i][j], S[j][k]);
                    double product = (R[i][j] * S[j][k]);
                    max = Math.max(max,product);   
                }
                T[i][k] = max;
            }
        }

        for(int i = 0; i < T.length; i++) {
            for(int j = 0; j < T[0].length; j++) {
                System.out.println(T[i][j]);
            }
        }
    }
}

/*
*/
public class AlphaBeta {
    public static int[] values = {0,0,0,0,4,5,2,10};

    public static int alp(int index, boolean isMax, int alpha, int beta) {

        //base of minimax
        // if(index > values.length / 2) return values[index];

        // int left = alp(2 * index, !isMax, alpha, beta);
        // int right = alp(2 * index + 1, !isMax, alpha, beta);

        // if(isMax) return Math.max(left, right);
        // else return Math.min(left, right);

        if(index >= values.length / 2) return values[index];

        int left = alp(2 * index, !isMax, alpha, beta);

        //added part for alpha beta
        if(isMax) alpha = Math.max(alpha, left);
        else beta = Math.min(beta, left);

        if(alpha >= beta) { 
            System.out.println("The node " + left + " is searching for a value smaller than it");
            return left;
        }

        int right = alp(2 * index + 1, !isMax, alpha, beta);

        if(isMax) return Math.max(left, right);
        else return Math.min(left, right);

    }

    public static void main(String[] args) {
        int result = alp(1, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.println(result);
    }
}
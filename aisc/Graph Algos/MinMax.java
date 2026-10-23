
public class Minmax {
    static int[] values = {0,0,0,3,5,4,7};

    public static int minimax(int index, boolean isMax) {
        if(index >= values.length / 2) return values[index];

        int left = minimax(2 * index, !isMax);
        int left = minimax(2 * index + 1, !isMax);

        if(isMax) return Math.max(left, right);
        else return Math.min(left, right);
    }

    public static void main(String[] args) {
        minimax()
    }
}

/*
psuedo code would look something like this

minimax(int index, boolean ismax) {

    base condition if node is leaf node then return the node

    calculate the left and the right subtrees
    left = min(2 * index, !isMax)
    right = min(2 * index + 1, !isMax)

    if(isMax) 
        return max(left, right)
    else
        return min(left, right)
}


3 total points
base condition if leaf node then return
calculate the left and right subtree
recursive call


*/
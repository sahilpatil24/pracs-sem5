public class SetOperations {
    public static void main(String[] args) {
        // System.out.println(a\n\nrgs[0]);

        double a[] = {0.4, 0.5, 0.66, 0.23};
        double b[] = {0.45, 0.88, 0.34, 0.92};

        System.out.println("\n\n1. union: ");
        for(int i = 0; i < a.length; i++) {
            System.out.print(Math.max(a[i],b[i]) + " ");
        }

        
        System.out.println("\n\n2. intersection: ");
        for(int i = 0; i < a.length; i++) {
            System.out.print(Math.min(a[i],b[i]) + " ");
        }

        System.out.println("\n\n3. complement of a: ");
        for(int i = 0; i < a.length; i++) {
            System.out.print((1 - a[i]) + " ");
        }

        System.out.println("\n\n4. difference a-b: ");
        for(int i = 0; i < a.length; i++) {
            System.out.printf("%.2f ",Math.min(a[i], 1 - b[i]));
        }

        System.out.println("\n\n5. algebric sum: ");
        for(int i = 0; i < a.length; i++) {
            System.out.print((a[i] + b[i] - a[i]*b[i] )+ " ");
        }

        System.out.println("\n\n6. algebric product: ");
        for(int i = 0; i < a.length; i++) {
            System.out.print((a[i]*b[i]) + " ");
        }

        System.out.println("\n\n7. bounded sum: ");
        for(int i = 0; i < a.length; i++) {
            System.out.printf("%.2f ",Math.min(a[i]+b[i],1));
        }

        System.out.println("\n\n8. bounded difference: ");
        for(int i = 0; i < a.length; i++) {
            System.out.print(Math.max(a[i]-b[i], 0) + " ");
        }


    }
}
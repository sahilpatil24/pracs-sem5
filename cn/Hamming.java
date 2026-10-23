import java.util.*;
public class Hamming {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h[] = new int[9];
        int r[] = new int[9];
        h[3] = 1;
        h[5] = 1;
        h[6] = 1;
        h[7] = 1;
        h[1] = h[3] ^ h[5] ^ h[7];
        h[2] = h[3] ^ h[6] ^ h[7];
        h[4] = h[5] ^ h[6] ^ h[7];

        //overall
        h[8] = h[1] ^ h[2] ^ h[3] ^ h[4] ^ h[5] ^ h[6] ^ h[7];
        int overall = 0;
        for(int i = 1; i <= 8; i++) {
            System.out.printf("%d ",h[i]);
        }
        System.out.println();
        for(int i = 1; i <= 8; i++) {
            r[i] = sc.nextInt();
            overall ^= r[i];
        }

        int c1 = r[1] ^ r[3] ^ r[5] ^ r[7];
        int c2 = r[2] ^ r[3] ^ r[6] ^ r[7];
        int c4 = r[4] ^ r[5] ^ r[6] ^ r[7];
        int error = c4 * 4 + c2 * 2 + c1;

        if(error == 0 && overall == 0) {
            System.out.println("No error");
        }
        else if(error == 0 && overall != 0)    System.out.println("error at position 8");
        else if(error != 0 && overall == 0)    System.out.println("double bit error detection");
        else if(error != 0 && overall != 0)    { 
            System.out.println("single bit error"); 
            r[error] ^= 1; 
            System.out.println("after correction: "); 
            for(int i = 1; i <= 8; i++) {
                System.out.printf("%d ",r[i]);
            }
        }
    }
}
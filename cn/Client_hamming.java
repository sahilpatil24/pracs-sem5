import java.util.*;
import java.net.*;
import java.io.*;

public class Client_hamming {
    public static void main(String[] args) {
        try {

        Socket s = new Socket("localhost",5000);

        // InputStream = s.getInputStream();
        Scanner sc = new Scanner(System.in);
        Scanner io = new Scanner(s.getInputStream());
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        System.out.println("Client side hamming:");

        int h[] = new int[9];
        System.out.println("Enter the 4 data bits");
        System.out.print("h[3]: ");
        h[3] = sc.nextInt();
        System.out.print("h[5]: ");
        h[5] = sc.nextInt();
        System.out.print("h[6]: ");
        h[6] = sc.nextInt();
        System.out.print("h[7]: ");
        h[7] = sc.nextInt();

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
        Integer i1 = new Integer(h[1]);
        String s2 = i1.toString();
        out.println(s2);
        String str = "";
        for(int i = 1; i < 9; i++) str += h[i];
        out.println(str);
        }
    
        catch(Exception e) {}
        
    }
}
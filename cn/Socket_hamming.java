import java.util.*;
import java.net.*;
import java.io.*;

public class Socket_hamming {
    public static void main(String[] args) {
        try {

        ServerSocket ss = new ServerSocket(5000);
        Socket s = ss.accept();

        // InputStream = s.getInputStream();
        Scanner sc = new Scanner(System.in);
        Scanner io = new Scanner(s.getInputStream());
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);

        System.out.println("Server side:");

        String s2 = io.nextLine();
        System.out.println(s2);
        int x = Integer.parseInt(s2);
        System.out.println("Blahblahblah");
        System.out.println(x+1*100);
        String code = io.nextLine();
        System.out.println(code);

        }
        catch(Exception e) {}
    }
}
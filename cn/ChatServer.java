import java.util.*;
import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        try {
            
        ServerSocket ss = new ServerSocket(5000);
        Socket s = ss.accept();

        Scanner sc = new Scanner(System.in);
        Scanner in = new Scanner(s.getInputStream());
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);

        while(true) {
            String msg = in.nextLine();
            System.out.print("Client: ");
            System.out.print(msg + "\n");

            out.println(sc.nextLine());
        }

        }
        catch(Exception e) { System.out.println(e); }

    }
}
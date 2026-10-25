import java.util.*;
import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        try {


        Socket s = new Socket("localhost",5000);
        Scanner sc = new Scanner(System.in);
        Scanner in = new Scanner(s.getInputStream());
        PrintWriter out = new PrintWriter(s.getOutputStream(),true);

        while(true) {
            out.println(sc.nextLine());


            String msg = in.nextLine();
            System.out.print("Server: ");
            System.out.print(msg + "\n");

        }

        }
                catch(Exception e) { System.out.println(e); }

    }
}
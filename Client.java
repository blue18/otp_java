import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {


        try {

            Scanner scanner = new Scanner(System.in);

            // getting localhost ip 
            InetAddress ip = InetAddress.getByName("localhost");

            // establishing the connection with server port 5056
            Socket socket = new Socket(ip, 5056);

            // obtaining input and output stream 
            DataInputStream dataInputStream = DataInputStream(socket.getInputStream());

            DataOutputStream dataOutputStream = DataOutputStream(socket.getOutputStream());
            
            // a loop that creates an exchange of information between client and client handler
            while (true) {

                // printing date or time as requested by client 

            }

            // Closing resources
            scanner.close();
            dataInputStream.close();
            dataOutputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
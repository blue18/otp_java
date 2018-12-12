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
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            
            // a loop that creates an exchange of information between client and client handler
            while (true) {

                // if socket is not closed, parse data between server and client
                if (!socket.isClosed()) {
                    System.out.println(dataInputStream.readUTF());
                    String tosent = scanner.nextLine();
                    dataOutputStream.writeUTF(tosent);
    
                    // if client sends exit, close this connection and then break from loop. 
                    if (tosent.equals("exit")) {
                        System.out.println("Closing this connection: " + socket);
                        socket.close();
                        System.out.println("Connection closed.");
                        break; 
                    }
    
                    // printing date or time as requested by client 
                    String received = dataInputStream.readUTF();
                    System.out.println(received);

                }
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
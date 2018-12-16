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

                // Print the menu from the server
                System.out.println(dataInputStream.readUTF());

                // Read user input 
                String tosent = scanner.nextLine();

                // Send response to server 
                dataOutputStream.writeUTF(tosent);

                // if client sends exit, close this connection and then break from loop. 
                if (tosent.equals("exit")) {
                    System.out.println("Closing this connection: " + socket);
                    socket.close();
                    System.out.println("Connection closed.");
                    break; 
                }

                // if user entered encrypt, read the message to be encrypted 
                if(tosent.equals("encrypt")) {
                    System.out.print("Enter message: ");

                    // read user input 
                    String message = scanner.nextLine();

                    // Send response to server 
                    dataOutputStream.writeUTF(message);
                } else {

                    // get input from server 
                    String received = dataInputStream.readUTF();

                     // printing date or time as requested by client 
                    System.out.println("Received: " + received);
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
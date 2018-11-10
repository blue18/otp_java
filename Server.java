// November 8, 2018 

import java.io.*; 
import java.text.*; 
import java.util.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import java.net.*; 

public class Server {
	public static void main(String[] args) throws IOException {

		// Server is listening
		ServerSocket serverSocket = new ServerSocket(5056);

		// running infinit loop for getting client request
		while (true) {

			Socket socket = null;

			try {

				// socket object to receive incoming client request
				System.out.println("Server is listening.");
				socket = serverSocket.accept();
				System.out.println("A new client is connected: " + socket);

				// obtaining input and output client requests 
				DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
				DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

				System.out.println("Assigning new thread for this client");

				// create a new thread object 
				Thread thread = new ClientHandler(socket, dataInputStream, dataOutputStream);

				// invoking the start() method 
				thread.start();

			} catch (Exception e) {
				socket.close();
				e.printStackTrace();
			}
		}

	}
}

// Login status
enum Status {
	Active, Inactive;
}

class ClientHandler extends Thread {

	DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd"); 
	DateFormat fortime = new SimpleDateFormat("hh:mm:ss"); 
	final DataInputStream dataInputStream; 
	final DataOutputStream dataOutputStream; 
	final Socket socket; 

	// constructor 
	public ClientHandler(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
		this.socket = socket;
		this.dataInputStream = dataInputStream;
		this.dataOutputStream = dataOutputStream;
	}

	@Override
	public void run() {
		String received;
		String toreturn;
		String username;
		String password;

		while (true) {

			try {		
				Status login = Status.Inactive;
				
				// ask the user what he/she wants 
				dataOutputStream.writeUTF("Enter username: ");

				// receive the answer from client 
				username = dataInputStream.readUTF();

				// check to see if the user is tom and the password is correct.
				if(username.equals("Tom")) {
					dataOutputStream.writeUTF("username received.");
					dataInputStream.reset();
					password = dataInputStream.readUTF();

					// To hard code the password is bad practice; will fix later.
					if(password.equals("tomato")) {
						dataOutputStream.writeUTF("Login Successful.");
						login = Status.Active;
					}
				} else {
					dataOutputStream.writeUTF("Invalid username.");
				}

				received = "";

				// If the client type the 'exit' button 
				if (received.equals("exit")) {
					System.out.println("Client " + this.socket + " sends exit...");
					System.out.println("Closing the connection.");
					this.socket.close();
					System.out.println("Connection closed");
					break;
				}

				if(login.equals(Status.Active)) {
					dataOutputStream.writeUTF("Enter Data | Time .");
					received = dataInputStream.readUTF();

					// creating data object 
					Date date = new Date(); 

					// write on output stream based on the answer of the client 
					switch (received) {
						case "Date":
							toreturn = fordate.format(date);
							dataOutputStream.writeUTF(toreturn);
							break;
						case "Time":
							toreturn = fortime.format(date);
							dataOutputStream.writeUTF(toreturn);
							break;
						default:
							dataOutputStream.writeUTF("Invalid input.");
							break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			// closing resources
			this.dataInputStream.close();
			this.dataOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

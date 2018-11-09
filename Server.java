// November 8, 2018 

import java.io.*; 
import java.text.*; 
import java.util.*; 
import java.net.*; 

public class Server {
	public static void main(String[] args) throws IOException {

		// Server is listening
		ServerSocket serverSocket = new ServerSocket(5000);

		// running infinit loop for getting client request
		while (true) {

			Socket socket = null;

			try {

				// Socket object to receive incoming client request
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

	class ClientHandler extends thread {

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

			while (true) {

				try {
					
					// ask the user what he/she wants 
					
					// receive the answer from client 

					// creating data object 

					// write on output stream based on the answer of the client 


				} catch (Exception e) {
					//TODO: handle exception
				}


			}


		}

	}
}
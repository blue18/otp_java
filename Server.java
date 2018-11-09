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

				// obtaining input and output client requests 

				// create a new thread object 

				// invoking the start() method 

			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		
	}
}
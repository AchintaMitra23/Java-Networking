package tcp_networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_Server {
	
	public static ServerSocket ss = null;
	public static Socket clientSocket = null;
	public static DataInputStream din = null;
	public static DataOutputStream dout = null;
	public static int num = 0;

	public TCP_Server(int port) throws Exception {
		System.out.println("\t\t Server has started...");
		ss = new ServerSocket(port);
		System.out.println("\t\t Server is waiting for Client to respond...");
		clientSocket = ss.accept();
		System.out.println("\t\t Connection has been establised...");
		din = new DataInputStream(clientSocket.getInputStream());
		dout = new DataOutputStream(clientSocket.getOutputStream());
	}

	public static void main(String[] args) throws Exception {
		TCP_Server server = new TCP_Server(9999);
		server.start();
	}

	private void start() throws NumberFormatException, IOException {
		num = Integer.parseInt(din.readUTF());
		System.out.println("\n\t\t\t\t Server received from Client : " + num);
		int fact = 1;
		for (int i = 1; i <= num; i++) {
			fact = (i * fact);
		}
		dout.writeUTF(String.valueOf(fact));
		System.out.println("\t\t\t\t Sending the result to client...");
		dout.flush();
		dout.close();
		ss.close();
 	}

}

package tcp_networking;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TCP_Client {
	
	public static Socket serverSocket = null;
	public static DataInputStream din = null;
	public static DataOutputStream dout = null;
	public static Scanner scan = null;

	public TCP_Client(String ip, int port) throws Exception {
		serverSocket = new Socket(ip, port);
		System.out.println("\t\t Client is connected to Server...");
		din = new DataInputStream(serverSocket.getInputStream());
		dout = new DataOutputStream(serverSocket.getOutputStream());
		scan = new Scanner(System.in);
	}

	public static void main(String[] args) throws Exception {
		TCP_Client client = new TCP_Client("localhost", 9999);
		client.start();
	}

	private void start() throws IOException {
		System.out.println("\n\t\t\t\t Enter the number to check factorial : ");
		int num = scan.nextInt();
		dout.writeUTF(String.valueOf(num));
		System.out.println("\t\t\t\t The number has been sent to Server...");
		int result = Integer.parseInt(din.readUTF());
		System.out.println("\t\t\t\t The result recieved from Server side is : " + result);
		dout.flush();
		dout.close();
		serverSocket.close();
	}

}

package udp_networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDP_Client {
	
	public static DatagramSocket ds = null;
	public static DatagramPacket dp = null;
	public static DatagramPacket dp1 = null;
	public static Scanner scan = null;
	public static int num = 0;

	public UDP_Client(InetAddress ip, int port) throws Exception {
		ds = new DatagramSocket();
		scan = new Scanner(System.in);
		System.out.println("\n\t\t\t\t Enter the number to check factorial : ");
		num = scan.nextInt();
		
		byte[] bit = String.valueOf(num).getBytes();
		byte[] bit1 = new byte[1024];
		
		dp = new DatagramPacket(bit, bit.length, ip, port);
		dp1 = new DatagramPacket(bit1, bit1.length);
	}

	public static void main(String[] args) throws Exception {
		InetAddress inet = InetAddress.getByName("localhost");
		UDP_Client client = new UDP_Client(inet, 9999);
		client.start();
	}

	private void start() throws Exception {
		ds.send(dp);
		System.out.println("\n\t\t Datagram Packet has been send from Client...");
		ds.receive(dp1);
		String str = new String(dp1.getData(), 0, dp1.getLength());
		System.out.println("\t\t Datagram Packet1 has been recieved from Server...");
		System.out.println("\t\t The result from server side is : " + str.trim());
	}
}

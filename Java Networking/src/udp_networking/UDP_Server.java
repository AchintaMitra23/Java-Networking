package udp_networking;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Server {
	
	public static DatagramSocket ds = null; 
	public static DatagramPacket dp = null;
	public static DatagramPacket dp1 = null;
	public static int fact = 1;

	public UDP_Server(InetAddress ip, int port) throws Exception {
		ds = new DatagramSocket(port);
		
		byte[] bit = new byte[1024];
		
		dp = new DatagramPacket(bit, bit.length);
	}

	public static void main(String[] args) throws Exception {
		InetAddress inet = InetAddress.getByName("localhost");
		UDP_Server server = new UDP_Server(inet, 9999);
		server.start(inet, 9999);
	}

	private void start(InetAddress ip, int port) throws Exception {
		ds.receive(dp);
		String str = new String(dp.getData(), 0, dp.getLength());
		System.out.println("The number is received from Client side is : " + str.trim());
		
		int num = Integer.parseInt(str.trim());
		
		for (int i = 1; i <= num; i++) {
			fact = (i * fact);
		}
		byte[] bit1 = String.valueOf(fact).getBytes();
		dp1 = new DatagramPacket(bit1, bit1.length, ip, port);
		ds.send(dp1);
		System.out.println("The result is send to Client side... : " + fact);
	}

}

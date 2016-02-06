package org.sales.amd.cc.dynProxy;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.sales.amd.cc.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContextManager extends Thread {
	static Logger logger = LoggerFactory.getLogger(ContextManager.class);
	private static Map<String, Client> unproxifiedContext = new ReplicatedMap<String, Client>();
	private static Map<String, Client> context = (Map<String, Client>) ReplicatedMapHandler
			.newInstance(unproxifiedContext);
	private static boolean started = false;
	private static UUID myUUID = UUID.randomUUID();
	private static MulticastSocket ms = null;

	public static Map<String, Client> getContext() {
		if (started == false) {
			started = true;
			new ContextManager().start();
		}
		return context;
	}

	public void run() {
		logger.info("Discovery thread started!");
		try {
			InetAddress addr = InetAddress.getByName(new String("224.10.10.1"));
			ms = new MulticastSocket(4455);
			ms.joinGroup(addr);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Date lastHB = new Date();
		while (true) {
			// HeartBeat si pas fait depuis 2secondes
			if (Math.abs(lastHB.getTime()-new Date().getTime())/1000 > 2) {
				send_multicast_message(myUUID.toString());
				lastHB = new Date();
			}
			
			DatagramPacket p = new DatagramPacket(new byte[256], 256);
			try {
				ms.setSoTimeout(1000);
				ms.receive(p);
			} catch (IOException e) {
				if (!(e instanceof SocketTimeoutException))
					e.printStackTrace();
			}
			if (p.getAddress() != null) { // on a reçu un message
				logger.info("Message received: " + new String(p.getData()));
				unproxifiedContext.put("ECHOTEST", new Client("ECHO", "EchoCity", "echo@gmail.com"));
			}
			else // on a timeout
				; // si on veut gérer le cas du timeout
		}
	}

	public static boolean send_multicast_message(String str) {
		MulticastSocket ms = null;
		
		byte[] buf = new byte[256];
		buf = str.getBytes();
		try {
			InetAddress addr = InetAddress.getByName(new String("224.10.10.1"));
			ms = new MulticastSocket(4455);
			ms.joinGroup(addr);
			DatagramPacket p = new DatagramPacket(buf, buf.length, addr, 4455);
			logger.info("Sending HeartBeat");
			ms.send(p);
			ms.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (true);
	}

	public static void main(String[] args) {
		MulticastSocket ms = null;
		ContextManager.getContext();
		byte[] buf = new byte[256];
		buf = new String("TOTO MULTICAST").getBytes();

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			InetAddress addr = InetAddress.getByName(new String("224.10.10.1"));
			ms = new MulticastSocket(4455);
			ms.joinGroup(addr);
			DatagramPacket p = new DatagramPacket(buf, buf.length, addr, 4455);
			logger.info("Sending TOTO");
			ms.send(p);
			ms.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ContextManager.getContext().put("TOTO", new Client("Toto", "TotoCity", "toto@gmail.com"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Get TOTO: " + ContextManager.getContext().get("TOTO").toString());
		logger.info("Get ECHOTEST: " + ContextManager.getContext().get("ECHOTEST").toString());
	}

}

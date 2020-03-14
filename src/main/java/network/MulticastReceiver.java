package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastReceiver extends Thread {
    protected MulticastSocket socket = null;
    protected byte[] buf = new byte[256];

    public void run() {
        try {
            System.out.println("Starting multicast server...");
            socket = new MulticastSocket(4446);
            var group = InetAddress.getByName("230.0.0.0");
            socket.joinGroup(group);
            while (true) {
                var packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                var received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(String.format("Client [%s] Message: %s", packet.getAddress(), received));
                if ("end".equals(received)) {
                    break;
                }
            }
            socket.leaveGroup(group);
            socket.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

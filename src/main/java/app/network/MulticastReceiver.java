package app.network;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class MulticastReceiver extends Thread {
    protected static final String BROADCAST_ADDRESS = "230.0.0.0";
    protected static final int BROADCAST_PORT = 30000;

    private NetworkInterface networkInterface;
    private MulticastSocket socket = null;
    private byte[] buf = new byte[256];

    public MulticastReceiver(NetworkInterface network) {
            networkInterface = network;
    }

    public void run() {
        try {
            System.out.println("Starting multicast server...");

            socket = new MulticastSocket(BROADCAST_PORT);
            socket.setInterface(networkInterface.getInterfaceAddresses().get(0).getAddress());

            var group = InetAddress.getByName(BROADCAST_ADDRESS);
            socket.joinGroup(group);

            while (true) {
                var packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                var received = new String(packet.getData(), 0, packet.getLength());

                System.out.println(String.format("Client [%s] Message: %s", packet.getAddress(), received));
                if ("EXIT".equals(received)) {
                    System.out.println("Received EXIT message. Shutting down receiver.");
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

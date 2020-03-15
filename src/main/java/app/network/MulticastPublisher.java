package app.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastPublisher {
    private DatagramSocket socket;
    private InetAddress group;
    private byte[] buf;

    public void send(String message) throws IOException {
        socket = new DatagramSocket();
        group = InetAddress.getByName(MulticastReceiver.BROADCAST_ADDRESS);
        buf = message.getBytes();

        System.out.println(String.format("Publishing message to multicast group: %s", message));

        var packet = new DatagramPacket(buf, buf.length, group, MulticastReceiver.BROADCAST_PORT);
        socket.send(packet);

        socket.close();
    }
}

package app;

import app.network.MulticastPublisher;
import app.network.MulticastReceiver;
import app.network.Network;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Starting Java Multicast Example!");

        var console = System.console();
        var interfaces = Network.GetActiveInterfaces();

        System.out.println("Getting list of adapters...");
        for (var i = 0; i < interfaces.size(); i++) {
            var item = interfaces.get(i);
            System.out.println(String.format("%d. %s", i, item.getDisplayName()));
        }

        var adapterIndex = console.readLine("What app.network adapter would you like to use? ");
        var receiver = new MulticastReceiver(interfaces.get(Integer.parseInt(adapterIndex)));
        receiver.start();

        var publisher = new MulticastPublisher();
        while(true) {
            var input = console.readLine("What message would you like to send? ");
            publisher.send(input);

            Thread.sleep(2000);
        }
    }
}

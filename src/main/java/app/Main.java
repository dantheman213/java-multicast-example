package app;

import network.MulticastReceiver;
import views.FrameMain;

public class Main {
    public static void main(String[] args) throws Exception {
        var receiver = new MulticastReceiver();
        receiver.start();

        var frame = new FrameMain();
        frame.show();
    }
}

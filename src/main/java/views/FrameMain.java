package views;

import network.MulticastPublisher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FrameMain {
    private JButton buttonPing;
    private JTextField textMessage;

    public void show() throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        var frame = new JFrame("Multicast Networking Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,80);

        var layout = new BorderLayout();
        frame.setLayout(layout);

        textMessage = new JTextField("Hello World!");
        frame.add(textMessage, BorderLayout.NORTH);

        buttonPing = new JButton("Send Ping");
        buttonPing.addActionListener(buttonPingPressed());
        frame.add(buttonPing, BorderLayout.SOUTH);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null); // will center form to screen
        frame.setVisible(true);
    }

    private ActionListener buttonPingPressed() {
        return new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    var publish = new MulticastPublisher();
                    publish.send(textMessage.getText());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        };
    }
}

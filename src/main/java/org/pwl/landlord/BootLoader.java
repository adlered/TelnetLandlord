package org.pwl.landlord;

import org.pwl.telnet.TelnetUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BootLoader {
    public static int port = 23;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            Logger.log("Listening on " + serverSocket.getLocalSocketAddress());
            while (true) {
                Socket socket = serverSocket.accept();
                TelnetUtil telnetUtil = new TelnetUtil(socket);
                new Thread(telnetUtil).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

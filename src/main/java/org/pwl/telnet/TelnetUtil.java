package org.pwl.telnet;

import java.net.Socket;

public class TelnetUtil implements Runnable {
    private Socket socket;

    public TelnetUtil(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        RunningConfig runningConfig = new RunningConfig();
        Trans.send(socket, runningConfig.encode, "Welcome to Telnet Landlord! What is your system?\r\n");
        Trans.send(socket, runningConfig.encode, "1: Windows (GB2312)\r\n");
        Trans.send(socket, runningConfig.encode, "2: Linux/macOS (UTF-8)\r\n");
        String quote = Trans.input(socket, runningConfig.encode);
        if (quote.length() > 1) {
            runningConfig.encode = "UTF-8";
        } else if (quote.equals("1")) {
            runningConfig.encode = "GB2312";
        } else if (quote.equals("2")) {
            runningConfig.encode = "UTF-8";
        }

    }
}

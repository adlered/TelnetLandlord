package org.pwl.telnet;

import java.io.IOException;
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
        } else {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        Trans.send(socket, runningConfig.encode, "================================\r\n");
        Trans.send(socket, runningConfig.encode, "=    鱼油，欢迎来到斗地主！\r\n");
        Trans.send(socket, runningConfig.encode, "================================\r\n");
        Trans.send(socket, runningConfig.encode, "请选择登录方式：\r\n");
        Trans.send(socket, runningConfig.encode, "1. 摸鱼派 (https://pwl.icu) 社区账号登录\r\n");
        String login = Trans.input(socket, runningConfig.encode);
        if (login.equals("1")) {
            Trans.send(socket, runningConfig.encode, "请输入摸鱼派社区用户名\r\n");
            String username = Trans.input(socket, runningConfig.encode);
            Trans.send(socket, runningConfig.encode, "请输入摸鱼派社区密码\r\n");
            String password = Trans.input(socket, runningConfig.encode);
        } else {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
    }
}

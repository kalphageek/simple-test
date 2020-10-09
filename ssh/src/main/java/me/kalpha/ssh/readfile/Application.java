package me.kalpha.ssh.readfile;

import com.jcraft.jsch.*;

import java.io.*;
import java.util.Properties;

public class Application {
    public static void main(String[] args) {
        String username = "jjd";
        String password = "123qwe";
//        String privateKeyPath = "C:\\Users\\kalph\\.ssh\\id_rsa";
        int port = 22;
        String hostname = "api2.deogi";

        System.out.println("Connecting to " + hostname);
        JSch jSch = new JSch();
        Session session = null;
        try {
//            jsch.addIdentity(privateKeyPath);
            session = jSch.getSession(username, hostname, port);
//            session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
            session.setPassword(password);

            // 세션과 관련된 정보를 설정한다.
            Properties config = new Properties();
            // 호스트 정보를 검사하지 않는다.
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
        } catch (JSchException e) {
            e.printStackTrace();
        }

        ChannelSftp channel = null;
        try {
            session.connect();
            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedOutputStream buff = new BufferedOutputStream(outputStream);
            channel.cd("/home/jjd/");
            channel.get("hello.txt", buff);

            System.out.println(outputStream.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }
}

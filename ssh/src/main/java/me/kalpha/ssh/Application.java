package me.kalpha.ssh;

import com.jcraft.jsch.*;

import java.io.File;
import java.io.InputStreamReader;

public class Application {
    public static void main(String[] args) {
        String username = "jjd";
        String hostname = "api2.deogi";
        int port = 22;
        String password = "123qwe";
//        String privateKeyPath = "C:\\Users\\kalph\\.ssh\\id_rsa";

        System.out.println("==> Connecting to " + hostname);
        Session session = null;
        JSch jsch = new JSch();

        try {
//            jsch.addIdentity(privateKeyPath);
            session = jsch.getSession(username, hostname, port);

//            session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
            session.setPassword(password);

            // 세션과 관련된 정보를 설정한다.
            java.util.Properties config = new java.util.Properties();
            // 호스트 정보를 검사하지 않는다.
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
        } catch (JSchException e) {
            throw new RuntimeException("Failed to create Jsch Session object.", e);
        }

        Channel channel = null;
        try {
            session.connect();

            channel = session.openChannel("exec");  //채널접속
            ChannelExec channelExec = (ChannelExec) channel; //명령 전송 채널사용
            channelExec.setCommand("ls"); //실행시킬 명령어 입력

            //콜백을 받을 준비.
            StringBuilder outputBuffer = new StringBuilder();
            InputStreamReader in = new InputStreamReader(channel.getInputStream());
            ((ChannelExec) channel).setErrStream(System.err);

            channel.connect();  //실행
            int i = 0;
            while ((i=in.read()) != -1) {
                outputBuffer.append((char)i);
            }
            System.out.println(outputBuffer);
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

package me.kalpha.ssh.download;

import com.jcraft.jsch.*;

import java.io.File;
import java.util.Properties;
import java.util.Vector;

public class Application {
    private static final String CURRENT_DIR = ".";
    private static final String PARENT_DIR = "..";

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
            System.out.println("Connected.");
            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();

            downloadDirectory(channel, "/home/jjd/tql", "\\temp", username);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.disconnect();
                System.out.println("Disconnected.");
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    private static void downloadDirectory(ChannelSftp channelSftp, String remotePath, String localPath, String user) throws SftpException {
        @SuppressWarnings("unchecked")
        Vector<ChannelSftp.LsEntry> childs = channelSftp.ls(remotePath);
//        changeOwnership(localPath, user);
        for (ChannelSftp.LsEntry child : childs) {
            if (child.getAttrs().isDir()) {
                if (CURRENT_DIR.equals(child.getFilename()) || PARENT_DIR.equals(child.getFilename())) {
                    continue;
                }
                new File(localPath + File.separator + child.getFilename()).mkdirs();
//                changeOwnership(localPath + File.separator + child.getFilename(), user);
                downloadDirectory(channelSftp, remotePath + File.separator + child.getFilename() + File.separator,
                        localPath + File.separator + child.getFilename(),user);
            } else {
                channelSftp.get(remotePath + File.separator + child.getFilename(),
                        localPath + File.separator + child.getFilename());
//                changeOwnership(localPath + File.separator + child.getFilename(), user);
            }
        }
    }
}

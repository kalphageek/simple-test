package me.kalpha.ssh.download;

import com.jcraft.jsch.*;
import me.kalpha.ssh.common.CommonBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.Properties;
import java.util.Vector;

public class Application {
    private static final String CURRENT_DIR = ".";
    private static final String PARENT_DIR = "..";

    public static void main(String[] args) {
        Session session = null;
        ChannelSftp channel = null;
        String hostname = "api2.deogi";
        String username = "jjd";
        String remotePath = "/home/jjd/tql";
        String localPath = "/home/jjd/tql";
        CommonBean commonBean = new CommonBean();

        try {
            session = commonBean.getJschSession(hostname, username);
            session.connect();
            System.out.println("Connected.");
            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();

            downloadDirectory(channel, remotePath, localPath, username);

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

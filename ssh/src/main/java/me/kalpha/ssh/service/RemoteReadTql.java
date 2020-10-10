package me.kalpha.ssh.service;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import me.kalpha.ssh.common.CommonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Service
public class RemoteReadTql {
    @Autowired
    CommonBean commonBean;
    public final String CURRENT_PATH = ".";
    public final String PARENT_PATH = "..";

    public void readRemoteTql() {
        String username = "jjd";
        String hostname = "api2.deogi";
        String tqlDirectory = "/home/jjd/tql";
        Session session = null;
        ChannelSftp channel = null;

        try {
            session = commonBean.getJschSession(hostname, username);
            session.connect();
            System.out.println("Connected");

            channel = (ChannelSftp)session.openChannel("sftp");
            channel.connect();

            List<String> tqlList = findTql(channel, tqlDirectory);
            for (String tql: tqlList) {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                BufferedOutputStream buff = new BufferedOutputStream(outputStream);
                channel.get(tql, buff);
                System.out.println("Tql file : " + tql);
                System.out.println(outputStream.toString());
                System.out.println("-------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.disconnect();
            }
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

    private List<String> findTql(ChannelSftp channelSftp, String remotePath) throws SftpException {
        List<String> tqlList = new ArrayList<>();
        Vector<ChannelSftp.LsEntry> childs = channelSftp.ls(remotePath);
        for (ChannelSftp.LsEntry child : childs) {
            if (child.getAttrs().isDir()) {
                if (CURRENT_PATH.equals(child.getFilename()) || PARENT_PATH.equals(child.getFilename())) {
                    continue;
                } else {
                    tqlList.addAll(findTql(channelSftp, remotePath + File.separator + child.getFilename()));
                }
            } else {
                tqlList.add(remotePath + File.separator + child.getFilename());
            }
        }

        return tqlList;
    }
}

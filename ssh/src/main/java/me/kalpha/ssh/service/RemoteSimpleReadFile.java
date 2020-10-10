package me.kalpha.ssh.service;

import com.jcraft.jsch.*;
import me.kalpha.ssh.common.CommonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Properties;

@Service
public class RemoteSimpleReadFile {
    @Autowired
    CommonBean commonBean;

    public void readSimpleFile() {
        Session session = null;
        ChannelSftp channel = null;
        String hostname = "api2.deogi";
        String username = "jjd";
        String remotePath = "/home/jjd";

        try {
            session = commonBean.getJschSession(hostname, username);
            session.connect();
            channel = (ChannelSftp) session.openChannel("sftp");
            channel.connect();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedOutputStream buff = new BufferedOutputStream(outputStream);
//            channel.cd(remotePath);
            channel.get(remotePath+File.separator+"hello.txt", buff);

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

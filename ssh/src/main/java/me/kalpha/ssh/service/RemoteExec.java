package me.kalpha.ssh.service;

import com.jcraft.jsch.*;
import me.kalpha.ssh.common.CommonBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.Properties;

@Service
public class RemoteExec {
    @Autowired
    CommonBean commonBean;

    public void execRemoteCommand() {
        Logger log = LoggerFactory.getLogger(this.getClass());

        Session session = null;
        Channel channel = null;
        String hostname = "api2.deogi";
        String username = "jjd";

        try {
            session = commonBean.getJschSession(hostname, username);
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

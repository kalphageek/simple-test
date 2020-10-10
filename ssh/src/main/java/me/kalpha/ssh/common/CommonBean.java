package me.kalpha.ssh.common;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class CommonBean {
    public Session getJschSession(String hostname, String username) throws JSchException {
        String privateKeyPath = "~/.ssh/id_rsa";
        int port = 22;

        System.out.println("Connecting to " + hostname);
        Session session = null;
        JSch jSch = new JSch();
        jSch.addIdentity(privateKeyPath);
        session = jSch.getSession(username, hostname, port);
        session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
//            session.setPassword(password);

        Properties config = new Properties();
        // 호스트 정보를 검사하지 않는다.
        config.put("StrictHostKeyChecking", "no");
        session.setConfig(config);

        return session;
    }
}

package me.kalpha.ssh.runner;

import me.kalpha.ssh.service.RemoteSftp;
import me.kalpha.ssh.service.RemoteExec;
import me.kalpha.ssh.service.RemoteSimpleFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SshRunner implements ApplicationRunner {
    @Autowired
    RemoteExec remoteExec;
    @Autowired
    RemoteSimpleFile remoteSimpleFile;
    @Autowired
    RemoteSftp remoteSftp;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        remoteExec.execCommand();
        remoteSimpleFile.readFile();
        remoteSftp.downloadDirectory();
    }
}

package me.kalpha.ssh.runner;

import me.kalpha.ssh.service.RemoteExec;
import me.kalpha.ssh.service.RemoteReadTql;
import me.kalpha.ssh.service.RemoteSimpleReadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class SshRunner implements ApplicationRunner {
    @Autowired
    RemoteExec remoteExec;
    @Autowired
    RemoteSimpleReadFile simpleReadFile;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        remoteExec.execRemoteCommand();
        simpleReadFile.readSimpleFile();
    }
}

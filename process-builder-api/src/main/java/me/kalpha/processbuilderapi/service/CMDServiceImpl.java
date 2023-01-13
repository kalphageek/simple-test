package me.kalpha.processbuilderapi.service;

import me.kalpha.processbuilderapi.common.CMDConfiguration;
import me.kalpha.processbuilderapi.tools.util.CMDUtil;
import me.kalpha.processbuilderapi.vo.CMDResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CMDServiceImpl implements CMDService {
    private CMDConfiguration cMDConfiguration;

    public CMDServiceImpl(CMDConfiguration cMDConfiguration) {
        this.cMDConfiguration = cMDConfiguration;
    }
    @Override
    public CMDResponse cat(String fileName) throws IOException, InterruptedException {
        return runCommand(cMDConfiguration.getCat()+"/"+fileName);
    }

    private CMDResponse runCommand(String command) throws IOException, InterruptedException {
        return CMDUtil.command(command);
    }
}

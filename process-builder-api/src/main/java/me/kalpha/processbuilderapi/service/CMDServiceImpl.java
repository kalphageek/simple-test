package me.kalpha.processbuilderapi.service;

import me.kalpha.processbuilderapi.common.CMDConfiguration;
import me.kalpha.processbuilderapi.common.CMDException;
import me.kalpha.processbuilderapi.tools.util.CMDUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class CMDServiceImpl implements CMDService {
    private CMDConfiguration cMDConfiguration;

    public CMDServiceImpl(CMDConfiguration cMDConfiguration) {
        this.cMDConfiguration = cMDConfiguration;
    }
    @Override
    public Map<String, String> cat(String fileName) throws IOException, InterruptedException, CMDException {
        return runCommand(cMDConfiguration.getCat()+"/"+fileName);
    }

    private Map<String, String> runCommand(String command) throws IOException, InterruptedException, CMDException {
        return CMDUtil.command(command);
    }
}

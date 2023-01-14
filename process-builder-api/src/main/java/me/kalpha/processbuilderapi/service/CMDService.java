package me.kalpha.processbuilderapi.service;

import me.kalpha.processbuilderapi.common.CMDException;

import java.io.IOException;
import java.util.Map;

public interface CMDService {
    public Map<String,String> cat(String fileName) throws IOException, InterruptedException, CMDException;
}

package me.kalpha.processbuilderapi.service;

import me.kalpha.processbuilderapi.vo.CMDResponse;

import java.io.IOException;

public interface CMDService {
    public CMDResponse cat(String fileName) throws IOException, InterruptedException;
}

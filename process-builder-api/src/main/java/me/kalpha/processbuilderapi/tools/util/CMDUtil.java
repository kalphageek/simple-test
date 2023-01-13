package me.kalpha.processbuilderapi.tools.util;

import lombok.extern.slf4j.Slf4j;
import me.kalpha.processbuilderapi.common.CMDException;
import me.kalpha.processbuilderapi.vo.CMDResponse;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Slf4j
public class CMDUtil {
    public static CMDResponse command(String command) throws IOException, InterruptedException, CMDException {
        CMDResponse response = new CMDResponse();

        log.info("Tne command is running [{}]", command);
        String homeDirectory =  System.getProperty("user.home");
        log.info("Home directory is {}", homeDirectory);
        String os = System.getProperty("os.name");
        Boolean isWindow = os.toLowerCase().startsWith("window");
        String codePage = "UTF-8";

        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(new File(homeDirectory));

        byte[] buffer = new byte[1000];
        ByteArrayOutputStream outputStream;
        ByteArrayOutputStream errorStream;
        String body = null;

        if (isWindow) {
            builder.command("cmd.exe", "/c", command);
            codePage = "CP949";
        } else {
            if (os.equals("HP-UX")) {
            } else { // Linux
            }
            builder.command("bash", "-c", command);
        }

        Process process = null;
        try {
            process = builder.start();
            int temp;
            outputStream = new ByteArrayOutputStream();

            while ((temp = process.getInputStream().read(buffer)) != -1) {
                outputStream.write(buffer, 0, (char)temp);
            }
            body = new String(outputStream.toByteArray(), codePage);

            int exitCode = process.waitFor();
            if (exitCode == 0) {
               response.setStatus("200");
               response.setResponseMessage(body);
            } else {
                errorStream = new ByteArrayOutputStream();

                while ((temp = process.getErrorStream().read(buffer)) != 1) {
                    errorStream.write(buffer, 0, (char)temp);
                }
                body = new String(errorStream.toByteArray(), codePage);

                throw new CMDException("["+command+"] "+body);
            }
        } catch (Exception e) {
            log.error("["+command+"] "+e.toString());
            throw e;
        } finally {
            if (process != null) {
                try {
                    process.getErrorStream().close();
                    process.getInputStream().close();
                    process.getOutputStream().close();
                    process.destroy();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        log.info("The command is finished");
        return response;
    }
}

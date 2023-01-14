package me.kalpha.processbuilderapi.tools.util;

import lombok.extern.slf4j.Slf4j;
import me.kalpha.processbuilderapi.common.Constants;
import me.kalpha.processbuilderapi.common.CMDException;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CMDUtil {
    public static Map<String, String> command(String command) throws IOException, InterruptedException, CMDException {
        Map<String, String> map = new HashMap<>();

        log.info("Tne command is running [{}]", command);
        String homeDirectory =  System.getProperty("user.home");
        log.info("Home directory is {}", homeDirectory);
        String os = System.getProperty("os.name");
        Boolean isWindow = os.toLowerCase().startsWith("window");
        String codePage = "UTF-8";

        ProcessBuilder builder = new ProcessBuilder();
        builder.directory(new File(homeDirectory));

        byte[] buffer = new byte[1024];
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
            IOUtils.copy(process.getInputStream(), outputStream);

            body = new String(outputStream.toByteArray(), codePage);

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                map.put(Constants.RESPONSE_MESSAGE, body);
            } else {
                errorStream = new ByteArrayOutputStream();

                IOUtils.copy(process.getErrorStream(), errorStream);
                body = new String(errorStream.toByteArray(), codePage);

                throw new CMDException(Constants.ExceptionClass.HANDLED, HttpStatus.BAD_REQUEST, body);
            }
        } catch (Exception e) {
            log.error(e.toString());
            throw e;
        } finally {
            if (process != null) {
                try {
                    process.getErrorStream().close();
                    process.getInputStream().close();
                    process.destroy();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        log.info("The command is finished");
        return map;
    }
}

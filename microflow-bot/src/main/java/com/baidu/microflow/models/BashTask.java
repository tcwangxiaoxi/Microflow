package com.baidu.microflow.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.java.Log;

@Log
@Getter
@ToString(callSuper = true)
public class BashTask extends Task {

    private String bashcmd;

    public BashTask(String name) {
        super(name);
    }

    public BashTask(String name, Integer retries) {
        super(name, retries);
    }

    public void bash(String bashcmd) {
        this.bashcmd = bashcmd;
    }

    public int run() {
        log.info("Run BashCommand: " + bashcmd);
        try {
            Process p = Runtime.getRuntime().exec(bashcmd);
            BufferedReader stdout = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stderr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            String line;
            while ((line = stdout.readLine()) != null) {
                System.out.println(line);
            }
            while ((line = stderr.readLine()) != null) {
                System.out.println(line);
            }
            return p.waitFor();
        } catch (IOException | InterruptedException e) {
            log.warning(e.getMessage());
        }

        return 0;
    }
}

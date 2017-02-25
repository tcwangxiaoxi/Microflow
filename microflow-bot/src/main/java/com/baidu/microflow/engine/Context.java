package com.baidu.microflow.engine;

import java.time.LocalDate;

public class Context {
    private String cwd;
    private String dataDir;

    public Context() {
    }

    public String now() {
        LocalDate now = LocalDate.now();
        return null;
    }

    public String dataDir() {
        return dataDir;
    }

    public String cwd() {
        return cwd;
    }

    public String env(String envKey) {
        return System.getenv(envKey);
    }
}

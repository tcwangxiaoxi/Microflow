package com.baidu.microflow.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import lombok.extern.java.Log;

@Log
public class ApplicationConfig {

    private static ApplicationConfig instance = new ApplicationConfig();

    private final String configFile = "microflow-bot.yml";
    private Yaml yaml;
    private Object config;

    private ApplicationConfig() {
        this.yaml = new Yaml();
        String configFilePath = System.getProperty("user.dir") + "/conf/" + configFile;
        try {
            FileReader configReader = new FileReader(configFilePath);
            config = yaml.load(configReader);
        } catch (FileNotFoundException e) {
            log.warning("ApplicationConfig file " + configFilePath + " not found");
        }
    }

    public static ApplicationConfig sharedInstance() {
        if (instance.config == null) {
            return null;
        }
        return instance;
    }

    public Object get(String key) {
        String[] slice = key.split("\\.");
        Object obj = config;
        for (String s : slice) {
            if (obj instanceof Map) {
                obj = ((Map) obj).get(s);
            } else if (obj instanceof List) {
                obj = ((List) obj).get(Integer.parseInt(s));
            }
        }
        return obj;
    }

    public String getString(String key) {
        return (String) get(key);
    }

    public Integer getInteger(String key) {
        return (Integer) get(key);
    }

    public List getList(String key) {
        return (List) get(key);
    }
}

package com.baidu.microflow.engine;

import com.baidu.microflow.dao.JobScriptDictRepository;
import com.baidu.microflow.entity.JobScriptDict;
import com.baidu.microflow.entity.ScriptDict;
import com.baidu.microflow.exceptions.CycleException;
import com.baidu.microflow.models.Job;
import com.baidu.microflow.utils.IdUtils;
import com.baidu.microflow.utils.SpringUtils;
import lombok.extern.java.Log;
import org.apache.commons.io.IOUtils;

import javax.script.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
// import scala.tools.nsc.interpreter.IMain;
// import scala.tools.nsc.settings.MutableSettings.BooleanSetting;

@Log
public class ScalaJobRunnable implements Runnable {
    private final String jobName;
    private final String jobScript;
    private final String preloadFileName = "preload.scala";
    private ScriptEngine engine;
    private Bindings bindings;


    private Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();

        // 用于绑定的源的上下文参数的持久化对象
        JobScriptDictRepository jobScriptDictRepository =
                SpringUtils.getBean(JobScriptDictRepository.class);
        List<JobScriptDict> dicts = jobScriptDictRepository.findByJobCode(jobName);

        if (dicts != null) {
            for (JobScriptDict dict : dicts) {
                ScriptDict scriptDict = dict.getScriptDict();
                params.put(scriptDict.getDictKey(), scriptDict.getDictValue());
            }
        }
        return params;
    }

    public void run() {
        engine = new ScriptEngineManager().getEngineByName("scala");
        if (engine == null) {
            log.warning(jobName + "Can not find scala engine");
            throw new RuntimeException("Can not find scala engine");
        }

        /*
         * Scala script should run with java classpath. Solution:
         * http://henning.kropponline.de/2016/04/17/scripting-scala-jsr-223/
         */
        //        ((BooleanSetting)(((IMain)engine).settings().usejavacp()))
        //                .value_$eq(true);

        Job job = new Job(jobName, jobScript);
        // 添加JobId的UUID，该ID用于跟踪相同任务重复执行的任务，以及执行脚本的路径
        job.setId(IdUtils.genUUID());

        bindings = new SimpleBindings();
        bindings.put("_job", job);
        // 绑定调度对应资源的上下文参数信息
        bindings.put("_dict", getParams());

        engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();

        // execute preload script first
        try {
            String preload = IOUtils.toString(
                    new InputStreamReader(
                            classloader.getResourceAsStream(preloadFileName)));

            if (!eval(preload) || !eval(jobScript)) {
                return;
            }

            JobEngine jobEngine = new JobEngine(job);
            jobEngine.run();
        } catch (IOException | NullPointerException | CycleException e) {
            e.printStackTrace();
            log.warning(jobName + " failed. " + e.getMessage());
        } finally {
            System.gc();
        }
    }

    public ScalaJobRunnable(String jobName, String jobScript) {
        this.jobName = jobName;
        this.jobScript = jobScript;
    }

    private boolean eval(String script) {
        Scanner scanner = new Scanner(script);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            try {
                log.info(jobName + " eval: " + line);
                engine.eval(line, bindings);
            } catch (ScriptException e) {
                log.warning(jobName + " eval fail on line: " + line);
                log.warning(jobName + " failed. " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}

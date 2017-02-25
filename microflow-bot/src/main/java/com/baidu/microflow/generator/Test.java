package com.baidu.microflow.generator;

import com.baidu.microflow.engine.ScalaJobRunnable;
import com.baidu.microflow.utils.SpringUtils;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by wangxiaoxi03 on 2017/2/23.
 */
@SpringBootApplication
public class Test {

    public static final String jobName = "0001";

    @Bean
    public TestStartupRunner testStartupRunner() {
        return new TestStartupRunner();
    }

    public static void main(String[] args) throws IOException, ScriptException {
        SpringUtils.initSpringApplicationContext(Test.class, args);
        test1(args);
//        test2();
    }

    public static void test1(String[] args) throws IOException {
//        String pathStr = "D:\\workspace\\baidu\\microflow\\microflow-bot\\src\\main\\resources\\testFlowByDB.scala";
        String pathStr = args[0];
        Path path = FileSystems.getDefault().getPath(pathStr);
        String script = new String(Files.readAllBytes(path));

        ScalaJobRunnable scalaJobRunnable = new ScalaJobRunnable(
                jobName, script);
        Thread scalaJobThread = new Thread(scalaJobRunnable);
        scalaJobThread.start();
    }

    public static void test2() {

        System.out.println("++++++++++++" + Thread.currentThread().getContextClassLoader().equals(Test.class.getClassLoader()));

        ScriptEngineManager sem = new ScriptEngineManager();

        String script_scala =
//                "import com.baidu.microflow.models.{Job, BashTask, QETask}";
//                "import com.baidu.microflow.generator.TaskFactory \r\n"+
//                "Thread.currentThread().getContextClassLoader.equals(this.getClass.getClassLoader)";
//                "this.getClass.getClassLoader.toString";
//                "Thread.currentThread().getContextClassLoader.toString";
                "Thread.currentThread().setContextClassLoader(org.springframework.boot.loader.LaunchedURLClassLoader.getClass.getClassLoader)";
        ScriptEngine se_scala = sem.getEngineByName("scala");
        /*((MutableSettings.BooleanSetting) (((IMain) se_scala).settings().usejavacp()))
                .value_$eq(true);*/
        try {
            System.out.println("======================================" + se_scala.eval(script_scala));
        } catch (ScriptException e) {
            e.printStackTrace();
        }


        script_scala = "import com.baidu.microflow.models.{Job, BashTask, QETask}";
        try {
            System.out.println("======================================" + se_scala.eval(script_scala));
        } catch (ScriptException e) {
            e.printStackTrace();
        }

    }
}

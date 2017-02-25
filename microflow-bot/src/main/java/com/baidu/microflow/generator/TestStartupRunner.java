package com.baidu.microflow.generator;

import com.baidu.microflow.dao.JobScriptDictRepository;
import com.baidu.microflow.dao.ScriptDictRepository;
import com.baidu.microflow.dao.ScriptResourceRepository;
import com.baidu.microflow.entity.JobScriptDict;
import com.baidu.microflow.entity.ScriptDict;
import com.baidu.microflow.entity.ScriptResource;
import com.google.common.collect.Lists;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangxiaoxi03 on 2017/2/24.
 */
@Order(Ordered.LOWEST_PRECEDENCE - 20)
public class TestStartupRunner implements CommandLineRunner {

    @Resource
    private JobScriptDictRepository jobScriptDictRepository;

    @Resource
    private ScriptDictRepository scriptDictRepository;

    @Resource
    private ScriptResourceRepository scriptResourceRepository;

    public void run(String... strings) throws Exception {
        // 初始化，添加资源文件
        ScriptResource scriptResource = new ScriptResource();
        scriptResource.setName("date");
        scriptResource.setOriginalScript("date > date.txt");
        scriptResourceRepository.save(scriptResource);

        // 添加字典项
        ScriptDict sd1 = new ScriptDict(Test.jobName + "_type", "bash.sh");
        ScriptDict sd2 = new ScriptDict(Test.jobName + "_sql", "select * from wang");

        // 添加字典项与调度任务的关系映射
        List<JobScriptDict> lists = Lists.newArrayList(
                new JobScriptDict(Test.jobName, sd1),
                new JobScriptDict(Test.jobName, sd2)
        );
        jobScriptDictRepository.save(lists);
    }
}

package com.baidu.microflow.dao;

import com.baidu.microflow.entity.JobScriptDict;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by wangxiaoxi03 on 2017/2/21.
 */
public interface JobScriptDictRepository extends CrudRepository<JobScriptDict, Long> {

    List<JobScriptDict> findByJobCode(String jobCode);
}

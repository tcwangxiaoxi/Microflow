package com.baidu.microflow.dao;

import com.baidu.microflow.entity.JobTrace;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangcuibao on 2016/11/24.
 */

@Transactional
public interface JobTraceDao extends CrudRepository<JobTrace, Integer> {
}

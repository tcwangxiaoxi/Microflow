package com.baidu.microflow.dao;

import com.baidu.microflow.entity.Job;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by zhangcuibao on 2016/11/3.
 */

public interface JobDao extends PagingAndSortingRepository<Job, Integer> {

    List<Job> findByJobName(String jobName);

    @Transactional
    default void updateActiveById(int active, Date time, int id){
        Job one = findOne(id);
        one.setActive(active);
        one.setUpdateTime(time);
        save(one);
    }
}

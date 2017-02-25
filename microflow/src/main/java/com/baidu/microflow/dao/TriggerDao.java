package com.baidu.microflow.dao;

import com.baidu.microflow.entity.Trigger;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 * Created by zhangcuibao on 2016/11/3.
 */
@Transactional
public interface TriggerDao extends CrudRepository<Trigger, Integer> {
}

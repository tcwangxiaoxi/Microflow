package com.baidu.microflow.dao;

import com.baidu.microflow.entity.ScriptResource;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by wangxiaoxi03 on 2017/2/21.
 */
public interface ScriptResourceRepository extends CrudRepository<ScriptResource, Long> {

    ScriptResource findOneByName(String name);
}

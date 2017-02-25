package com.baidu.microflow.entity;

import com.baidu.microflow.entity.base.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Created by wangxiaoxi03 on 2017/2/21.
 */
@Entity
@Data
public class JobScriptDict extends AbstractEntity {

    private String jobCode;

    public JobScriptDict() {
    }

    public JobScriptDict(String jobCode, ScriptDict scriptDict) {
        this.jobCode = jobCode;
        this.scriptDict = scriptDict;
    }

    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "dict_id")
    private ScriptDict scriptDict;


}

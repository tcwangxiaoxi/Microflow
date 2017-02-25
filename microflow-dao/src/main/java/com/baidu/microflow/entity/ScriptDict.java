package com.baidu.microflow.entity;

import com.baidu.microflow.entity.base.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangxiaoxi03 on 2017/2/21.
 */
@Entity
@Data
public class ScriptDict extends AbstractEntity {

    private String dictKey;

    private String dictValue;

    @OneToMany(mappedBy = "scriptDict")
    private List<JobScriptDict> jobScriptDicts = new ArrayList<>();

    public ScriptDict() {
    }

    public ScriptDict(String dictKey, String dictValue) {
        this.dictKey = dictKey;
        this.dictValue = dictValue;
    }
}

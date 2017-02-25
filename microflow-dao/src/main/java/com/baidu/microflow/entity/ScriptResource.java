package com.baidu.microflow.entity;

import com.baidu.microflow.entity.base.AbstractEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Created by wangxiaoxi03 on 2017/2/21.
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Data
public class ScriptResource extends AbstractEntity {

    private String name;

    @Lob
    private String originalScript;

    private String version;

    private Long size;
}

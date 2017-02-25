package com.baidu.microflow.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

/**
 * Created by zhangcuibao on 2016/11/3.
 */

@Entity
@Getter
@Setter
@Table(name = "microflow_trigger")
public class Trigger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "trigger_name", unique = true, nullable = false, length = 32)
    private String triggerName;

    @Column(name = "type")
    private Integer type;

    @Column(name = "schedual", length = 32)
    private String schedual;

    @Column(name = "user_name", nullable = false, length = 32)
    private String userName;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}

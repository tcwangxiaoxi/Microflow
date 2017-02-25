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
@Table(name = "microflow_job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "job_name", unique = true, nullable = false, length = 32)
    private String jobName;

    @Column(name = "trigger_id", nullable = false)
    private Integer triggerId;

    @Column(name = "scope", length = 128)
    private String scope;

    @Column(name = "job_code", nullable = false)
    private String jobCode;

    @Column(name = "active", nullable = false)
    private Integer active;

    @Column(name = "succeed_count")
    private Integer succeedCount;

    @Column(name = "failure_count")
    private Integer failureCount;

    @Column(name = "user_name", nullable = false, length = 32)
    private String userName;

    @Column(name = "alarm_sendee", length = 128)
    private String alarmSendee;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}

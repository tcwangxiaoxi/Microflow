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
 * Created by zhangcuibao on 2016/11/23.
 */
@Entity
@Getter
@Setter
@Table(name = "microflow_job_trace")
public class JobTrace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "job_id")
    private int jobId;

    @Column(name = "trigger_id")
    private int triggerId;

    @Column(name = "status")
    private int status;

    @Column(name = "standard_time")
    private Date standardTime;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;
}

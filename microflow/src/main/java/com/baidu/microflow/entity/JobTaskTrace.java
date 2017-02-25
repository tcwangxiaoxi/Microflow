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
@Table(name = "microflow_job_task_trace")
public class JobTaskTrace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "job_trace_id")
    private int jobTraceId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "retries_count")
    private int retriesCount;

    @Column(name = "status")
    private int status;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;
}

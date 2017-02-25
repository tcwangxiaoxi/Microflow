package com.baidu.microflow.database;

import static com.baidu.microflow.generated.jooq.Tables.MICROFLOW_JOB_TASK_TRACE;
import static com.baidu.microflow.generated.jooq.Tables.MICROFLOW_JOB_TRACE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.types.UByte;
import org.jooq.types.UInteger;

import com.baidu.microflow.config.ApplicationConfig;

import lombok.extern.java.Log;

/**
 * Auther: chenxin<chenxin@baidu.com>
 */
@Log
public class JobTraceContext {

    private Connection connection;

    public JobTraceContext() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            ApplicationConfig applicationConfig = ApplicationConfig.sharedInstance();
            String connURL = String.format("jdbc:mysql://%s:%d/%s",
                applicationConfig.getString("database.host"),
                applicationConfig.getInteger("database.port"),
                applicationConfig.getString("database.database"));
            connection = DriverManager.getConnection(connURL,
                    applicationConfig.getString("database.user"),
                    applicationConfig.getString("database.password"));
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            log.warning(e.getMessage());
        }
    }

    public boolean createJobTrace(String jobName, String jobScript,
                                  LocalDateTime startTime, LocalDateTime endTime, int status) {
        if (connection == null) {
            return false;
        }
        DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
        Timestamp s = Timestamp.valueOf(startTime);
        Timestamp e = Timestamp.valueOf(endTime);
        int ret = context.insertInto(MICROFLOW_JOB_TRACE)
            .set(MICROFLOW_JOB_TRACE.JOB_NAME, jobName)
            .set(MICROFLOW_JOB_TRACE.JOB_SCRIPT, jobScript)
            .set(MICROFLOW_JOB_TRACE.JOB_ID, UInteger.valueOf(1))
            .set(MICROFLOW_JOB_TRACE.TRIGGER_ID, UInteger.valueOf(1))
            .set(MICROFLOW_JOB_TRACE.START_TIME, s)
            .set(MICROFLOW_JOB_TRACE.END_TIME, e)
            .set(MICROFLOW_JOB_TRACE.STATUS, UByte.valueOf(status))
            .execute();
        log.info("createJobTrace return " + ret);
        return true;
    }

    public boolean createTaskTrace(String taskName, int retriesCount,
                                   LocalDateTime startTime, LocalDateTime endTime, int status) {
        if (connection == null) {
            return false;
        }
        DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
        Timestamp s = Timestamp.valueOf(startTime);
        Timestamp e = Timestamp.valueOf(endTime);
        int ret = context.insertInto(MICROFLOW_JOB_TASK_TRACE)
            .set(MICROFLOW_JOB_TASK_TRACE.TASK_NAME, taskName)
            .set(MICROFLOW_JOB_TASK_TRACE.RETRIES_COUNT, UInteger.valueOf(retriesCount))
            .set(MICROFLOW_JOB_TASK_TRACE.START_TIME, s)
            .set(MICROFLOW_JOB_TASK_TRACE.END_TIME, e)
            .set(MICROFLOW_JOB_TASK_TRACE.STATUS, UByte.valueOf(status))
            .execute();
        log.info("createTaskTrace return " + ret);
        return true;
    }

//    public boolean getJobTraces() {
//        DSLContext context = DSL.using(connection, SQLDialect.MYSQL);
//        Result<Record> result = context.select().from(JOB_TRACE).fetch();
//
//        for (Record r : result) {
//            Long id = r.getValue(JOB_TRACE.ID);
//            String jobName = r.getValue(JOB_TRACE.JOB_NAME);
//            log.info("get record " + id + ", jobName: " + jobName);
//        }
//        return true;
//    }
}

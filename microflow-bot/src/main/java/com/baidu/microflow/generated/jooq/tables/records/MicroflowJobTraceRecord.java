/**
 * This class is generated by jOOQ
 */
package com.baidu.microflow.generated.jooq.tables.records;


import com.baidu.microflow.generated.jooq.tables.MicroflowJobTrace;

import java.sql.Timestamp;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.types.UByte;
import org.jooq.types.UInteger;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.8.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MicroflowJobTraceRecord extends UpdatableRecordImpl<MicroflowJobTraceRecord> implements Record8<UInteger, UInteger, UInteger, UByte, Timestamp, Timestamp, String, String> {

    private static final long serialVersionUID = -1071980988;

    /**
     * Setter for <code>microflow.microflow_job_trace.id</code>.
     */
    public void setId(UInteger value) {
        set(0, value);
    }

    /**
     * Getter for <code>microflow.microflow_job_trace.id</code>.
     */
    public UInteger getId() {
        return (UInteger) get(0);
    }

    /**
     * Setter for <code>microflow.microflow_job_trace.job_id</code>.
     */
    public void setJobId(UInteger value) {
        set(1, value);
    }

    /**
     * Getter for <code>microflow.microflow_job_trace.job_id</code>.
     */
    public UInteger getJobId() {
        return (UInteger) get(1);
    }

    /**
     * Setter for <code>microflow.microflow_job_trace.trigger_id</code>.
     */
    public void setTriggerId(UInteger value) {
        set(2, value);
    }

    /**
     * Getter for <code>microflow.microflow_job_trace.trigger_id</code>.
     */
    public UInteger getTriggerId() {
        return (UInteger) get(2);
    }

    /**
     * Setter for <code>microflow.microflow_job_trace.status</code>.
     */
    public void setStatus(UByte value) {
        set(3, value);
    }

    /**
     * Getter for <code>microflow.microflow_job_trace.status</code>.
     */
    public UByte getStatus() {
        return (UByte) get(3);
    }

    /**
     * Setter for <code>microflow.microflow_job_trace.start_time</code>.
     */
    public void setStartTime(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>microflow.microflow_job_trace.start_time</code>.
     */
    public Timestamp getStartTime() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>microflow.microflow_job_trace.end_time</code>.
     */
    public void setEndTime(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>microflow.microflow_job_trace.end_time</code>.
     */
    public Timestamp getEndTime() {
        return (Timestamp) get(5);
    }

    /**
     * Setter for <code>microflow.microflow_job_trace.job_name</code>.
     */
    public void setJobName(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>microflow.microflow_job_trace.job_name</code>.
     */
    public String getJobName() {
        return (String) get(6);
    }

    /**
     * Setter for <code>microflow.microflow_job_trace.job_script</code>.
     */
    public void setJobScript(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>microflow.microflow_job_trace.job_script</code>.
     */
    public String getJobScript() {
        return (String) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<UInteger> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<UInteger, UInteger, UInteger, UByte, Timestamp, Timestamp, String, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<UInteger, UInteger, UInteger, UByte, Timestamp, Timestamp, String, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field1() {
        return MicroflowJobTrace.MICROFLOW_JOB_TRACE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field2() {
        return MicroflowJobTrace.MICROFLOW_JOB_TRACE.JOB_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UInteger> field3() {
        return MicroflowJobTrace.MICROFLOW_JOB_TRACE.TRIGGER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<UByte> field4() {
        return MicroflowJobTrace.MICROFLOW_JOB_TRACE.STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return MicroflowJobTrace.MICROFLOW_JOB_TRACE.START_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field6() {
        return MicroflowJobTrace.MICROFLOW_JOB_TRACE.END_TIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return MicroflowJobTrace.MICROFLOW_JOB_TRACE.JOB_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return MicroflowJobTrace.MICROFLOW_JOB_TRACE.JOB_SCRIPT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value2() {
        return getJobId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UInteger value3() {
        return getTriggerId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UByte value4() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getStartTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value6() {
        return getEndTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getJobName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getJobScript();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MicroflowJobTraceRecord value1(UInteger value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MicroflowJobTraceRecord value2(UInteger value) {
        setJobId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MicroflowJobTraceRecord value3(UInteger value) {
        setTriggerId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MicroflowJobTraceRecord value4(UByte value) {
        setStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MicroflowJobTraceRecord value5(Timestamp value) {
        setStartTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MicroflowJobTraceRecord value6(Timestamp value) {
        setEndTime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MicroflowJobTraceRecord value7(String value) {
        setJobName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MicroflowJobTraceRecord value8(String value) {
        setJobScript(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public MicroflowJobTraceRecord values(UInteger value1, UInteger value2, UInteger value3, UByte value4, Timestamp value5, Timestamp value6, String value7, String value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MicroflowJobTraceRecord
     */
    public MicroflowJobTraceRecord() {
        super(MicroflowJobTrace.MICROFLOW_JOB_TRACE);
    }

    /**
     * Create a detached, initialised MicroflowJobTraceRecord
     */
    public MicroflowJobTraceRecord(UInteger id, UInteger jobId, UInteger triggerId, UByte status, Timestamp startTime, Timestamp endTime, String jobName, String jobScript) {
        super(MicroflowJobTrace.MICROFLOW_JOB_TRACE);

        set(0, id);
        set(1, jobId);
        set(2, triggerId);
        set(3, status);
        set(4, startTime);
        set(5, endTime);
        set(6, jobName);
        set(7, jobScript);
    }
}

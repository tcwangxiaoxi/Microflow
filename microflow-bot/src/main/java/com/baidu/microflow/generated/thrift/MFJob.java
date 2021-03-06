/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.baidu.microflow.generated.thrift;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Generated;

import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-12-08")
public class MFJob implements org.apache.thrift.TBase<MFJob, MFJob._Fields>, java.io.Serializable, Cloneable, Comparable<MFJob> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("MFJob");

  private static final org.apache.thrift.protocol.TField JOB_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("jobId", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField JOB_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("jobName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField JOB_SCRIPT_FIELD_DESC = new org.apache.thrift.protocol.TField("jobScript", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new MFJobStandardSchemeFactory());
    schemes.put(TupleScheme.class, new MFJobTupleSchemeFactory());
  }

  private long jobId; // required
  private String jobName; // required
  private String jobScript; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    JOB_ID((short)1, "jobId"),
    JOB_NAME((short)2, "jobName"),
    JOB_SCRIPT((short)3, "jobScript");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // JOB_ID
          return JOB_ID;
        case 2: // JOB_NAME
          return JOB_NAME;
        case 3: // JOB_SCRIPT
          return JOB_SCRIPT;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __JOBID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.JOB_ID, new org.apache.thrift.meta_data.FieldMetaData("jobId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.JOB_NAME, new org.apache.thrift.meta_data.FieldMetaData("jobName", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.JOB_SCRIPT, new org.apache.thrift.meta_data.FieldMetaData("jobScript", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(MFJob.class, metaDataMap);
  }

  public MFJob() {
  }

  public MFJob(
    long jobId,
    String jobName,
    String jobScript)
  {
    this();
    this.jobId = jobId;
    setJobIdIsSet(true);
    this.jobName = jobName;
    this.jobScript = jobScript;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public MFJob(MFJob other) {
    __isset_bitfield = other.__isset_bitfield;
    this.jobId = other.jobId;
    if (other.isSetJobName()) {
      this.jobName = other.jobName;
    }
    if (other.isSetJobScript()) {
      this.jobScript = other.jobScript;
    }
  }

  public MFJob deepCopy() {
    return new MFJob(this);
  }

  @Override
  public void clear() {
    setJobIdIsSet(false);
    this.jobId = 0;
    this.jobName = null;
    this.jobScript = null;
  }

  public long getJobId() {
    return this.jobId;
  }

  public void setJobId(long jobId) {
    this.jobId = jobId;
    setJobIdIsSet(true);
  }

  public void unsetJobId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __JOBID_ISSET_ID);
  }

  /** Returns true if field jobId is set (has been assigned a value) and false otherwise */
  public boolean isSetJobId() {
    return EncodingUtils.testBit(__isset_bitfield, __JOBID_ISSET_ID);
  }

  public void setJobIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __JOBID_ISSET_ID, value);
  }

  public String getJobName() {
    return this.jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  public void unsetJobName() {
    this.jobName = null;
  }

  /** Returns true if field jobName is set (has been assigned a value) and false otherwise */
  public boolean isSetJobName() {
    return this.jobName != null;
  }

  public void setJobNameIsSet(boolean value) {
    if (!value) {
      this.jobName = null;
    }
  }

  public String getJobScript() {
    return this.jobScript;
  }

  public void setJobScript(String jobScript) {
    this.jobScript = jobScript;
  }

  public void unsetJobScript() {
    this.jobScript = null;
  }

  /** Returns true if field jobScript is set (has been assigned a value) and false otherwise */
  public boolean isSetJobScript() {
    return this.jobScript != null;
  }

  public void setJobScriptIsSet(boolean value) {
    if (!value) {
      this.jobScript = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case JOB_ID:
      if (value == null) {
        unsetJobId();
      } else {
        setJobId((Long)value);
      }
      break;

    case JOB_NAME:
      if (value == null) {
        unsetJobName();
      } else {
        setJobName((String)value);
      }
      break;

    case JOB_SCRIPT:
      if (value == null) {
        unsetJobScript();
      } else {
        setJobScript((String)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case JOB_ID:
      return getJobId();

    case JOB_NAME:
      return getJobName();

    case JOB_SCRIPT:
      return getJobScript();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case JOB_ID:
      return isSetJobId();
    case JOB_NAME:
      return isSetJobName();
    case JOB_SCRIPT:
      return isSetJobScript();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof MFJob)
      return this.equals((MFJob)that);
    return false;
  }

  public boolean equals(MFJob that) {
    if (that == null)
      return false;

    boolean this_present_jobId = true;
    boolean that_present_jobId = true;
    if (this_present_jobId || that_present_jobId) {
      if (!(this_present_jobId && that_present_jobId))
        return false;
      if (this.jobId != that.jobId)
        return false;
    }

    boolean this_present_jobName = true && this.isSetJobName();
    boolean that_present_jobName = true && that.isSetJobName();
    if (this_present_jobName || that_present_jobName) {
      if (!(this_present_jobName && that_present_jobName))
        return false;
      if (!this.jobName.equals(that.jobName))
        return false;
    }

    boolean this_present_jobScript = true && this.isSetJobScript();
    boolean that_present_jobScript = true && that.isSetJobScript();
    if (this_present_jobScript || that_present_jobScript) {
      if (!(this_present_jobScript && that_present_jobScript))
        return false;
      if (!this.jobScript.equals(that.jobScript))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_jobId = true;
    list.add(present_jobId);
    if (present_jobId)
      list.add(jobId);

    boolean present_jobName = true && (isSetJobName());
    list.add(present_jobName);
    if (present_jobName)
      list.add(jobName);

    boolean present_jobScript = true && (isSetJobScript());
    list.add(present_jobScript);
    if (present_jobScript)
      list.add(jobScript);

    return list.hashCode();
  }

  @Override
  public int compareTo(MFJob other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetJobId()).compareTo(other.isSetJobId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetJobId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.jobId, other.jobId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetJobName()).compareTo(other.isSetJobName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetJobName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.jobName, other.jobName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetJobScript()).compareTo(other.isSetJobScript());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetJobScript()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.jobScript, other.jobScript);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("MFJob(");
    boolean first = true;

    sb.append("jobId:");
    sb.append(this.jobId);
    first = false;
    if (!first) sb.append(", ");
    sb.append("jobName:");
    if (this.jobName == null) {
      sb.append("null");
    } else {
      sb.append(this.jobName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("jobScript:");
    if (this.jobScript == null) {
      sb.append("null");
    } else {
      sb.append(this.jobScript);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws TException {
    // check for required fields
    if (!isSetJobId()) {
      throw new TProtocolException("Required field 'jobId' is unset! Struct:" + toString());
    }

    if (!isSetJobName()) {
      throw new TProtocolException("Required field 'jobName' is unset! Struct:" + toString());
    }

    if (!isSetJobScript()) {
      throw new TProtocolException("Required field 'jobScript' is unset! Struct:" + toString());
    }

    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class MFJobStandardSchemeFactory implements SchemeFactory {
    public MFJobStandardScheme getScheme() {
      return new MFJobStandardScheme();
    }
  }

  private static class MFJobStandardScheme extends StandardScheme<MFJob> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, MFJob struct) throws TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
          break;
        }
        switch (schemeField.id) {
          case 1: // JOB_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.jobId = iprot.readI64();
              struct.setJobIdIsSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // JOB_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.jobName = iprot.readString();
              struct.setJobNameIsSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // JOB_SCRIPT
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.jobScript = iprot.readString();
              struct.setJobScriptIsSet(true);
            } else {
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, MFJob struct) throws TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(JOB_ID_FIELD_DESC);
      oprot.writeI64(struct.jobId);
      oprot.writeFieldEnd();
      if (struct.jobName != null) {
        oprot.writeFieldBegin(JOB_NAME_FIELD_DESC);
        oprot.writeString(struct.jobName);
        oprot.writeFieldEnd();
      }
      if (struct.jobScript != null) {
        oprot.writeFieldBegin(JOB_SCRIPT_FIELD_DESC);
        oprot.writeString(struct.jobScript);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class MFJobTupleSchemeFactory implements SchemeFactory {
    public MFJobTupleScheme getScheme() {
      return new MFJobTupleScheme();
    }
  }

  private static class MFJobTupleScheme extends TupleScheme<MFJob> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, MFJob struct) throws TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeI64(struct.jobId);
      oprot.writeString(struct.jobName);
      oprot.writeString(struct.jobScript);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, MFJob struct) throws TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.jobId = iprot.readI64();
      struct.setJobIdIsSet(true);
      struct.jobName = iprot.readString();
      struct.setJobNameIsSet(true);
      struct.jobScript = iprot.readString();
      struct.setJobScriptIsSet(true);
    }
  }

}


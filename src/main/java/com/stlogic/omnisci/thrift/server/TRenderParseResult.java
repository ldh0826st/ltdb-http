/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.stlogic.omnisci.thrift.server;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2021-02-02")
public class TRenderParseResult implements org.apache.thrift.TBase<TRenderParseResult, TRenderParseResult._Fields>, java.io.Serializable, Cloneable, Comparable<TRenderParseResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TRenderParseResult");

  private static final org.apache.thrift.protocol.TField MERGE_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("merge_type", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField NODE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("node_id", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField EXECUTION_TIME_MS_FIELD_DESC = new org.apache.thrift.protocol.TField("execution_time_ms", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField RENDER_TIME_MS_FIELD_DESC = new org.apache.thrift.protocol.TField("render_time_ms", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField TOTAL_TIME_MS_FIELD_DESC = new org.apache.thrift.protocol.TField("total_time_ms", org.apache.thrift.protocol.TType.I64, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TRenderParseResultStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TRenderParseResultTupleSchemeFactory();

  /**
   * 
   * @see TMergeType
   */
  public TMergeType merge_type; // required
  public int node_id; // required
  public long execution_time_ms; // required
  public long render_time_ms; // required
  public long total_time_ms; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * 
     * @see TMergeType
     */
    MERGE_TYPE((short)1, "merge_type"),
    NODE_ID((short)2, "node_id"),
    EXECUTION_TIME_MS((short)3, "execution_time_ms"),
    RENDER_TIME_MS((short)4, "render_time_ms"),
    TOTAL_TIME_MS((short)5, "total_time_ms");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // MERGE_TYPE
          return MERGE_TYPE;
        case 2: // NODE_ID
          return NODE_ID;
        case 3: // EXECUTION_TIME_MS
          return EXECUTION_TIME_MS;
        case 4: // RENDER_TIME_MS
          return RENDER_TIME_MS;
        case 5: // TOTAL_TIME_MS
          return TOTAL_TIME_MS;
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
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __NODE_ID_ISSET_ID = 0;
  private static final int __EXECUTION_TIME_MS_ISSET_ID = 1;
  private static final int __RENDER_TIME_MS_ISSET_ID = 2;
  private static final int __TOTAL_TIME_MS_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.MERGE_TYPE, new org.apache.thrift.meta_data.FieldMetaData("merge_type", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, TMergeType.class)));
    tmpMap.put(_Fields.NODE_ID, new org.apache.thrift.meta_data.FieldMetaData("node_id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.EXECUTION_TIME_MS, new org.apache.thrift.meta_data.FieldMetaData("execution_time_ms", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.RENDER_TIME_MS, new org.apache.thrift.meta_data.FieldMetaData("render_time_ms", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TOTAL_TIME_MS, new org.apache.thrift.meta_data.FieldMetaData("total_time_ms", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TRenderParseResult.class, metaDataMap);
  }

  public TRenderParseResult() {
  }

  public TRenderParseResult(
    TMergeType merge_type,
    int node_id,
    long execution_time_ms,
    long render_time_ms,
    long total_time_ms)
  {
    this();
    this.merge_type = merge_type;
    this.node_id = node_id;
    setNode_idIsSet(true);
    this.execution_time_ms = execution_time_ms;
    setExecution_time_msIsSet(true);
    this.render_time_ms = render_time_ms;
    setRender_time_msIsSet(true);
    this.total_time_ms = total_time_ms;
    setTotal_time_msIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TRenderParseResult(TRenderParseResult other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetMerge_type()) {
      this.merge_type = other.merge_type;
    }
    this.node_id = other.node_id;
    this.execution_time_ms = other.execution_time_ms;
    this.render_time_ms = other.render_time_ms;
    this.total_time_ms = other.total_time_ms;
  }

  public TRenderParseResult deepCopy() {
    return new TRenderParseResult(this);
  }

  @Override
  public void clear() {
    this.merge_type = null;
    setNode_idIsSet(false);
    this.node_id = 0;
    setExecution_time_msIsSet(false);
    this.execution_time_ms = 0;
    setRender_time_msIsSet(false);
    this.render_time_ms = 0;
    setTotal_time_msIsSet(false);
    this.total_time_ms = 0;
  }

  /**
   * 
   * @see TMergeType
   */
  public TMergeType getMerge_type() {
    return this.merge_type;
  }

  /**
   * 
   * @see TMergeType
   */
  public TRenderParseResult setMerge_type(TMergeType merge_type) {
    this.merge_type = merge_type;
    return this;
  }

  public void unsetMerge_type() {
    this.merge_type = null;
  }

  /** Returns true if field merge_type is set (has been assigned a value) and false otherwise */
  public boolean isSetMerge_type() {
    return this.merge_type != null;
  }

  public void setMerge_typeIsSet(boolean value) {
    if (!value) {
      this.merge_type = null;
    }
  }

  public int getNode_id() {
    return this.node_id;
  }

  public TRenderParseResult setNode_id(int node_id) {
    this.node_id = node_id;
    setNode_idIsSet(true);
    return this;
  }

  public void unsetNode_id() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __NODE_ID_ISSET_ID);
  }

  /** Returns true if field node_id is set (has been assigned a value) and false otherwise */
  public boolean isSetNode_id() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __NODE_ID_ISSET_ID);
  }

  public void setNode_idIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __NODE_ID_ISSET_ID, value);
  }

  public long getExecution_time_ms() {
    return this.execution_time_ms;
  }

  public TRenderParseResult setExecution_time_ms(long execution_time_ms) {
    this.execution_time_ms = execution_time_ms;
    setExecution_time_msIsSet(true);
    return this;
  }

  public void unsetExecution_time_ms() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __EXECUTION_TIME_MS_ISSET_ID);
  }

  /** Returns true if field execution_time_ms is set (has been assigned a value) and false otherwise */
  public boolean isSetExecution_time_ms() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __EXECUTION_TIME_MS_ISSET_ID);
  }

  public void setExecution_time_msIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __EXECUTION_TIME_MS_ISSET_ID, value);
  }

  public long getRender_time_ms() {
    return this.render_time_ms;
  }

  public TRenderParseResult setRender_time_ms(long render_time_ms) {
    this.render_time_ms = render_time_ms;
    setRender_time_msIsSet(true);
    return this;
  }

  public void unsetRender_time_ms() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __RENDER_TIME_MS_ISSET_ID);
  }

  /** Returns true if field render_time_ms is set (has been assigned a value) and false otherwise */
  public boolean isSetRender_time_ms() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __RENDER_TIME_MS_ISSET_ID);
  }

  public void setRender_time_msIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __RENDER_TIME_MS_ISSET_ID, value);
  }

  public long getTotal_time_ms() {
    return this.total_time_ms;
  }

  public TRenderParseResult setTotal_time_ms(long total_time_ms) {
    this.total_time_ms = total_time_ms;
    setTotal_time_msIsSet(true);
    return this;
  }

  public void unsetTotal_time_ms() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TOTAL_TIME_MS_ISSET_ID);
  }

  /** Returns true if field total_time_ms is set (has been assigned a value) and false otherwise */
  public boolean isSetTotal_time_ms() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TOTAL_TIME_MS_ISSET_ID);
  }

  public void setTotal_time_msIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TOTAL_TIME_MS_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case MERGE_TYPE:
      if (value == null) {
        unsetMerge_type();
      } else {
        setMerge_type((TMergeType)value);
      }
      break;

    case NODE_ID:
      if (value == null) {
        unsetNode_id();
      } else {
        setNode_id((java.lang.Integer)value);
      }
      break;

    case EXECUTION_TIME_MS:
      if (value == null) {
        unsetExecution_time_ms();
      } else {
        setExecution_time_ms((java.lang.Long)value);
      }
      break;

    case RENDER_TIME_MS:
      if (value == null) {
        unsetRender_time_ms();
      } else {
        setRender_time_ms((java.lang.Long)value);
      }
      break;

    case TOTAL_TIME_MS:
      if (value == null) {
        unsetTotal_time_ms();
      } else {
        setTotal_time_ms((java.lang.Long)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case MERGE_TYPE:
      return getMerge_type();

    case NODE_ID:
      return getNode_id();

    case EXECUTION_TIME_MS:
      return getExecution_time_ms();

    case RENDER_TIME_MS:
      return getRender_time_ms();

    case TOTAL_TIME_MS:
      return getTotal_time_ms();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case MERGE_TYPE:
      return isSetMerge_type();
    case NODE_ID:
      return isSetNode_id();
    case EXECUTION_TIME_MS:
      return isSetExecution_time_ms();
    case RENDER_TIME_MS:
      return isSetRender_time_ms();
    case TOTAL_TIME_MS:
      return isSetTotal_time_ms();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TRenderParseResult)
      return this.equals((TRenderParseResult)that);
    return false;
  }

  public boolean equals(TRenderParseResult that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_merge_type = true && this.isSetMerge_type();
    boolean that_present_merge_type = true && that.isSetMerge_type();
    if (this_present_merge_type || that_present_merge_type) {
      if (!(this_present_merge_type && that_present_merge_type))
        return false;
      if (!this.merge_type.equals(that.merge_type))
        return false;
    }

    boolean this_present_node_id = true;
    boolean that_present_node_id = true;
    if (this_present_node_id || that_present_node_id) {
      if (!(this_present_node_id && that_present_node_id))
        return false;
      if (this.node_id != that.node_id)
        return false;
    }

    boolean this_present_execution_time_ms = true;
    boolean that_present_execution_time_ms = true;
    if (this_present_execution_time_ms || that_present_execution_time_ms) {
      if (!(this_present_execution_time_ms && that_present_execution_time_ms))
        return false;
      if (this.execution_time_ms != that.execution_time_ms)
        return false;
    }

    boolean this_present_render_time_ms = true;
    boolean that_present_render_time_ms = true;
    if (this_present_render_time_ms || that_present_render_time_ms) {
      if (!(this_present_render_time_ms && that_present_render_time_ms))
        return false;
      if (this.render_time_ms != that.render_time_ms)
        return false;
    }

    boolean this_present_total_time_ms = true;
    boolean that_present_total_time_ms = true;
    if (this_present_total_time_ms || that_present_total_time_ms) {
      if (!(this_present_total_time_ms && that_present_total_time_ms))
        return false;
      if (this.total_time_ms != that.total_time_ms)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetMerge_type()) ? 131071 : 524287);
    if (isSetMerge_type())
      hashCode = hashCode * 8191 + merge_type.getValue();

    hashCode = hashCode * 8191 + node_id;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(execution_time_ms);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(render_time_ms);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(total_time_ms);

    return hashCode;
  }

  @Override
  public int compareTo(TRenderParseResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetMerge_type()).compareTo(other.isSetMerge_type());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMerge_type()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.merge_type, other.merge_type);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetNode_id()).compareTo(other.isSetNode_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNode_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.node_id, other.node_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetExecution_time_ms()).compareTo(other.isSetExecution_time_ms());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetExecution_time_ms()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.execution_time_ms, other.execution_time_ms);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRender_time_ms()).compareTo(other.isSetRender_time_ms());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRender_time_ms()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.render_time_ms, other.render_time_ms);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTotal_time_ms()).compareTo(other.isSetTotal_time_ms());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTotal_time_ms()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.total_time_ms, other.total_time_ms);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TRenderParseResult(");
    boolean first = true;

    sb.append("merge_type:");
    if (this.merge_type == null) {
      sb.append("null");
    } else {
      sb.append(this.merge_type);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("node_id:");
    sb.append(this.node_id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("execution_time_ms:");
    sb.append(this.execution_time_ms);
    first = false;
    if (!first) sb.append(", ");
    sb.append("render_time_ms:");
    sb.append(this.render_time_ms);
    first = false;
    if (!first) sb.append(", ");
    sb.append("total_time_ms:");
    sb.append(this.total_time_ms);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TRenderParseResultStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TRenderParseResultStandardScheme getScheme() {
      return new TRenderParseResultStandardScheme();
    }
  }

  private static class TRenderParseResultStandardScheme extends org.apache.thrift.scheme.StandardScheme<TRenderParseResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TRenderParseResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // MERGE_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.merge_type = com.stlogic.omnisci.thrift.server.TMergeType.findByValue(iprot.readI32());
              struct.setMerge_typeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // NODE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.node_id = iprot.readI32();
              struct.setNode_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // EXECUTION_TIME_MS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.execution_time_ms = iprot.readI64();
              struct.setExecution_time_msIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // RENDER_TIME_MS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.render_time_ms = iprot.readI64();
              struct.setRender_time_msIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // TOTAL_TIME_MS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.total_time_ms = iprot.readI64();
              struct.setTotal_time_msIsSet(true);
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

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TRenderParseResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.merge_type != null) {
        oprot.writeFieldBegin(MERGE_TYPE_FIELD_DESC);
        oprot.writeI32(struct.merge_type.getValue());
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(NODE_ID_FIELD_DESC);
      oprot.writeI32(struct.node_id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(EXECUTION_TIME_MS_FIELD_DESC);
      oprot.writeI64(struct.execution_time_ms);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(RENDER_TIME_MS_FIELD_DESC);
      oprot.writeI64(struct.render_time_ms);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TOTAL_TIME_MS_FIELD_DESC);
      oprot.writeI64(struct.total_time_ms);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TRenderParseResultTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TRenderParseResultTupleScheme getScheme() {
      return new TRenderParseResultTupleScheme();
    }
  }

  private static class TRenderParseResultTupleScheme extends org.apache.thrift.scheme.TupleScheme<TRenderParseResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TRenderParseResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetMerge_type()) {
        optionals.set(0);
      }
      if (struct.isSetNode_id()) {
        optionals.set(1);
      }
      if (struct.isSetExecution_time_ms()) {
        optionals.set(2);
      }
      if (struct.isSetRender_time_ms()) {
        optionals.set(3);
      }
      if (struct.isSetTotal_time_ms()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetMerge_type()) {
        oprot.writeI32(struct.merge_type.getValue());
      }
      if (struct.isSetNode_id()) {
        oprot.writeI32(struct.node_id);
      }
      if (struct.isSetExecution_time_ms()) {
        oprot.writeI64(struct.execution_time_ms);
      }
      if (struct.isSetRender_time_ms()) {
        oprot.writeI64(struct.render_time_ms);
      }
      if (struct.isSetTotal_time_ms()) {
        oprot.writeI64(struct.total_time_ms);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TRenderParseResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.merge_type = com.stlogic.omnisci.thrift.server.TMergeType.findByValue(iprot.readI32());
        struct.setMerge_typeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.node_id = iprot.readI32();
        struct.setNode_idIsSet(true);
      }
      if (incoming.get(2)) {
        struct.execution_time_ms = iprot.readI64();
        struct.setExecution_time_msIsSet(true);
      }
      if (incoming.get(3)) {
        struct.render_time_ms = iprot.readI64();
        struct.setRender_time_msIsSet(true);
      }
      if (incoming.get(4)) {
        struct.total_time_ms = iprot.readI64();
        struct.setTotal_time_msIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}


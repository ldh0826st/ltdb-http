/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.stlogic.omnisci.thrift.server;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2021-02-02")
public class TQueryResult implements org.apache.thrift.TBase<TQueryResult, TQueryResult._Fields>, java.io.Serializable, Cloneable, Comparable<TQueryResult> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TQueryResult");

  private static final org.apache.thrift.protocol.TField ROW_SET_FIELD_DESC = new org.apache.thrift.protocol.TField("row_set", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField EXECUTION_TIME_MS_FIELD_DESC = new org.apache.thrift.protocol.TField("execution_time_ms", org.apache.thrift.protocol.TType.I64, (short)2);
  private static final org.apache.thrift.protocol.TField TOTAL_TIME_MS_FIELD_DESC = new org.apache.thrift.protocol.TField("total_time_ms", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField NONCE_FIELD_DESC = new org.apache.thrift.protocol.TField("nonce", org.apache.thrift.protocol.TType.STRING, (short)4);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TQueryResultStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TQueryResultTupleSchemeFactory();

  public TRowSet row_set; // required
  public long execution_time_ms; // required
  public long total_time_ms; // required
  public java.lang.String nonce; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ROW_SET((short)1, "row_set"),
    EXECUTION_TIME_MS((short)2, "execution_time_ms"),
    TOTAL_TIME_MS((short)3, "total_time_ms"),
    NONCE((short)4, "nonce");

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
        case 1: // ROW_SET
          return ROW_SET;
        case 2: // EXECUTION_TIME_MS
          return EXECUTION_TIME_MS;
        case 3: // TOTAL_TIME_MS
          return TOTAL_TIME_MS;
        case 4: // NONCE
          return NONCE;
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
  private static final int __EXECUTION_TIME_MS_ISSET_ID = 0;
  private static final int __TOTAL_TIME_MS_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ROW_SET, new org.apache.thrift.meta_data.FieldMetaData("row_set", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TRowSet.class)));
    tmpMap.put(_Fields.EXECUTION_TIME_MS, new org.apache.thrift.meta_data.FieldMetaData("execution_time_ms", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TOTAL_TIME_MS, new org.apache.thrift.meta_data.FieldMetaData("total_time_ms", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.NONCE, new org.apache.thrift.meta_data.FieldMetaData("nonce", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TQueryResult.class, metaDataMap);
  }

  public TQueryResult() {
  }

  public TQueryResult(
    TRowSet row_set,
    long execution_time_ms,
    long total_time_ms,
    java.lang.String nonce)
  {
    this();
    this.row_set = row_set;
    this.execution_time_ms = execution_time_ms;
    setExecution_time_msIsSet(true);
    this.total_time_ms = total_time_ms;
    setTotal_time_msIsSet(true);
    this.nonce = nonce;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TQueryResult(TQueryResult other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetRow_set()) {
      this.row_set = new TRowSet(other.row_set);
    }
    this.execution_time_ms = other.execution_time_ms;
    this.total_time_ms = other.total_time_ms;
    if (other.isSetNonce()) {
      this.nonce = other.nonce;
    }
  }

  public TQueryResult deepCopy() {
    return new TQueryResult(this);
  }

  @Override
  public void clear() {
    this.row_set = null;
    setExecution_time_msIsSet(false);
    this.execution_time_ms = 0;
    setTotal_time_msIsSet(false);
    this.total_time_ms = 0;
    this.nonce = null;
  }

  public TRowSet getRow_set() {
    return this.row_set;
  }

  public TQueryResult setRow_set(TRowSet row_set) {
    this.row_set = row_set;
    return this;
  }

  public void unsetRow_set() {
    this.row_set = null;
  }

  /** Returns true if field row_set is set (has been assigned a value) and false otherwise */
  public boolean isSetRow_set() {
    return this.row_set != null;
  }

  public void setRow_setIsSet(boolean value) {
    if (!value) {
      this.row_set = null;
    }
  }

  public long getExecution_time_ms() {
    return this.execution_time_ms;
  }

  public TQueryResult setExecution_time_ms(long execution_time_ms) {
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

  public long getTotal_time_ms() {
    return this.total_time_ms;
  }

  public TQueryResult setTotal_time_ms(long total_time_ms) {
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

  public java.lang.String getNonce() {
    return this.nonce;
  }

  public TQueryResult setNonce(java.lang.String nonce) {
    this.nonce = nonce;
    return this;
  }

  public void unsetNonce() {
    this.nonce = null;
  }

  /** Returns true if field nonce is set (has been assigned a value) and false otherwise */
  public boolean isSetNonce() {
    return this.nonce != null;
  }

  public void setNonceIsSet(boolean value) {
    if (!value) {
      this.nonce = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case ROW_SET:
      if (value == null) {
        unsetRow_set();
      } else {
        setRow_set((TRowSet)value);
      }
      break;

    case EXECUTION_TIME_MS:
      if (value == null) {
        unsetExecution_time_ms();
      } else {
        setExecution_time_ms((java.lang.Long)value);
      }
      break;

    case TOTAL_TIME_MS:
      if (value == null) {
        unsetTotal_time_ms();
      } else {
        setTotal_time_ms((java.lang.Long)value);
      }
      break;

    case NONCE:
      if (value == null) {
        unsetNonce();
      } else {
        setNonce((java.lang.String)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case ROW_SET:
      return getRow_set();

    case EXECUTION_TIME_MS:
      return getExecution_time_ms();

    case TOTAL_TIME_MS:
      return getTotal_time_ms();

    case NONCE:
      return getNonce();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case ROW_SET:
      return isSetRow_set();
    case EXECUTION_TIME_MS:
      return isSetExecution_time_ms();
    case TOTAL_TIME_MS:
      return isSetTotal_time_ms();
    case NONCE:
      return isSetNonce();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TQueryResult)
      return this.equals((TQueryResult)that);
    return false;
  }

  public boolean equals(TQueryResult that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_row_set = true && this.isSetRow_set();
    boolean that_present_row_set = true && that.isSetRow_set();
    if (this_present_row_set || that_present_row_set) {
      if (!(this_present_row_set && that_present_row_set))
        return false;
      if (!this.row_set.equals(that.row_set))
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

    boolean this_present_total_time_ms = true;
    boolean that_present_total_time_ms = true;
    if (this_present_total_time_ms || that_present_total_time_ms) {
      if (!(this_present_total_time_ms && that_present_total_time_ms))
        return false;
      if (this.total_time_ms != that.total_time_ms)
        return false;
    }

    boolean this_present_nonce = true && this.isSetNonce();
    boolean that_present_nonce = true && that.isSetNonce();
    if (this_present_nonce || that_present_nonce) {
      if (!(this_present_nonce && that_present_nonce))
        return false;
      if (!this.nonce.equals(that.nonce))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetRow_set()) ? 131071 : 524287);
    if (isSetRow_set())
      hashCode = hashCode * 8191 + row_set.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(execution_time_ms);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(total_time_ms);

    hashCode = hashCode * 8191 + ((isSetNonce()) ? 131071 : 524287);
    if (isSetNonce())
      hashCode = hashCode * 8191 + nonce.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TQueryResult other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetRow_set()).compareTo(other.isSetRow_set());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRow_set()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.row_set, other.row_set);
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
    lastComparison = java.lang.Boolean.valueOf(isSetNonce()).compareTo(other.isSetNonce());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNonce()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.nonce, other.nonce);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TQueryResult(");
    boolean first = true;

    sb.append("row_set:");
    if (this.row_set == null) {
      sb.append("null");
    } else {
      sb.append(this.row_set);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("execution_time_ms:");
    sb.append(this.execution_time_ms);
    first = false;
    if (!first) sb.append(", ");
    sb.append("total_time_ms:");
    sb.append(this.total_time_ms);
    first = false;
    if (!first) sb.append(", ");
    sb.append("nonce:");
    if (this.nonce == null) {
      sb.append("null");
    } else {
      sb.append(this.nonce);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (row_set != null) {
      row_set.validate();
    }
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

  private static class TQueryResultStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TQueryResultStandardScheme getScheme() {
      return new TQueryResultStandardScheme();
    }
  }

  private static class TQueryResultStandardScheme extends org.apache.thrift.scheme.StandardScheme<TQueryResult> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TQueryResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ROW_SET
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.row_set = new TRowSet();
              struct.row_set.read(iprot);
              struct.setRow_setIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // EXECUTION_TIME_MS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.execution_time_ms = iprot.readI64();
              struct.setExecution_time_msIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // TOTAL_TIME_MS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.total_time_ms = iprot.readI64();
              struct.setTotal_time_msIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // NONCE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.nonce = iprot.readString();
              struct.setNonceIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TQueryResult struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.row_set != null) {
        oprot.writeFieldBegin(ROW_SET_FIELD_DESC);
        struct.row_set.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(EXECUTION_TIME_MS_FIELD_DESC);
      oprot.writeI64(struct.execution_time_ms);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TOTAL_TIME_MS_FIELD_DESC);
      oprot.writeI64(struct.total_time_ms);
      oprot.writeFieldEnd();
      if (struct.nonce != null) {
        oprot.writeFieldBegin(NONCE_FIELD_DESC);
        oprot.writeString(struct.nonce);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TQueryResultTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TQueryResultTupleScheme getScheme() {
      return new TQueryResultTupleScheme();
    }
  }

  private static class TQueryResultTupleScheme extends org.apache.thrift.scheme.TupleScheme<TQueryResult> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TQueryResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetRow_set()) {
        optionals.set(0);
      }
      if (struct.isSetExecution_time_ms()) {
        optionals.set(1);
      }
      if (struct.isSetTotal_time_ms()) {
        optionals.set(2);
      }
      if (struct.isSetNonce()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetRow_set()) {
        struct.row_set.write(oprot);
      }
      if (struct.isSetExecution_time_ms()) {
        oprot.writeI64(struct.execution_time_ms);
      }
      if (struct.isSetTotal_time_ms()) {
        oprot.writeI64(struct.total_time_ms);
      }
      if (struct.isSetNonce()) {
        oprot.writeString(struct.nonce);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TQueryResult struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.row_set = new TRowSet();
        struct.row_set.read(iprot);
        struct.setRow_setIsSet(true);
      }
      if (incoming.get(1)) {
        struct.execution_time_ms = iprot.readI64();
        struct.setExecution_time_msIsSet(true);
      }
      if (incoming.get(2)) {
        struct.total_time_ms = iprot.readI64();
        struct.setTotal_time_msIsSet(true);
      }
      if (incoming.get(3)) {
        struct.nonce = iprot.readString();
        struct.setNonceIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}


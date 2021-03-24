/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.stlogic.omnisci.thrift.server;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2021-02-02")
public class TCreateParams implements org.apache.thrift.TBase<TCreateParams, TCreateParams._Fields>, java.io.Serializable, Cloneable, Comparable<TCreateParams> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TCreateParams");

  private static final org.apache.thrift.protocol.TField IS_REPLICATED_FIELD_DESC = new org.apache.thrift.protocol.TField("is_replicated", org.apache.thrift.protocol.TType.BOOL, (short)1);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TCreateParamsStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TCreateParamsTupleSchemeFactory();

  public boolean is_replicated; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    IS_REPLICATED((short)1, "is_replicated");

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
        case 1: // IS_REPLICATED
          return IS_REPLICATED;
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
  private static final int __IS_REPLICATED_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.IS_REPLICATED, new org.apache.thrift.meta_data.FieldMetaData("is_replicated", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TCreateParams.class, metaDataMap);
  }

  public TCreateParams() {
  }

  public TCreateParams(
    boolean is_replicated)
  {
    this();
    this.is_replicated = is_replicated;
    setIs_replicatedIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TCreateParams(TCreateParams other) {
    __isset_bitfield = other.__isset_bitfield;
    this.is_replicated = other.is_replicated;
  }

  public TCreateParams deepCopy() {
    return new TCreateParams(this);
  }

  @Override
  public void clear() {
    setIs_replicatedIsSet(false);
    this.is_replicated = false;
  }

  public boolean isIs_replicated() {
    return this.is_replicated;
  }

  public TCreateParams setIs_replicated(boolean is_replicated) {
    this.is_replicated = is_replicated;
    setIs_replicatedIsSet(true);
    return this;
  }

  public void unsetIs_replicated() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __IS_REPLICATED_ISSET_ID);
  }

  /** Returns true if field is_replicated is set (has been assigned a value) and false otherwise */
  public boolean isSetIs_replicated() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __IS_REPLICATED_ISSET_ID);
  }

  public void setIs_replicatedIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __IS_REPLICATED_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case IS_REPLICATED:
      if (value == null) {
        unsetIs_replicated();
      } else {
        setIs_replicated((java.lang.Boolean)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case IS_REPLICATED:
      return isIs_replicated();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case IS_REPLICATED:
      return isSetIs_replicated();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TCreateParams)
      return this.equals((TCreateParams)that);
    return false;
  }

  public boolean equals(TCreateParams that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_is_replicated = true;
    boolean that_present_is_replicated = true;
    if (this_present_is_replicated || that_present_is_replicated) {
      if (!(this_present_is_replicated && that_present_is_replicated))
        return false;
      if (this.is_replicated != that.is_replicated)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((is_replicated) ? 131071 : 524287);

    return hashCode;
  }

  @Override
  public int compareTo(TCreateParams other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetIs_replicated()).compareTo(other.isSetIs_replicated());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIs_replicated()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.is_replicated, other.is_replicated);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TCreateParams(");
    boolean first = true;

    sb.append("is_replicated:");
    sb.append(this.is_replicated);
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

  private static class TCreateParamsStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TCreateParamsStandardScheme getScheme() {
      return new TCreateParamsStandardScheme();
    }
  }

  private static class TCreateParamsStandardScheme extends org.apache.thrift.scheme.StandardScheme<TCreateParams> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TCreateParams struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // IS_REPLICATED
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.is_replicated = iprot.readBool();
              struct.setIs_replicatedIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TCreateParams struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(IS_REPLICATED_FIELD_DESC);
      oprot.writeBool(struct.is_replicated);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TCreateParamsTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TCreateParamsTupleScheme getScheme() {
      return new TCreateParamsTupleScheme();
    }
  }

  private static class TCreateParamsTupleScheme extends org.apache.thrift.scheme.TupleScheme<TCreateParams> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TCreateParams struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetIs_replicated()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetIs_replicated()) {
        oprot.writeBool(struct.is_replicated);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TCreateParams struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        struct.is_replicated = iprot.readBool();
        struct.setIs_replicatedIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

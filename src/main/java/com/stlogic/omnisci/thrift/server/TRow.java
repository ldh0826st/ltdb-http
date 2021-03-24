/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.stlogic.omnisci.thrift.server;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2021-02-02")
public class TRow implements org.apache.thrift.TBase<TRow, TRow._Fields>, java.io.Serializable, Cloneable, Comparable<TRow> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TRow");

  private static final org.apache.thrift.protocol.TField COLS_FIELD_DESC = new org.apache.thrift.protocol.TField("cols", org.apache.thrift.protocol.TType.LIST, (short)1);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TRowStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TRowTupleSchemeFactory();

  public java.util.List<TDatum> cols; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    COLS((short)1, "cols");

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
        case 1: // COLS
          return COLS;
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
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.COLS, new org.apache.thrift.meta_data.FieldMetaData("cols", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TDatum.class))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TRow.class, metaDataMap);
  }

  public TRow() {
  }

  public TRow(
    java.util.List<TDatum> cols)
  {
    this();
    this.cols = cols;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TRow(TRow other) {
    if (other.isSetCols()) {
      java.util.List<TDatum> __this__cols = new java.util.ArrayList<TDatum>(other.cols.size());
      for (TDatum other_element : other.cols) {
        __this__cols.add(new TDatum(other_element));
      }
      this.cols = __this__cols;
    }
  }

  public TRow deepCopy() {
    return new TRow(this);
  }

  @Override
  public void clear() {
    this.cols = null;
  }

  public int getColsSize() {
    return (this.cols == null) ? 0 : this.cols.size();
  }

  public java.util.Iterator<TDatum> getColsIterator() {
    return (this.cols == null) ? null : this.cols.iterator();
  }

  public void addToCols(TDatum elem) {
    if (this.cols == null) {
      this.cols = new java.util.ArrayList<TDatum>();
    }
    this.cols.add(elem);
  }

  public java.util.List<TDatum> getCols() {
    return this.cols;
  }

  public TRow setCols(java.util.List<TDatum> cols) {
    this.cols = cols;
    return this;
  }

  public void unsetCols() {
    this.cols = null;
  }

  /** Returns true if field cols is set (has been assigned a value) and false otherwise */
  public boolean isSetCols() {
    return this.cols != null;
  }

  public void setColsIsSet(boolean value) {
    if (!value) {
      this.cols = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case COLS:
      if (value == null) {
        unsetCols();
      } else {
        setCols((java.util.List<TDatum>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case COLS:
      return getCols();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case COLS:
      return isSetCols();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TRow)
      return this.equals((TRow)that);
    return false;
  }

  public boolean equals(TRow that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_cols = true && this.isSetCols();
    boolean that_present_cols = true && that.isSetCols();
    if (this_present_cols || that_present_cols) {
      if (!(this_present_cols && that_present_cols))
        return false;
      if (!this.cols.equals(that.cols))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetCols()) ? 131071 : 524287);
    if (isSetCols())
      hashCode = hashCode * 8191 + cols.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(TRow other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetCols()).compareTo(other.isSetCols());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCols()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.cols, other.cols);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TRow(");
    boolean first = true;

    sb.append("cols:");
    if (this.cols == null) {
      sb.append("null");
    } else {
      sb.append(this.cols);
    }
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TRowStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TRowStandardScheme getScheme() {
      return new TRowStandardScheme();
    }
  }

  private static class TRowStandardScheme extends org.apache.thrift.scheme.StandardScheme<TRow> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TRow struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // COLS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list8 = iprot.readListBegin();
                struct.cols = new java.util.ArrayList<TDatum>(_list8.size);
                TDatum _elem9;
                for (int _i10 = 0; _i10 < _list8.size; ++_i10)
                {
                  _elem9 = new TDatum();
                  _elem9.read(iprot);
                  struct.cols.add(_elem9);
                }
                iprot.readListEnd();
              }
              struct.setColsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TRow struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.cols != null) {
        oprot.writeFieldBegin(COLS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.cols.size()));
          for (TDatum _iter11 : struct.cols)
          {
            _iter11.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TRowTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TRowTupleScheme getScheme() {
      return new TRowTupleScheme();
    }
  }

  private static class TRowTupleScheme extends org.apache.thrift.scheme.TupleScheme<TRow> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TRow struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetCols()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.isSetCols()) {
        {
          oprot.writeI32(struct.cols.size());
          for (TDatum _iter12 : struct.cols)
          {
            _iter12.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TRow struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list13 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.cols = new java.util.ArrayList<TDatum>(_list13.size);
          TDatum _elem14;
          for (int _i15 = 0; _i15 < _list13.size; ++_i15)
          {
            _elem14 = new TDatum();
            _elem14.read(iprot);
            struct.cols.add(_elem14);
          }
        }
        struct.setColsIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}


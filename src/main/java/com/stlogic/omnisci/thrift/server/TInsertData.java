/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.stlogic.omnisci.thrift.server;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2021-02-02")
public class TInsertData implements org.apache.thrift.TBase<TInsertData, TInsertData._Fields>, java.io.Serializable, Cloneable, Comparable<TInsertData> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TInsertData");

  private static final org.apache.thrift.protocol.TField DB_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("db_id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField TABLE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("table_id", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField COLUMN_IDS_FIELD_DESC = new org.apache.thrift.protocol.TField("column_ids", org.apache.thrift.protocol.TType.LIST, (short)3);
  private static final org.apache.thrift.protocol.TField DATA_FIELD_DESC = new org.apache.thrift.protocol.TField("data", org.apache.thrift.protocol.TType.LIST, (short)4);
  private static final org.apache.thrift.protocol.TField NUM_ROWS_FIELD_DESC = new org.apache.thrift.protocol.TField("num_rows", org.apache.thrift.protocol.TType.I64, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TInsertDataStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TInsertDataTupleSchemeFactory();

  public int db_id; // required
  public int table_id; // required
  public java.util.List<java.lang.Integer> column_ids; // required
  public java.util.List<TDataBlockPtr> data; // required
  public long num_rows; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DB_ID((short)1, "db_id"),
    TABLE_ID((short)2, "table_id"),
    COLUMN_IDS((short)3, "column_ids"),
    DATA((short)4, "data"),
    NUM_ROWS((short)5, "num_rows");

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
        case 1: // DB_ID
          return DB_ID;
        case 2: // TABLE_ID
          return TABLE_ID;
        case 3: // COLUMN_IDS
          return COLUMN_IDS;
        case 4: // DATA
          return DATA;
        case 5: // NUM_ROWS
          return NUM_ROWS;
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
  private static final int __DB_ID_ISSET_ID = 0;
  private static final int __TABLE_ID_ISSET_ID = 1;
  private static final int __NUM_ROWS_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DB_ID, new org.apache.thrift.meta_data.FieldMetaData("db_id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.TABLE_ID, new org.apache.thrift.meta_data.FieldMetaData("table_id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.COLUMN_IDS, new org.apache.thrift.meta_data.FieldMetaData("column_ids", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32))));
    tmpMap.put(_Fields.DATA, new org.apache.thrift.meta_data.FieldMetaData("data", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, TDataBlockPtr.class))));
    tmpMap.put(_Fields.NUM_ROWS, new org.apache.thrift.meta_data.FieldMetaData("num_rows", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TInsertData.class, metaDataMap);
  }

  public TInsertData() {
  }

  public TInsertData(
    int db_id,
    int table_id,
    java.util.List<java.lang.Integer> column_ids,
    java.util.List<TDataBlockPtr> data,
    long num_rows)
  {
    this();
    this.db_id = db_id;
    setDb_idIsSet(true);
    this.table_id = table_id;
    setTable_idIsSet(true);
    this.column_ids = column_ids;
    this.data = data;
    this.num_rows = num_rows;
    setNum_rowsIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TInsertData(TInsertData other) {
    __isset_bitfield = other.__isset_bitfield;
    this.db_id = other.db_id;
    this.table_id = other.table_id;
    if (other.isSetColumn_ids()) {
      java.util.List<java.lang.Integer> __this__column_ids = new java.util.ArrayList<java.lang.Integer>(other.column_ids);
      this.column_ids = __this__column_ids;
    }
    if (other.isSetData()) {
      java.util.List<TDataBlockPtr> __this__data = new java.util.ArrayList<TDataBlockPtr>(other.data.size());
      for (TDataBlockPtr other_element : other.data) {
        __this__data.add(new TDataBlockPtr(other_element));
      }
      this.data = __this__data;
    }
    this.num_rows = other.num_rows;
  }

  public TInsertData deepCopy() {
    return new TInsertData(this);
  }

  @Override
  public void clear() {
    setDb_idIsSet(false);
    this.db_id = 0;
    setTable_idIsSet(false);
    this.table_id = 0;
    this.column_ids = null;
    this.data = null;
    setNum_rowsIsSet(false);
    this.num_rows = 0;
  }

  public int getDb_id() {
    return this.db_id;
  }

  public TInsertData setDb_id(int db_id) {
    this.db_id = db_id;
    setDb_idIsSet(true);
    return this;
  }

  public void unsetDb_id() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __DB_ID_ISSET_ID);
  }

  /** Returns true if field db_id is set (has been assigned a value) and false otherwise */
  public boolean isSetDb_id() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __DB_ID_ISSET_ID);
  }

  public void setDb_idIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __DB_ID_ISSET_ID, value);
  }

  public int getTable_id() {
    return this.table_id;
  }

  public TInsertData setTable_id(int table_id) {
    this.table_id = table_id;
    setTable_idIsSet(true);
    return this;
  }

  public void unsetTable_id() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __TABLE_ID_ISSET_ID);
  }

  /** Returns true if field table_id is set (has been assigned a value) and false otherwise */
  public boolean isSetTable_id() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __TABLE_ID_ISSET_ID);
  }

  public void setTable_idIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __TABLE_ID_ISSET_ID, value);
  }

  public int getColumn_idsSize() {
    return (this.column_ids == null) ? 0 : this.column_ids.size();
  }

  public java.util.Iterator<java.lang.Integer> getColumn_idsIterator() {
    return (this.column_ids == null) ? null : this.column_ids.iterator();
  }

  public void addToColumn_ids(int elem) {
    if (this.column_ids == null) {
      this.column_ids = new java.util.ArrayList<java.lang.Integer>();
    }
    this.column_ids.add(elem);
  }

  public java.util.List<java.lang.Integer> getColumn_ids() {
    return this.column_ids;
  }

  public TInsertData setColumn_ids(java.util.List<java.lang.Integer> column_ids) {
    this.column_ids = column_ids;
    return this;
  }

  public void unsetColumn_ids() {
    this.column_ids = null;
  }

  /** Returns true if field column_ids is set (has been assigned a value) and false otherwise */
  public boolean isSetColumn_ids() {
    return this.column_ids != null;
  }

  public void setColumn_idsIsSet(boolean value) {
    if (!value) {
      this.column_ids = null;
    }
  }

  public int getDataSize() {
    return (this.data == null) ? 0 : this.data.size();
  }

  public java.util.Iterator<TDataBlockPtr> getDataIterator() {
    return (this.data == null) ? null : this.data.iterator();
  }

  public void addToData(TDataBlockPtr elem) {
    if (this.data == null) {
      this.data = new java.util.ArrayList<TDataBlockPtr>();
    }
    this.data.add(elem);
  }

  public java.util.List<TDataBlockPtr> getData() {
    return this.data;
  }

  public TInsertData setData(java.util.List<TDataBlockPtr> data) {
    this.data = data;
    return this;
  }

  public void unsetData() {
    this.data = null;
  }

  /** Returns true if field data is set (has been assigned a value) and false otherwise */
  public boolean isSetData() {
    return this.data != null;
  }

  public void setDataIsSet(boolean value) {
    if (!value) {
      this.data = null;
    }
  }

  public long getNum_rows() {
    return this.num_rows;
  }

  public TInsertData setNum_rows(long num_rows) {
    this.num_rows = num_rows;
    setNum_rowsIsSet(true);
    return this;
  }

  public void unsetNum_rows() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __NUM_ROWS_ISSET_ID);
  }

  /** Returns true if field num_rows is set (has been assigned a value) and false otherwise */
  public boolean isSetNum_rows() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __NUM_ROWS_ISSET_ID);
  }

  public void setNum_rowsIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __NUM_ROWS_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case DB_ID:
      if (value == null) {
        unsetDb_id();
      } else {
        setDb_id((java.lang.Integer)value);
      }
      break;

    case TABLE_ID:
      if (value == null) {
        unsetTable_id();
      } else {
        setTable_id((java.lang.Integer)value);
      }
      break;

    case COLUMN_IDS:
      if (value == null) {
        unsetColumn_ids();
      } else {
        setColumn_ids((java.util.List<java.lang.Integer>)value);
      }
      break;

    case DATA:
      if (value == null) {
        unsetData();
      } else {
        setData((java.util.List<TDataBlockPtr>)value);
      }
      break;

    case NUM_ROWS:
      if (value == null) {
        unsetNum_rows();
      } else {
        setNum_rows((java.lang.Long)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case DB_ID:
      return getDb_id();

    case TABLE_ID:
      return getTable_id();

    case COLUMN_IDS:
      return getColumn_ids();

    case DATA:
      return getData();

    case NUM_ROWS:
      return getNum_rows();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case DB_ID:
      return isSetDb_id();
    case TABLE_ID:
      return isSetTable_id();
    case COLUMN_IDS:
      return isSetColumn_ids();
    case DATA:
      return isSetData();
    case NUM_ROWS:
      return isSetNum_rows();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TInsertData)
      return this.equals((TInsertData)that);
    return false;
  }

  public boolean equals(TInsertData that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_db_id = true;
    boolean that_present_db_id = true;
    if (this_present_db_id || that_present_db_id) {
      if (!(this_present_db_id && that_present_db_id))
        return false;
      if (this.db_id != that.db_id)
        return false;
    }

    boolean this_present_table_id = true;
    boolean that_present_table_id = true;
    if (this_present_table_id || that_present_table_id) {
      if (!(this_present_table_id && that_present_table_id))
        return false;
      if (this.table_id != that.table_id)
        return false;
    }

    boolean this_present_column_ids = true && this.isSetColumn_ids();
    boolean that_present_column_ids = true && that.isSetColumn_ids();
    if (this_present_column_ids || that_present_column_ids) {
      if (!(this_present_column_ids && that_present_column_ids))
        return false;
      if (!this.column_ids.equals(that.column_ids))
        return false;
    }

    boolean this_present_data = true && this.isSetData();
    boolean that_present_data = true && that.isSetData();
    if (this_present_data || that_present_data) {
      if (!(this_present_data && that_present_data))
        return false;
      if (!this.data.equals(that.data))
        return false;
    }

    boolean this_present_num_rows = true;
    boolean that_present_num_rows = true;
    if (this_present_num_rows || that_present_num_rows) {
      if (!(this_present_num_rows && that_present_num_rows))
        return false;
      if (this.num_rows != that.num_rows)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + db_id;

    hashCode = hashCode * 8191 + table_id;

    hashCode = hashCode * 8191 + ((isSetColumn_ids()) ? 131071 : 524287);
    if (isSetColumn_ids())
      hashCode = hashCode * 8191 + column_ids.hashCode();

    hashCode = hashCode * 8191 + ((isSetData()) ? 131071 : 524287);
    if (isSetData())
      hashCode = hashCode * 8191 + data.hashCode();

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(num_rows);

    return hashCode;
  }

  @Override
  public int compareTo(TInsertData other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetDb_id()).compareTo(other.isSetDb_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDb_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.db_id, other.db_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetTable_id()).compareTo(other.isSetTable_id());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTable_id()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.table_id, other.table_id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetColumn_ids()).compareTo(other.isSetColumn_ids());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetColumn_ids()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.column_ids, other.column_ids);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetData()).compareTo(other.isSetData());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetData()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.data, other.data);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetNum_rows()).compareTo(other.isSetNum_rows());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNum_rows()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.num_rows, other.num_rows);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TInsertData(");
    boolean first = true;

    sb.append("db_id:");
    sb.append(this.db_id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("table_id:");
    sb.append(this.table_id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("column_ids:");
    if (this.column_ids == null) {
      sb.append("null");
    } else {
      sb.append(this.column_ids);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("data:");
    if (this.data == null) {
      sb.append("null");
    } else {
      sb.append(this.data);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("num_rows:");
    sb.append(this.num_rows);
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

  private static class TInsertDataStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TInsertDataStandardScheme getScheme() {
      return new TInsertDataStandardScheme();
    }
  }

  private static class TInsertDataStandardScheme extends org.apache.thrift.scheme.StandardScheme<TInsertData> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TInsertData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DB_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.db_id = iprot.readI32();
              struct.setDb_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // TABLE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.table_id = iprot.readI32();
              struct.setTable_idIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // COLUMN_IDS
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list200 = iprot.readListBegin();
                struct.column_ids = new java.util.ArrayList<java.lang.Integer>(_list200.size);
                int _elem201;
                for (int _i202 = 0; _i202 < _list200.size; ++_i202)
                {
                  _elem201 = iprot.readI32();
                  struct.column_ids.add(_elem201);
                }
                iprot.readListEnd();
              }
              struct.setColumn_idsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // DATA
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list203 = iprot.readListBegin();
                struct.data = new java.util.ArrayList<TDataBlockPtr>(_list203.size);
                TDataBlockPtr _elem204;
                for (int _i205 = 0; _i205 < _list203.size; ++_i205)
                {
                  _elem204 = new TDataBlockPtr();
                  _elem204.read(iprot);
                  struct.data.add(_elem204);
                }
                iprot.readListEnd();
              }
              struct.setDataIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // NUM_ROWS
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.num_rows = iprot.readI64();
              struct.setNum_rowsIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TInsertData struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(DB_ID_FIELD_DESC);
      oprot.writeI32(struct.db_id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TABLE_ID_FIELD_DESC);
      oprot.writeI32(struct.table_id);
      oprot.writeFieldEnd();
      if (struct.column_ids != null) {
        oprot.writeFieldBegin(COLUMN_IDS_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, struct.column_ids.size()));
          for (int _iter206 : struct.column_ids)
          {
            oprot.writeI32(_iter206);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.data != null) {
        oprot.writeFieldBegin(DATA_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.data.size()));
          for (TDataBlockPtr _iter207 : struct.data)
          {
            _iter207.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(NUM_ROWS_FIELD_DESC);
      oprot.writeI64(struct.num_rows);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TInsertDataTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TInsertDataTupleScheme getScheme() {
      return new TInsertDataTupleScheme();
    }
  }

  private static class TInsertDataTupleScheme extends org.apache.thrift.scheme.TupleScheme<TInsertData> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TInsertData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetDb_id()) {
        optionals.set(0);
      }
      if (struct.isSetTable_id()) {
        optionals.set(1);
      }
      if (struct.isSetColumn_ids()) {
        optionals.set(2);
      }
      if (struct.isSetData()) {
        optionals.set(3);
      }
      if (struct.isSetNum_rows()) {
        optionals.set(4);
      }
      oprot.writeBitSet(optionals, 5);
      if (struct.isSetDb_id()) {
        oprot.writeI32(struct.db_id);
      }
      if (struct.isSetTable_id()) {
        oprot.writeI32(struct.table_id);
      }
      if (struct.isSetColumn_ids()) {
        {
          oprot.writeI32(struct.column_ids.size());
          for (int _iter208 : struct.column_ids)
          {
            oprot.writeI32(_iter208);
          }
        }
      }
      if (struct.isSetData()) {
        {
          oprot.writeI32(struct.data.size());
          for (TDataBlockPtr _iter209 : struct.data)
          {
            _iter209.write(oprot);
          }
        }
      }
      if (struct.isSetNum_rows()) {
        oprot.writeI64(struct.num_rows);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TInsertData struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(5);
      if (incoming.get(0)) {
        struct.db_id = iprot.readI32();
        struct.setDb_idIsSet(true);
      }
      if (incoming.get(1)) {
        struct.table_id = iprot.readI32();
        struct.setTable_idIsSet(true);
      }
      if (incoming.get(2)) {
        {
          org.apache.thrift.protocol.TList _list210 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.I32, iprot.readI32());
          struct.column_ids = new java.util.ArrayList<java.lang.Integer>(_list210.size);
          int _elem211;
          for (int _i212 = 0; _i212 < _list210.size; ++_i212)
          {
            _elem211 = iprot.readI32();
            struct.column_ids.add(_elem211);
          }
        }
        struct.setColumn_idsIsSet(true);
      }
      if (incoming.get(3)) {
        {
          org.apache.thrift.protocol.TList _list213 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.data = new java.util.ArrayList<TDataBlockPtr>(_list213.size);
          TDataBlockPtr _elem214;
          for (int _i215 = 0; _i215 < _list213.size; ++_i215)
          {
            _elem214 = new TDataBlockPtr();
            _elem214.read(iprot);
            struct.data.add(_elem214);
          }
        }
        struct.setDataIsSet(true);
      }
      if (incoming.get(4)) {
        struct.num_rows = iprot.readI64();
        struct.setNum_rowsIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}


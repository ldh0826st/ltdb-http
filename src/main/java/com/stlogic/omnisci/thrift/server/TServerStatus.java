/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.stlogic.omnisci.thrift.server;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2021-02-02")
public class TServerStatus implements org.apache.thrift.TBase<TServerStatus, TServerStatus._Fields>, java.io.Serializable, Cloneable, Comparable<TServerStatus> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TServerStatus");

  private static final org.apache.thrift.protocol.TField READ_ONLY_FIELD_DESC = new org.apache.thrift.protocol.TField("read_only", org.apache.thrift.protocol.TType.BOOL, (short)1);
  private static final org.apache.thrift.protocol.TField VERSION_FIELD_DESC = new org.apache.thrift.protocol.TField("version", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField RENDERING_ENABLED_FIELD_DESC = new org.apache.thrift.protocol.TField("rendering_enabled", org.apache.thrift.protocol.TType.BOOL, (short)3);
  private static final org.apache.thrift.protocol.TField START_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("start_time", org.apache.thrift.protocol.TType.I64, (short)4);
  private static final org.apache.thrift.protocol.TField EDITION_FIELD_DESC = new org.apache.thrift.protocol.TField("edition", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField HOST_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("host_name", org.apache.thrift.protocol.TType.STRING, (short)6);
  private static final org.apache.thrift.protocol.TField POLY_RENDERING_ENABLED_FIELD_DESC = new org.apache.thrift.protocol.TField("poly_rendering_enabled", org.apache.thrift.protocol.TType.BOOL, (short)7);
  private static final org.apache.thrift.protocol.TField ROLE_FIELD_DESC = new org.apache.thrift.protocol.TField("role", org.apache.thrift.protocol.TType.I32, (short)8);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new TServerStatusStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new TServerStatusTupleSchemeFactory();

  public boolean read_only; // required
  public java.lang.String version; // required
  public boolean rendering_enabled; // required
  public long start_time; // required
  public java.lang.String edition; // required
  public java.lang.String host_name; // required
  public boolean poly_rendering_enabled; // required
  /**
   * 
   * @see TRole
   */
  public TRole role; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    READ_ONLY((short)1, "read_only"),
    VERSION((short)2, "version"),
    RENDERING_ENABLED((short)3, "rendering_enabled"),
    START_TIME((short)4, "start_time"),
    EDITION((short)5, "edition"),
    HOST_NAME((short)6, "host_name"),
    POLY_RENDERING_ENABLED((short)7, "poly_rendering_enabled"),
    /**
     * 
     * @see TRole
     */
    ROLE((short)8, "role");

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
        case 1: // READ_ONLY
          return READ_ONLY;
        case 2: // VERSION
          return VERSION;
        case 3: // RENDERING_ENABLED
          return RENDERING_ENABLED;
        case 4: // START_TIME
          return START_TIME;
        case 5: // EDITION
          return EDITION;
        case 6: // HOST_NAME
          return HOST_NAME;
        case 7: // POLY_RENDERING_ENABLED
          return POLY_RENDERING_ENABLED;
        case 8: // ROLE
          return ROLE;
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
  private static final int __READ_ONLY_ISSET_ID = 0;
  private static final int __RENDERING_ENABLED_ISSET_ID = 1;
  private static final int __START_TIME_ISSET_ID = 2;
  private static final int __POLY_RENDERING_ENABLED_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.READ_ONLY, new org.apache.thrift.meta_data.FieldMetaData("read_only", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.VERSION, new org.apache.thrift.meta_data.FieldMetaData("version", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.RENDERING_ENABLED, new org.apache.thrift.meta_data.FieldMetaData("rendering_enabled", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.START_TIME, new org.apache.thrift.meta_data.FieldMetaData("start_time", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.EDITION, new org.apache.thrift.meta_data.FieldMetaData("edition", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.HOST_NAME, new org.apache.thrift.meta_data.FieldMetaData("host_name", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.POLY_RENDERING_ENABLED, new org.apache.thrift.meta_data.FieldMetaData("poly_rendering_enabled", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.ROLE, new org.apache.thrift.meta_data.FieldMetaData("role", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, TRole.class)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TServerStatus.class, metaDataMap);
  }

  public TServerStatus() {
  }

  public TServerStatus(
    boolean read_only,
    java.lang.String version,
    boolean rendering_enabled,
    long start_time,
    java.lang.String edition,
    java.lang.String host_name,
    boolean poly_rendering_enabled,
    TRole role)
  {
    this();
    this.read_only = read_only;
    setRead_onlyIsSet(true);
    this.version = version;
    this.rendering_enabled = rendering_enabled;
    setRendering_enabledIsSet(true);
    this.start_time = start_time;
    setStart_timeIsSet(true);
    this.edition = edition;
    this.host_name = host_name;
    this.poly_rendering_enabled = poly_rendering_enabled;
    setPoly_rendering_enabledIsSet(true);
    this.role = role;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TServerStatus(TServerStatus other) {
    __isset_bitfield = other.__isset_bitfield;
    this.read_only = other.read_only;
    if (other.isSetVersion()) {
      this.version = other.version;
    }
    this.rendering_enabled = other.rendering_enabled;
    this.start_time = other.start_time;
    if (other.isSetEdition()) {
      this.edition = other.edition;
    }
    if (other.isSetHost_name()) {
      this.host_name = other.host_name;
    }
    this.poly_rendering_enabled = other.poly_rendering_enabled;
    if (other.isSetRole()) {
      this.role = other.role;
    }
  }

  public TServerStatus deepCopy() {
    return new TServerStatus(this);
  }

  @Override
  public void clear() {
    setRead_onlyIsSet(false);
    this.read_only = false;
    this.version = null;
    setRendering_enabledIsSet(false);
    this.rendering_enabled = false;
    setStart_timeIsSet(false);
    this.start_time = 0;
    this.edition = null;
    this.host_name = null;
    setPoly_rendering_enabledIsSet(false);
    this.poly_rendering_enabled = false;
    this.role = null;
  }

  public boolean isRead_only() {
    return this.read_only;
  }

  public TServerStatus setRead_only(boolean read_only) {
    this.read_only = read_only;
    setRead_onlyIsSet(true);
    return this;
  }

  public void unsetRead_only() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __READ_ONLY_ISSET_ID);
  }

  /** Returns true if field read_only is set (has been assigned a value) and false otherwise */
  public boolean isSetRead_only() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __READ_ONLY_ISSET_ID);
  }

  public void setRead_onlyIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __READ_ONLY_ISSET_ID, value);
  }

  public java.lang.String getVersion() {
    return this.version;
  }

  public TServerStatus setVersion(java.lang.String version) {
    this.version = version;
    return this;
  }

  public void unsetVersion() {
    this.version = null;
  }

  /** Returns true if field version is set (has been assigned a value) and false otherwise */
  public boolean isSetVersion() {
    return this.version != null;
  }

  public void setVersionIsSet(boolean value) {
    if (!value) {
      this.version = null;
    }
  }

  public boolean isRendering_enabled() {
    return this.rendering_enabled;
  }

  public TServerStatus setRendering_enabled(boolean rendering_enabled) {
    this.rendering_enabled = rendering_enabled;
    setRendering_enabledIsSet(true);
    return this;
  }

  public void unsetRendering_enabled() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __RENDERING_ENABLED_ISSET_ID);
  }

  /** Returns true if field rendering_enabled is set (has been assigned a value) and false otherwise */
  public boolean isSetRendering_enabled() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __RENDERING_ENABLED_ISSET_ID);
  }

  public void setRendering_enabledIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __RENDERING_ENABLED_ISSET_ID, value);
  }

  public long getStart_time() {
    return this.start_time;
  }

  public TServerStatus setStart_time(long start_time) {
    this.start_time = start_time;
    setStart_timeIsSet(true);
    return this;
  }

  public void unsetStart_time() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __START_TIME_ISSET_ID);
  }

  /** Returns true if field start_time is set (has been assigned a value) and false otherwise */
  public boolean isSetStart_time() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __START_TIME_ISSET_ID);
  }

  public void setStart_timeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __START_TIME_ISSET_ID, value);
  }

  public java.lang.String getEdition() {
    return this.edition;
  }

  public TServerStatus setEdition(java.lang.String edition) {
    this.edition = edition;
    return this;
  }

  public void unsetEdition() {
    this.edition = null;
  }

  /** Returns true if field edition is set (has been assigned a value) and false otherwise */
  public boolean isSetEdition() {
    return this.edition != null;
  }

  public void setEditionIsSet(boolean value) {
    if (!value) {
      this.edition = null;
    }
  }

  public java.lang.String getHost_name() {
    return this.host_name;
  }

  public TServerStatus setHost_name(java.lang.String host_name) {
    this.host_name = host_name;
    return this;
  }

  public void unsetHost_name() {
    this.host_name = null;
  }

  /** Returns true if field host_name is set (has been assigned a value) and false otherwise */
  public boolean isSetHost_name() {
    return this.host_name != null;
  }

  public void setHost_nameIsSet(boolean value) {
    if (!value) {
      this.host_name = null;
    }
  }

  public boolean isPoly_rendering_enabled() {
    return this.poly_rendering_enabled;
  }

  public TServerStatus setPoly_rendering_enabled(boolean poly_rendering_enabled) {
    this.poly_rendering_enabled = poly_rendering_enabled;
    setPoly_rendering_enabledIsSet(true);
    return this;
  }

  public void unsetPoly_rendering_enabled() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __POLY_RENDERING_ENABLED_ISSET_ID);
  }

  /** Returns true if field poly_rendering_enabled is set (has been assigned a value) and false otherwise */
  public boolean isSetPoly_rendering_enabled() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __POLY_RENDERING_ENABLED_ISSET_ID);
  }

  public void setPoly_rendering_enabledIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __POLY_RENDERING_ENABLED_ISSET_ID, value);
  }

  /**
   * 
   * @see TRole
   */
  public TRole getRole() {
    return this.role;
  }

  /**
   * 
   * @see TRole
   */
  public TServerStatus setRole(TRole role) {
    this.role = role;
    return this;
  }

  public void unsetRole() {
    this.role = null;
  }

  /** Returns true if field role is set (has been assigned a value) and false otherwise */
  public boolean isSetRole() {
    return this.role != null;
  }

  public void setRoleIsSet(boolean value) {
    if (!value) {
      this.role = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case READ_ONLY:
      if (value == null) {
        unsetRead_only();
      } else {
        setRead_only((java.lang.Boolean)value);
      }
      break;

    case VERSION:
      if (value == null) {
        unsetVersion();
      } else {
        setVersion((java.lang.String)value);
      }
      break;

    case RENDERING_ENABLED:
      if (value == null) {
        unsetRendering_enabled();
      } else {
        setRendering_enabled((java.lang.Boolean)value);
      }
      break;

    case START_TIME:
      if (value == null) {
        unsetStart_time();
      } else {
        setStart_time((java.lang.Long)value);
      }
      break;

    case EDITION:
      if (value == null) {
        unsetEdition();
      } else {
        setEdition((java.lang.String)value);
      }
      break;

    case HOST_NAME:
      if (value == null) {
        unsetHost_name();
      } else {
        setHost_name((java.lang.String)value);
      }
      break;

    case POLY_RENDERING_ENABLED:
      if (value == null) {
        unsetPoly_rendering_enabled();
      } else {
        setPoly_rendering_enabled((java.lang.Boolean)value);
      }
      break;

    case ROLE:
      if (value == null) {
        unsetRole();
      } else {
        setRole((TRole)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case READ_ONLY:
      return isRead_only();

    case VERSION:
      return getVersion();

    case RENDERING_ENABLED:
      return isRendering_enabled();

    case START_TIME:
      return getStart_time();

    case EDITION:
      return getEdition();

    case HOST_NAME:
      return getHost_name();

    case POLY_RENDERING_ENABLED:
      return isPoly_rendering_enabled();

    case ROLE:
      return getRole();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case READ_ONLY:
      return isSetRead_only();
    case VERSION:
      return isSetVersion();
    case RENDERING_ENABLED:
      return isSetRendering_enabled();
    case START_TIME:
      return isSetStart_time();
    case EDITION:
      return isSetEdition();
    case HOST_NAME:
      return isSetHost_name();
    case POLY_RENDERING_ENABLED:
      return isSetPoly_rendering_enabled();
    case ROLE:
      return isSetRole();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof TServerStatus)
      return this.equals((TServerStatus)that);
    return false;
  }

  public boolean equals(TServerStatus that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_read_only = true;
    boolean that_present_read_only = true;
    if (this_present_read_only || that_present_read_only) {
      if (!(this_present_read_only && that_present_read_only))
        return false;
      if (this.read_only != that.read_only)
        return false;
    }

    boolean this_present_version = true && this.isSetVersion();
    boolean that_present_version = true && that.isSetVersion();
    if (this_present_version || that_present_version) {
      if (!(this_present_version && that_present_version))
        return false;
      if (!this.version.equals(that.version))
        return false;
    }

    boolean this_present_rendering_enabled = true;
    boolean that_present_rendering_enabled = true;
    if (this_present_rendering_enabled || that_present_rendering_enabled) {
      if (!(this_present_rendering_enabled && that_present_rendering_enabled))
        return false;
      if (this.rendering_enabled != that.rendering_enabled)
        return false;
    }

    boolean this_present_start_time = true;
    boolean that_present_start_time = true;
    if (this_present_start_time || that_present_start_time) {
      if (!(this_present_start_time && that_present_start_time))
        return false;
      if (this.start_time != that.start_time)
        return false;
    }

    boolean this_present_edition = true && this.isSetEdition();
    boolean that_present_edition = true && that.isSetEdition();
    if (this_present_edition || that_present_edition) {
      if (!(this_present_edition && that_present_edition))
        return false;
      if (!this.edition.equals(that.edition))
        return false;
    }

    boolean this_present_host_name = true && this.isSetHost_name();
    boolean that_present_host_name = true && that.isSetHost_name();
    if (this_present_host_name || that_present_host_name) {
      if (!(this_present_host_name && that_present_host_name))
        return false;
      if (!this.host_name.equals(that.host_name))
        return false;
    }

    boolean this_present_poly_rendering_enabled = true;
    boolean that_present_poly_rendering_enabled = true;
    if (this_present_poly_rendering_enabled || that_present_poly_rendering_enabled) {
      if (!(this_present_poly_rendering_enabled && that_present_poly_rendering_enabled))
        return false;
      if (this.poly_rendering_enabled != that.poly_rendering_enabled)
        return false;
    }

    boolean this_present_role = true && this.isSetRole();
    boolean that_present_role = true && that.isSetRole();
    if (this_present_role || that_present_role) {
      if (!(this_present_role && that_present_role))
        return false;
      if (!this.role.equals(that.role))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((read_only) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((isSetVersion()) ? 131071 : 524287);
    if (isSetVersion())
      hashCode = hashCode * 8191 + version.hashCode();

    hashCode = hashCode * 8191 + ((rendering_enabled) ? 131071 : 524287);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(start_time);

    hashCode = hashCode * 8191 + ((isSetEdition()) ? 131071 : 524287);
    if (isSetEdition())
      hashCode = hashCode * 8191 + edition.hashCode();

    hashCode = hashCode * 8191 + ((isSetHost_name()) ? 131071 : 524287);
    if (isSetHost_name())
      hashCode = hashCode * 8191 + host_name.hashCode();

    hashCode = hashCode * 8191 + ((poly_rendering_enabled) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((isSetRole()) ? 131071 : 524287);
    if (isSetRole())
      hashCode = hashCode * 8191 + role.getValue();

    return hashCode;
  }

  @Override
  public int compareTo(TServerStatus other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetRead_only()).compareTo(other.isSetRead_only());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRead_only()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.read_only, other.read_only);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetVersion()).compareTo(other.isSetVersion());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetVersion()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.version, other.version);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRendering_enabled()).compareTo(other.isSetRendering_enabled());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRendering_enabled()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.rendering_enabled, other.rendering_enabled);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetStart_time()).compareTo(other.isSetStart_time());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStart_time()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.start_time, other.start_time);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetEdition()).compareTo(other.isSetEdition());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetEdition()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.edition, other.edition);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetHost_name()).compareTo(other.isSetHost_name());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetHost_name()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.host_name, other.host_name);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetPoly_rendering_enabled()).compareTo(other.isSetPoly_rendering_enabled());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetPoly_rendering_enabled()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.poly_rendering_enabled, other.poly_rendering_enabled);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetRole()).compareTo(other.isSetRole());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetRole()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.role, other.role);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("TServerStatus(");
    boolean first = true;

    sb.append("read_only:");
    sb.append(this.read_only);
    first = false;
    if (!first) sb.append(", ");
    sb.append("version:");
    if (this.version == null) {
      sb.append("null");
    } else {
      sb.append(this.version);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("rendering_enabled:");
    sb.append(this.rendering_enabled);
    first = false;
    if (!first) sb.append(", ");
    sb.append("start_time:");
    sb.append(this.start_time);
    first = false;
    if (!first) sb.append(", ");
    sb.append("edition:");
    if (this.edition == null) {
      sb.append("null");
    } else {
      sb.append(this.edition);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("host_name:");
    if (this.host_name == null) {
      sb.append("null");
    } else {
      sb.append(this.host_name);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("poly_rendering_enabled:");
    sb.append(this.poly_rendering_enabled);
    first = false;
    if (!first) sb.append(", ");
    sb.append("role:");
    if (this.role == null) {
      sb.append("null");
    } else {
      sb.append(this.role);
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TServerStatusStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TServerStatusStandardScheme getScheme() {
      return new TServerStatusStandardScheme();
    }
  }

  private static class TServerStatusStandardScheme extends org.apache.thrift.scheme.StandardScheme<TServerStatus> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TServerStatus struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // READ_ONLY
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.read_only = iprot.readBool();
              struct.setRead_onlyIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // VERSION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.version = iprot.readString();
              struct.setVersionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // RENDERING_ENABLED
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.rendering_enabled = iprot.readBool();
              struct.setRendering_enabledIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // START_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.start_time = iprot.readI64();
              struct.setStart_timeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // EDITION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.edition = iprot.readString();
              struct.setEditionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // HOST_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.host_name = iprot.readString();
              struct.setHost_nameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // POLY_RENDERING_ENABLED
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.poly_rendering_enabled = iprot.readBool();
              struct.setPoly_rendering_enabledIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 8: // ROLE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.role = com.stlogic.omnisci.thrift.server.TRole.findByValue(iprot.readI32());
              struct.setRoleIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, TServerStatus struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(READ_ONLY_FIELD_DESC);
      oprot.writeBool(struct.read_only);
      oprot.writeFieldEnd();
      if (struct.version != null) {
        oprot.writeFieldBegin(VERSION_FIELD_DESC);
        oprot.writeString(struct.version);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(RENDERING_ENABLED_FIELD_DESC);
      oprot.writeBool(struct.rendering_enabled);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(START_TIME_FIELD_DESC);
      oprot.writeI64(struct.start_time);
      oprot.writeFieldEnd();
      if (struct.edition != null) {
        oprot.writeFieldBegin(EDITION_FIELD_DESC);
        oprot.writeString(struct.edition);
        oprot.writeFieldEnd();
      }
      if (struct.host_name != null) {
        oprot.writeFieldBegin(HOST_NAME_FIELD_DESC);
        oprot.writeString(struct.host_name);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(POLY_RENDERING_ENABLED_FIELD_DESC);
      oprot.writeBool(struct.poly_rendering_enabled);
      oprot.writeFieldEnd();
      if (struct.role != null) {
        oprot.writeFieldBegin(ROLE_FIELD_DESC);
        oprot.writeI32(struct.role.getValue());
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TServerStatusTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public TServerStatusTupleScheme getScheme() {
      return new TServerStatusTupleScheme();
    }
  }

  private static class TServerStatusTupleScheme extends org.apache.thrift.scheme.TupleScheme<TServerStatus> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TServerStatus struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetRead_only()) {
        optionals.set(0);
      }
      if (struct.isSetVersion()) {
        optionals.set(1);
      }
      if (struct.isSetRendering_enabled()) {
        optionals.set(2);
      }
      if (struct.isSetStart_time()) {
        optionals.set(3);
      }
      if (struct.isSetEdition()) {
        optionals.set(4);
      }
      if (struct.isSetHost_name()) {
        optionals.set(5);
      }
      if (struct.isSetPoly_rendering_enabled()) {
        optionals.set(6);
      }
      if (struct.isSetRole()) {
        optionals.set(7);
      }
      oprot.writeBitSet(optionals, 8);
      if (struct.isSetRead_only()) {
        oprot.writeBool(struct.read_only);
      }
      if (struct.isSetVersion()) {
        oprot.writeString(struct.version);
      }
      if (struct.isSetRendering_enabled()) {
        oprot.writeBool(struct.rendering_enabled);
      }
      if (struct.isSetStart_time()) {
        oprot.writeI64(struct.start_time);
      }
      if (struct.isSetEdition()) {
        oprot.writeString(struct.edition);
      }
      if (struct.isSetHost_name()) {
        oprot.writeString(struct.host_name);
      }
      if (struct.isSetPoly_rendering_enabled()) {
        oprot.writeBool(struct.poly_rendering_enabled);
      }
      if (struct.isSetRole()) {
        oprot.writeI32(struct.role.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TServerStatus struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(8);
      if (incoming.get(0)) {
        struct.read_only = iprot.readBool();
        struct.setRead_onlyIsSet(true);
      }
      if (incoming.get(1)) {
        struct.version = iprot.readString();
        struct.setVersionIsSet(true);
      }
      if (incoming.get(2)) {
        struct.rendering_enabled = iprot.readBool();
        struct.setRendering_enabledIsSet(true);
      }
      if (incoming.get(3)) {
        struct.start_time = iprot.readI64();
        struct.setStart_timeIsSet(true);
      }
      if (incoming.get(4)) {
        struct.edition = iprot.readString();
        struct.setEditionIsSet(true);
      }
      if (incoming.get(5)) {
        struct.host_name = iprot.readString();
        struct.setHost_nameIsSet(true);
      }
      if (incoming.get(6)) {
        struct.poly_rendering_enabled = iprot.readBool();
        struct.setPoly_rendering_enabledIsSet(true);
      }
      if (incoming.get(7)) {
        struct.role = com.stlogic.omnisci.thrift.server.TRole.findByValue(iprot.readI32());
        struct.setRoleIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}


/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.chen.demo.thrift.model;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-05-03")
public class QueryParameter implements org.apache.thrift.TBase<QueryParameter, QueryParameter._Fields>, java.io.Serializable, Cloneable, Comparable<QueryParameter> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("QueryParameter");

  private static final org.apache.thrift.protocol.TField AGE_START_FIELD_DESC = new org.apache.thrift.protocol.TField("ageStart", org.apache.thrift.protocol.TType.I16, (short)1);
  private static final org.apache.thrift.protocol.TField AGE_END_FIELD_DESC = new org.apache.thrift.protocol.TField("ageEnd", org.apache.thrift.protocol.TType.I16, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new QueryParameterStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new QueryParameterTupleSchemeFactory();

  public short ageStart; // required
  public short ageEnd; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    AGE_START((short)1, "ageStart"),
    AGE_END((short)2, "ageEnd");

    private static final java.util.Map<String, _Fields> byName = new java.util.HashMap<String, _Fields>();

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
        case 1: // AGE_START
          return AGE_START;
        case 2: // AGE_END
          return AGE_END;
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
  private static final int __AGESTART_ISSET_ID = 0;
  private static final int __AGEEND_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.AGE_START, new org.apache.thrift.meta_data.FieldMetaData("ageStart", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    tmpMap.put(_Fields.AGE_END, new org.apache.thrift.meta_data.FieldMetaData("ageEnd", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I16)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(QueryParameter.class, metaDataMap);
  }

  public QueryParameter() {
  }

  public QueryParameter(
    short ageStart,
    short ageEnd)
  {
    this();
    this.ageStart = ageStart;
    setAgeStartIsSet(true);
    this.ageEnd = ageEnd;
    setAgeEndIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public QueryParameter(QueryParameter other) {
    __isset_bitfield = other.__isset_bitfield;
    this.ageStart = other.ageStart;
    this.ageEnd = other.ageEnd;
  }

  public QueryParameter deepCopy() {
    return new QueryParameter(this);
  }

  @Override
  public void clear() {
    setAgeStartIsSet(false);
    this.ageStart = 0;
    setAgeEndIsSet(false);
    this.ageEnd = 0;
  }

  public short getAgeStart() {
    return this.ageStart;
  }

  public QueryParameter setAgeStart(short ageStart) {
    this.ageStart = ageStart;
    setAgeStartIsSet(true);
    return this;
  }

  public void unsetAgeStart() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __AGESTART_ISSET_ID);
  }

  /** Returns true if field ageStart is set (has been assigned a value) and false otherwise */
  public boolean isSetAgeStart() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __AGESTART_ISSET_ID);
  }

  public void setAgeStartIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __AGESTART_ISSET_ID, value);
  }

  public short getAgeEnd() {
    return this.ageEnd;
  }

  public QueryParameter setAgeEnd(short ageEnd) {
    this.ageEnd = ageEnd;
    setAgeEndIsSet(true);
    return this;
  }

  public void unsetAgeEnd() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __AGEEND_ISSET_ID);
  }

  /** Returns true if field ageEnd is set (has been assigned a value) and false otherwise */
  public boolean isSetAgeEnd() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __AGEEND_ISSET_ID);
  }

  public void setAgeEndIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __AGEEND_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case AGE_START:
      if (value == null) {
        unsetAgeStart();
      } else {
        setAgeStart((Short)value);
      }
      break;

    case AGE_END:
      if (value == null) {
        unsetAgeEnd();
      } else {
        setAgeEnd((Short)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case AGE_START:
      return getAgeStart();

    case AGE_END:
      return getAgeEnd();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case AGE_START:
      return isSetAgeStart();
    case AGE_END:
      return isSetAgeEnd();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof QueryParameter)
      return this.equals((QueryParameter)that);
    return false;
  }

  public boolean equals(QueryParameter that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_ageStart = true;
    boolean that_present_ageStart = true;
    if (this_present_ageStart || that_present_ageStart) {
      if (!(this_present_ageStart && that_present_ageStart))
        return false;
      if (this.ageStart != that.ageStart)
        return false;
    }

    boolean this_present_ageEnd = true;
    boolean that_present_ageEnd = true;
    if (this_present_ageEnd || that_present_ageEnd) {
      if (!(this_present_ageEnd && that_present_ageEnd))
        return false;
      if (this.ageEnd != that.ageEnd)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ageStart;

    hashCode = hashCode * 8191 + ageEnd;

    return hashCode;
  }

  @Override
  public int compareTo(QueryParameter other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetAgeStart()).compareTo(other.isSetAgeStart());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAgeStart()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ageStart, other.ageStart);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAgeEnd()).compareTo(other.isSetAgeEnd());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAgeEnd()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.ageEnd, other.ageEnd);
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
  public String toString() {
    StringBuilder sb = new StringBuilder("QueryParameter(");
    boolean first = true;

    sb.append("ageStart:");
    sb.append(this.ageStart);
    first = false;
    if (!first) sb.append(", ");
    sb.append("ageEnd:");
    sb.append(this.ageEnd);
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

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class QueryParameterStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public QueryParameterStandardScheme getScheme() {
      return new QueryParameterStandardScheme();
    }
  }

  private static class QueryParameterStandardScheme extends org.apache.thrift.scheme.StandardScheme<QueryParameter> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, QueryParameter struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // AGE_START
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.ageStart = iprot.readI16();
              struct.setAgeStartIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // AGE_END
            if (schemeField.type == org.apache.thrift.protocol.TType.I16) {
              struct.ageEnd = iprot.readI16();
              struct.setAgeEndIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, QueryParameter struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(AGE_START_FIELD_DESC);
      oprot.writeI16(struct.ageStart);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(AGE_END_FIELD_DESC);
      oprot.writeI16(struct.ageEnd);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class QueryParameterTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public QueryParameterTupleScheme getScheme() {
      return new QueryParameterTupleScheme();
    }
  }

  private static class QueryParameterTupleScheme extends org.apache.thrift.scheme.TupleScheme<QueryParameter> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, QueryParameter struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetAgeStart()) {
        optionals.set(0);
      }
      if (struct.isSetAgeEnd()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetAgeStart()) {
        oprot.writeI16(struct.ageStart);
      }
      if (struct.isSetAgeEnd()) {
        oprot.writeI16(struct.ageEnd);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, QueryParameter struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.ageStart = iprot.readI16();
        struct.setAgeStartIsSet(true);
      }
      if (incoming.get(1)) {
        struct.ageEnd = iprot.readI16();
        struct.setAgeEndIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

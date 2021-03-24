/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.stlogic.omnisci.thrift.server;


public enum TRole implements org.apache.thrift.TEnum {
  SERVER(0),
  AGGREGATOR(1),
  LEAF(2),
  STRING_DICTIONARY(3);

  private final int value;

  private TRole(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static TRole findByValue(int value) { 
    switch (value) {
      case 0:
        return SERVER;
      case 1:
        return AGGREGATOR;
      case 2:
        return LEAF;
      case 3:
        return STRING_DICTIONARY;
      default:
        return null;
    }
  }
}

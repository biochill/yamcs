// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: gap-request.proto

package org.yamcs.protobuf;

public final class GapRequest {
  private GapRequest() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public static final class CcsdsGap extends
      com.google.protobuf.GeneratedMessage {
    // Use CcsdsGap.newBuilder() to construct.
    private CcsdsGap() {
      initFields();
    }
    private CcsdsGap(boolean noInit) {}
    
    private static final CcsdsGap defaultInstance;
    public static CcsdsGap getDefaultInstance() {
      return defaultInstance;
    }
    
    public CcsdsGap getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.yamcs.protobuf.GapRequest.internal_static_gaprequest_CcsdsGap_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.yamcs.protobuf.GapRequest.internal_static_gaprequest_CcsdsGap_fieldAccessorTable;
    }
    
    // optional int32 apid = 1;
    public static final int APID_FIELD_NUMBER = 1;
    private boolean hasApid;
    private int apid_ = 0;
    public boolean hasApid() { return hasApid; }
    public int getApid() { return apid_; }
    
    // required int64 startTime = 2;
    public static final int STARTTIME_FIELD_NUMBER = 2;
    private boolean hasStartTime;
    private long startTime_ = 0L;
    public boolean hasStartTime() { return hasStartTime; }
    public long getStartTime() { return startTime_; }
    
    // required int64 stopTime = 3;
    public static final int STOPTIME_FIELD_NUMBER = 3;
    private boolean hasStopTime;
    private long stopTime_ = 0L;
    public boolean hasStopTime() { return hasStopTime; }
    public long getStopTime() { return stopTime_; }
    
    private void initFields() {
    }
    public final boolean isInitialized() {
      if (!hasStartTime) return false;
      if (!hasStopTime) return false;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (hasApid()) {
        output.writeInt32(1, getApid());
      }
      if (hasStartTime()) {
        output.writeInt64(2, getStartTime());
      }
      if (hasStopTime()) {
        output.writeInt64(3, getStopTime());
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (hasApid()) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, getApid());
      }
      if (hasStartTime()) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(2, getStartTime());
      }
      if (hasStopTime()) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(3, getStopTime());
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    public static org.yamcs.protobuf.GapRequest.CcsdsGap parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGap parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGap parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGap parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGap parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGap parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGap parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGap parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGap parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGap parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(org.yamcs.protobuf.GapRequest.CcsdsGap prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> {
      private org.yamcs.protobuf.GapRequest.CcsdsGap result;
      
      // Construct using org.yamcs.protobuf.GapRequest.CcsdsGap.newBuilder()
      private Builder() {}
      
      private static Builder create() {
        Builder builder = new Builder();
        builder.result = new org.yamcs.protobuf.GapRequest.CcsdsGap();
        return builder;
      }
      
      protected org.yamcs.protobuf.GapRequest.CcsdsGap internalGetResult() {
        return result;
      }
      
      public Builder clear() {
        if (result == null) {
          throw new IllegalStateException(
            "Cannot call clear() after build().");
        }
        result = new org.yamcs.protobuf.GapRequest.CcsdsGap();
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(result);
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.yamcs.protobuf.GapRequest.CcsdsGap.getDescriptor();
      }
      
      public org.yamcs.protobuf.GapRequest.CcsdsGap getDefaultInstanceForType() {
        return org.yamcs.protobuf.GapRequest.CcsdsGap.getDefaultInstance();
      }
      
      public boolean isInitialized() {
        return result.isInitialized();
      }
      public org.yamcs.protobuf.GapRequest.CcsdsGap build() {
        if (result != null && !isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return buildPartial();
      }
      
      private org.yamcs.protobuf.GapRequest.CcsdsGap buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        if (!isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return buildPartial();
      }
      
      public org.yamcs.protobuf.GapRequest.CcsdsGap buildPartial() {
        if (result == null) {
          throw new IllegalStateException(
            "build() has already been called on this Builder.");
        }
        org.yamcs.protobuf.GapRequest.CcsdsGap returnMe = result;
        result = null;
        return returnMe;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.yamcs.protobuf.GapRequest.CcsdsGap) {
          return mergeFrom((org.yamcs.protobuf.GapRequest.CcsdsGap)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(org.yamcs.protobuf.GapRequest.CcsdsGap other) {
        if (other == org.yamcs.protobuf.GapRequest.CcsdsGap.getDefaultInstance()) return this;
        if (other.hasApid()) {
          setApid(other.getApid());
        }
        if (other.hasStartTime()) {
          setStartTime(other.getStartTime());
        }
        if (other.hasStopTime()) {
          setStopTime(other.getStopTime());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                return this;
              }
              break;
            }
            case 8: {
              setApid(input.readInt32());
              break;
            }
            case 16: {
              setStartTime(input.readInt64());
              break;
            }
            case 24: {
              setStopTime(input.readInt64());
              break;
            }
          }
        }
      }
      
      
      // optional int32 apid = 1;
      public boolean hasApid() {
        return result.hasApid();
      }
      public int getApid() {
        return result.getApid();
      }
      public Builder setApid(int value) {
        result.hasApid = true;
        result.apid_ = value;
        return this;
      }
      public Builder clearApid() {
        result.hasApid = false;
        result.apid_ = 0;
        return this;
      }
      
      // required int64 startTime = 2;
      public boolean hasStartTime() {
        return result.hasStartTime();
      }
      public long getStartTime() {
        return result.getStartTime();
      }
      public Builder setStartTime(long value) {
        result.hasStartTime = true;
        result.startTime_ = value;
        return this;
      }
      public Builder clearStartTime() {
        result.hasStartTime = false;
        result.startTime_ = 0L;
        return this;
      }
      
      // required int64 stopTime = 3;
      public boolean hasStopTime() {
        return result.hasStopTime();
      }
      public long getStopTime() {
        return result.getStopTime();
      }
      public Builder setStopTime(long value) {
        result.hasStopTime = true;
        result.stopTime_ = value;
        return this;
      }
      public Builder clearStopTime() {
        result.hasStopTime = false;
        result.stopTime_ = 0L;
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:gaprequest.CcsdsGap)
    }
    
    static {
      defaultInstance = new CcsdsGap(true);
      org.yamcs.protobuf.GapRequest.internalForceInit();
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:gaprequest.CcsdsGap)
  }
  
  public static final class CcsdsGapRequest extends
      com.google.protobuf.GeneratedMessage {
    // Use CcsdsGapRequest.newBuilder() to construct.
    private CcsdsGapRequest() {
      initFields();
    }
    private CcsdsGapRequest(boolean noInit) {}
    
    private static final CcsdsGapRequest defaultInstance;
    public static CcsdsGapRequest getDefaultInstance() {
      return defaultInstance;
    }
    
    public CcsdsGapRequest getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return org.yamcs.protobuf.GapRequest.internal_static_gaprequest_CcsdsGapRequest_descriptor;
    }
    
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return org.yamcs.protobuf.GapRequest.internal_static_gaprequest_CcsdsGapRequest_fieldAccessorTable;
    }
    
    // repeated .gaprequest.CcsdsGap gaps = 1;
    public static final int GAPS_FIELD_NUMBER = 1;
    private java.util.List<org.yamcs.protobuf.GapRequest.CcsdsGap> gaps_ =
      java.util.Collections.emptyList();
    public java.util.List<org.yamcs.protobuf.GapRequest.CcsdsGap> getGapsList() {
      return gaps_;
    }
    public int getGapsCount() { return gaps_.size(); }
    public org.yamcs.protobuf.GapRequest.CcsdsGap getGaps(int index) {
      return gaps_.get(index);
    }
    
    private void initFields() {
    }
    public final boolean isInitialized() {
      for (org.yamcs.protobuf.GapRequest.CcsdsGap element : getGapsList()) {
        if (!element.isInitialized()) return false;
      }
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      for (org.yamcs.protobuf.GapRequest.CcsdsGap element : getGapsList()) {
        output.writeMessage(1, element);
      }
      getUnknownFields().writeTo(output);
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      for (org.yamcs.protobuf.GapRequest.CcsdsGap element : getGapsList()) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, element);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }
    
    public static org.yamcs.protobuf.GapRequest.CcsdsGapRequest parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGapRequest parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGapRequest parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGapRequest parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGapRequest parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGapRequest parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGapRequest parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGapRequest parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input, extensionRegistry)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGapRequest parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static org.yamcs.protobuf.GapRequest.CcsdsGapRequest parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(org.yamcs.protobuf.GapRequest.CcsdsGapRequest prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> {
      private org.yamcs.protobuf.GapRequest.CcsdsGapRequest result;
      
      // Construct using org.yamcs.protobuf.GapRequest.CcsdsGapRequest.newBuilder()
      private Builder() {}
      
      private static Builder create() {
        Builder builder = new Builder();
        builder.result = new org.yamcs.protobuf.GapRequest.CcsdsGapRequest();
        return builder;
      }
      
      protected org.yamcs.protobuf.GapRequest.CcsdsGapRequest internalGetResult() {
        return result;
      }
      
      public Builder clear() {
        if (result == null) {
          throw new IllegalStateException(
            "Cannot call clear() after build().");
        }
        result = new org.yamcs.protobuf.GapRequest.CcsdsGapRequest();
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(result);
      }
      
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return org.yamcs.protobuf.GapRequest.CcsdsGapRequest.getDescriptor();
      }
      
      public org.yamcs.protobuf.GapRequest.CcsdsGapRequest getDefaultInstanceForType() {
        return org.yamcs.protobuf.GapRequest.CcsdsGapRequest.getDefaultInstance();
      }
      
      public boolean isInitialized() {
        return result.isInitialized();
      }
      public org.yamcs.protobuf.GapRequest.CcsdsGapRequest build() {
        if (result != null && !isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return buildPartial();
      }
      
      private org.yamcs.protobuf.GapRequest.CcsdsGapRequest buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        if (!isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return buildPartial();
      }
      
      public org.yamcs.protobuf.GapRequest.CcsdsGapRequest buildPartial() {
        if (result == null) {
          throw new IllegalStateException(
            "build() has already been called on this Builder.");
        }
        if (result.gaps_ != java.util.Collections.EMPTY_LIST) {
          result.gaps_ =
            java.util.Collections.unmodifiableList(result.gaps_);
        }
        org.yamcs.protobuf.GapRequest.CcsdsGapRequest returnMe = result;
        result = null;
        return returnMe;
      }
      
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.yamcs.protobuf.GapRequest.CcsdsGapRequest) {
          return mergeFrom((org.yamcs.protobuf.GapRequest.CcsdsGapRequest)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }
      
      public Builder mergeFrom(org.yamcs.protobuf.GapRequest.CcsdsGapRequest other) {
        if (other == org.yamcs.protobuf.GapRequest.CcsdsGapRequest.getDefaultInstance()) return this;
        if (!other.gaps_.isEmpty()) {
          if (result.gaps_.isEmpty()) {
            result.gaps_ = new java.util.ArrayList<org.yamcs.protobuf.GapRequest.CcsdsGap>();
          }
          result.gaps_.addAll(other.gaps_);
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder(
            this.getUnknownFields());
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              this.setUnknownFields(unknownFields.build());
              return this;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                this.setUnknownFields(unknownFields.build());
                return this;
              }
              break;
            }
            case 10: {
              org.yamcs.protobuf.GapRequest.CcsdsGap.Builder subBuilder = org.yamcs.protobuf.GapRequest.CcsdsGap.newBuilder();
              input.readMessage(subBuilder, extensionRegistry);
              addGaps(subBuilder.buildPartial());
              break;
            }
          }
        }
      }
      
      
      // repeated .gaprequest.CcsdsGap gaps = 1;
      public java.util.List<org.yamcs.protobuf.GapRequest.CcsdsGap> getGapsList() {
        return java.util.Collections.unmodifiableList(result.gaps_);
      }
      public int getGapsCount() {
        return result.getGapsCount();
      }
      public org.yamcs.protobuf.GapRequest.CcsdsGap getGaps(int index) {
        return result.getGaps(index);
      }
      public Builder setGaps(int index, org.yamcs.protobuf.GapRequest.CcsdsGap value) {
        if (value == null) {
          throw new NullPointerException();
        }
        result.gaps_.set(index, value);
        return this;
      }
      public Builder setGaps(int index, org.yamcs.protobuf.GapRequest.CcsdsGap.Builder builderForValue) {
        result.gaps_.set(index, builderForValue.build());
        return this;
      }
      public Builder addGaps(org.yamcs.protobuf.GapRequest.CcsdsGap value) {
        if (value == null) {
          throw new NullPointerException();
        }
        if (result.gaps_.isEmpty()) {
          result.gaps_ = new java.util.ArrayList<org.yamcs.protobuf.GapRequest.CcsdsGap>();
        }
        result.gaps_.add(value);
        return this;
      }
      public Builder addGaps(org.yamcs.protobuf.GapRequest.CcsdsGap.Builder builderForValue) {
        if (result.gaps_.isEmpty()) {
          result.gaps_ = new java.util.ArrayList<org.yamcs.protobuf.GapRequest.CcsdsGap>();
        }
        result.gaps_.add(builderForValue.build());
        return this;
      }
      public Builder addAllGaps(
          java.lang.Iterable<? extends org.yamcs.protobuf.GapRequest.CcsdsGap> values) {
        if (result.gaps_.isEmpty()) {
          result.gaps_ = new java.util.ArrayList<org.yamcs.protobuf.GapRequest.CcsdsGap>();
        }
        super.addAll(values, result.gaps_);
        return this;
      }
      public Builder clearGaps() {
        result.gaps_ = java.util.Collections.emptyList();
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:gaprequest.CcsdsGapRequest)
    }
    
    static {
      defaultInstance = new CcsdsGapRequest(true);
      org.yamcs.protobuf.GapRequest.internalForceInit();
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:gaprequest.CcsdsGapRequest)
  }
  
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_gaprequest_CcsdsGap_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_gaprequest_CcsdsGap_fieldAccessorTable;
  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_gaprequest_CcsdsGapRequest_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_gaprequest_CcsdsGapRequest_fieldAccessorTable;
  
  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\021gap-request.proto\022\ngaprequest\"=\n\010Ccsds" +
      "Gap\022\014\n\004apid\030\001 \001(\005\022\021\n\tstartTime\030\002 \002(\003\022\020\n\010" +
      "stopTime\030\003 \002(\003\"5\n\017CcsdsGapRequest\022\"\n\004gap" +
      "s\030\001 \003(\0132\024.gaprequest.CcsdsGapB\024\n\022org.yam" +
      "cs.protobuf"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_gaprequest_CcsdsGap_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_gaprequest_CcsdsGap_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_gaprequest_CcsdsGap_descriptor,
              new java.lang.String[] { "Apid", "StartTime", "StopTime", },
              org.yamcs.protobuf.GapRequest.CcsdsGap.class,
              org.yamcs.protobuf.GapRequest.CcsdsGap.Builder.class);
          internal_static_gaprequest_CcsdsGapRequest_descriptor =
            getDescriptor().getMessageTypes().get(1);
          internal_static_gaprequest_CcsdsGapRequest_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_gaprequest_CcsdsGapRequest_descriptor,
              new java.lang.String[] { "Gaps", },
              org.yamcs.protobuf.GapRequest.CcsdsGapRequest.class,
              org.yamcs.protobuf.GapRequest.CcsdsGapRequest.Builder.class);
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }
  
  public static void internalForceInit() {}
  
  // @@protoc_insertion_point(outer_class_scope)
}

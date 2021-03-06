// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: resources/foo.proto

package j.se.io.aio.bean;

public final class Foo {
  private Foo() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }
  public interface PersonOrBuilder
      extends com.google.protobuf.MessageLiteOrBuilder {
    
    // required int32 id = 1;
    boolean hasId();
    int getId();
    
    // optional string name = 2;
    boolean hasName();
    String getName();
    
    // optional string motto = 3 [default = "When the cat is away, the mouse is alone!"];
    boolean hasMotto();
    String getMotto();
    
    // optional .foo.Person.Gender gender = 4;
    boolean hasGender();
    j.se.io.aio.bean.Foo.Person.Gender getGender();
  }
  public static final class Person extends
      com.google.protobuf.GeneratedMessageLite
      implements PersonOrBuilder {
    // Use Person.newBuilder() to construct.
    private Person(Builder builder) {
      super(builder);
    }
    private Person(boolean noInit) {}
    
    private static final Person defaultInstance;
    public static Person getDefaultInstance() {
      return defaultInstance;
    }
    
    public Person getDefaultInstanceForType() {
      return defaultInstance;
    }
    
    public enum Gender
        implements com.google.protobuf.Internal.EnumLite {
      MALE(0, 1),
      FEMALE(1, 2),
      ;
      
      public static final int MALE_VALUE = 1;
      public static final int FEMALE_VALUE = 2;
      
      
      public final int getNumber() { return value; }
      
      public static Gender valueOf(int value) {
        switch (value) {
          case 1: return MALE;
          case 2: return FEMALE;
          default: return null;
        }
      }
      
      public static com.google.protobuf.Internal.EnumLiteMap<Gender>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static com.google.protobuf.Internal.EnumLiteMap<Gender>
          internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<Gender>() {
              public Gender findValueByNumber(int number) {
                return Gender.valueOf(number);
              }
            };
      
      private final int value;
      
      private Gender(int index, int value) {
        this.value = value;
      }
      
      // @@protoc_insertion_point(enum_scope:foo.Person.Gender)
    }
    
    private int bitField0_;
    // required int32 id = 1;
    public static final int ID_FIELD_NUMBER = 1;
    private int id_;
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    public int getId() {
      return id_;
    }
    
    // optional string name = 2;
    public static final int NAME_FIELD_NUMBER = 2;
    private java.lang.Object name_;
    public boolean hasName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    public String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          name_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // optional string motto = 3 [default = "When the cat is away, the mouse is alone!"];
    public static final int MOTTO_FIELD_NUMBER = 3;
    private java.lang.Object motto_;
    public boolean hasMotto() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    public String getMotto() {
      java.lang.Object ref = motto_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        if (com.google.protobuf.Internal.isValidUtf8(bs)) {
          motto_ = s;
        }
        return s;
      }
    }
    private com.google.protobuf.ByteString getMottoBytes() {
      java.lang.Object ref = motto_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8((String) ref);
        motto_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    
    // optional .foo.Person.Gender gender = 4;
    public static final int GENDER_FIELD_NUMBER = 4;
    private j.se.io.aio.bean.Foo.Person.Gender gender_;
    public boolean hasGender() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    public j.se.io.aio.bean.Foo.Person.Gender getGender() {
      return gender_;
    }
    
    private void initFields() {
      id_ = 0;
      name_ = "";
      motto_ = "When the cat is away, the mouse is alone!";
      gender_ = j.se.io.aio.bean.Foo.Person.Gender.MALE;
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;
      
      if (!hasId()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, id_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeBytes(2, getNameBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        output.writeBytes(3, getMottoBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        output.writeEnum(4, gender_.getNumber());
      }
    }
    
    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;
    
      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, id_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, getNameBytes());
      }
      if (((bitField0_ & 0x00000004) == 0x00000004)) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(3, getMottoBytes());
      }
      if (((bitField0_ & 0x00000008) == 0x00000008)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(4, gender_.getNumber());
      }
      memoizedSerializedSize = size;
      return size;
    }
    
    private static final long serialVersionUID = 0L;
    @java.lang.Override
    protected java.lang.Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }
    
    public static j.se.io.aio.bean.Foo.Person parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static j.se.io.aio.bean.Foo.Person parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static j.se.io.aio.bean.Foo.Person parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data).buildParsed();
    }
    public static j.se.io.aio.bean.Foo.Person parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return newBuilder().mergeFrom(data, extensionRegistry)
               .buildParsed();
    }
    public static j.se.io.aio.bean.Foo.Person parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static j.se.io.aio.bean.Foo.Person parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    public static j.se.io.aio.bean.Foo.Person parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      Builder builder = newBuilder();
      if (builder.mergeDelimitedFrom(input)) {
        return builder.buildParsed();
      } else {
        return null;
      }
    }
    public static j.se.io.aio.bean.Foo.Person parseDelimitedFrom(
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
    public static j.se.io.aio.bean.Foo.Person parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input).buildParsed();
    }
    public static j.se.io.aio.bean.Foo.Person parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return newBuilder().mergeFrom(input, extensionRegistry)
               .buildParsed();
    }
    
    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(j.se.io.aio.bean.Foo.Person prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }
    
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageLite.Builder<
          j.se.io.aio.bean.Foo.Person, Builder>
        implements j.se.io.aio.bean.Foo.PersonOrBuilder {
      // Construct using lion.bean.Foo.Person.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }
      
      private void maybeForceBuilderInitialization() {
      }
      private static Builder create() {
        return new Builder();
      }
      
      public Builder clear() {
        super.clear();
        id_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        name_ = "";
        bitField0_ = (bitField0_ & ~0x00000002);
        motto_ = "When the cat is away, the mouse is alone!";
        bitField0_ = (bitField0_ & ~0x00000004);
        gender_ = j.se.io.aio.bean.Foo.Person.Gender.MALE;
        bitField0_ = (bitField0_ & ~0x00000008);
        return this;
      }
      
      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }
      
      public j.se.io.aio.bean.Foo.Person getDefaultInstanceForType() {
        return j.se.io.aio.bean.Foo.Person.getDefaultInstance();
      }
      
      public j.se.io.aio.bean.Foo.Person build() {
        j.se.io.aio.bean.Foo.Person result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }
      
      private j.se.io.aio.bean.Foo.Person buildParsed()
          throws com.google.protobuf.InvalidProtocolBufferException {
        j.se.io.aio.bean.Foo.Person result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(
            result).asInvalidProtocolBufferException();
        }
        return result;
      }
      
      public j.se.io.aio.bean.Foo.Person buildPartial() {
        j.se.io.aio.bean.Foo.Person result = new j.se.io.aio.bean.Foo.Person(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.id_ = id_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.name_ = name_;
        if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
          to_bitField0_ |= 0x00000004;
        }
        result.motto_ = motto_;
        if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
          to_bitField0_ |= 0x00000008;
        }
        result.gender_ = gender_;
        result.bitField0_ = to_bitField0_;
        return result;
      }
      
      public Builder mergeFrom(j.se.io.aio.bean.Foo.Person other) {
        if (other == j.se.io.aio.bean.Foo.Person.getDefaultInstance()) return this;
        if (other.hasId()) {
          setId(other.getId());
        }
        if (other.hasName()) {
          setName(other.getName());
        }
        if (other.hasMotto()) {
          setMotto(other.getMotto());
        }
        if (other.hasGender()) {
          setGender(other.getGender());
        }
        return this;
      }
      
      public final boolean isInitialized() {
        if (!hasId()) {
          
          return false;
        }
        return true;
      }
      
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        while (true) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              
              return this;
            default: {
              if (!parseUnknownField(input, extensionRegistry, tag)) {
                
                return this;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              id_ = input.readInt32();
              break;
            }
            case 18: {
              bitField0_ |= 0x00000002;
              name_ = input.readBytes();
              break;
            }
            case 26: {
              bitField0_ |= 0x00000004;
              motto_ = input.readBytes();
              break;
            }
            case 32: {
              int rawValue = input.readEnum();
              j.se.io.aio.bean.Foo.Person.Gender value = j.se.io.aio.bean.Foo.Person.Gender.valueOf(rawValue);
              if (value != null) {
                bitField0_ |= 0x00000008;
                gender_ = value;
              }
              break;
            }
          }
        }
      }
      
      private int bitField0_;
      
      // required int32 id = 1;
      private int id_ ;
      public boolean hasId() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      public int getId() {
        return id_;
      }
      public Builder setId(int value) {
        bitField0_ |= 0x00000001;
        id_ = value;
        
        return this;
      }
      public Builder clearId() {
        bitField0_ = (bitField0_ & ~0x00000001);
        id_ = 0;
        
        return this;
      }
      
      // optional string name = 2;
      private java.lang.Object name_ = "";
      public boolean hasName() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      public String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          name_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setName(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
        name_ = value;
        
        return this;
      }
      public Builder clearName() {
        bitField0_ = (bitField0_ & ~0x00000002);
        name_ = getDefaultInstance().getName();
        
        return this;
      }
      void setName(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000002;
        name_ = value;
        
      }
      
      // optional string motto = 3 [default = "When the cat is away, the mouse is alone!"];
      private java.lang.Object motto_ = "When the cat is away, the mouse is alone!";
      public boolean hasMotto() {
        return ((bitField0_ & 0x00000004) == 0x00000004);
      }
      public String getMotto() {
        java.lang.Object ref = motto_;
        if (!(ref instanceof String)) {
          String s = ((com.google.protobuf.ByteString) ref).toStringUtf8();
          motto_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      public Builder setMotto(String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000004;
        motto_ = value;
        
        return this;
      }
      public Builder clearMotto() {
        bitField0_ = (bitField0_ & ~0x00000004);
        motto_ = getDefaultInstance().getMotto();
        
        return this;
      }
      void setMotto(com.google.protobuf.ByteString value) {
        bitField0_ |= 0x00000004;
        motto_ = value;
        
      }
      
      // optional .foo.Person.Gender gender = 4;
      private j.se.io.aio.bean.Foo.Person.Gender gender_ = j.se.io.aio.bean.Foo.Person.Gender.MALE;
      public boolean hasGender() {
        return ((bitField0_ & 0x00000008) == 0x00000008);
      }
      public j.se.io.aio.bean.Foo.Person.Gender getGender() {
        return gender_;
      }
      public Builder setGender(j.se.io.aio.bean.Foo.Person.Gender value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000008;
        gender_ = value;
        
        return this;
      }
      public Builder clearGender() {
        bitField0_ = (bitField0_ & ~0x00000008);
        gender_ = j.se.io.aio.bean.Foo.Person.Gender.MALE;
        
        return this;
      }
      
      // @@protoc_insertion_point(builder_scope:foo.Person)
    }
    
    static {
      defaultInstance = new Person(true);
      defaultInstance.initFields();
    }
    
    // @@protoc_insertion_point(class_scope:foo.Person)
  }
  
  
  static {
  }
  
  // @@protoc_insertion_point(outer_class_scope)
}

package com.junmeng.common;

import org.springframework.util.StringUtils;

/**
 * @author james
 * @date 2020/3/20
 */
public enum SerializeType {

    DefaultJavaSerializer("DefaultJavaSerializer"),

    HessianSerializer("HessianSerializer"),

    JSONSerializer("JSONSerializer"),

    ProtoStuffSerializer("ProtoStuffSerializer"),

    XmlSerializer("XmlSerializer"),

    MarshallingSerializer("MarshallingSerializer"),

    AvroSerializer("AvroSerializer"),

    ProtocolBufferSerializer("ProtocolBufferSerializer"),

    ThriftSerializer("ThriftSerializer");

    private String serializeType;

    SerializeType(String serializeType) {
        this.serializeType = serializeType;
    }

    public static SerializeType queryByType(String serializeType) {
        if (StringUtils.isEmpty(serializeType)) {
            return null;
        }
        for (SerializeType serialize : SerializeType.values()) {
            if (org.apache.commons.lang.StringUtils.equalsIgnoreCase(serializeType, serialize.getSerializeType())) {
                return serialize;
            }
        }
        return null;
    }

    public String getSerializeType() {
        return serializeType;
    }
}

package com.github.jakobwilms.picoder;

public enum CodingType {
    ENCODE("encode"),
    DECODE("decode"),
    ;

    private final String name;

    CodingType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CodingType getByName(String name) {
        switch (name) {
            case "decode":
                return DECODE;
            case "encode":
            default:
                return ENCODE;
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}

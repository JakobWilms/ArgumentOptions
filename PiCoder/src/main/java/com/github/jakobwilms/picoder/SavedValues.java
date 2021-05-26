package com.github.jakobwilms.picoder;

import java.util.ArrayList;
import java.util.List;

public class SavedValues {

    private byte[] b1;

    public SavedValues() {
        this.b1 = new byte[5];
    }

    public byte[] getB1() {
        return b1;
    }

    public List<Byte> toList() {
        List<Byte> returnValue = new ArrayList<>();
        for (byte b : getB1()) {
            returnValue.add(b);
        }
        return returnValue;
    }

    public void removeSavedValues(List<Byte> bytes) {
        for (int i = 0; i < 5; i++) {
            this.b1[5 - 1 - i] = bytes.get(bytes.size() - 1);
            bytes.remove(bytes.size() - 1);
        }
    }

    public void setB1(byte[] b1) {
        this.b1 = b1;
    }
}

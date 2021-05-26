package com.github.jakobwilms.picoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Decoder {

    private final SavedValues savedValues;
    private List<byte[]> bytesToDecode;
    private String end;

    public Decoder() {
        this.savedValues = new SavedValues();
    }

    public void decode() {
        final List<String> decodedStrings = new ArrayList<>();
        final List<byte[]> decodedBytes = new ArrayList<>();
        byte[] b1 = getSavedValues().getB1();
        for (int i = 0; i < bytesToDecode.size(); i++) {
            byte[] bytes = this.bytesToDecode.get(i);
            for (int j = 0; j < bytes.length; j++) {
                byte b = bytes[i];
                byte cache = Integer.valueOf(b << 2).byteValue();
                switch (j) {
                    case 0:
                        cache ^= b1[0];
                        break;
                    case 1:
                        cache ^= b1[1];
                        break;
                    case 2:
                        cache ^= b1[2];
                        break;
                    case 3:
                        cache ^= b1[3];
                        break;
                    case 4:
                        cache ^= b1[4];
                        break;
                    default:
                        cache ^= ((b1[0] & b1[3]) >> 1);
                        break;
                }
                bytes[j] = cache;
            }
            decodedBytes.add(bytes);
        }
        for (byte[] bytes : decodedBytes) {
            decodedStrings.add(new String(bytes));
        }
        StringBuilder builder = new StringBuilder();
        for (String string : decodedStrings) {
            builder.append(string);
        }
        this.end = builder.toString();
    }

    public void load(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file); ObjectInputStream stream = new ObjectInputStream(fileInputStream)) {
            byte[] values = stream.readAllBytes();
            byte[] dot = "↑".getBytes();
            int dotLength = dot.length;
            final List<Byte> encodedByteList = new ArrayList<>();
            final List<byte[]> byteList = new ArrayList<>();
            for (byte value : values) {
                encodedByteList.add(value);
            }
            getSavedValues().removeSavedValues(encodedByteList);
            int lastDot = 0;
            for (int i = 0; i < encodedByteList.size(); i++) {
                byte value = encodedByteList.get(i);
                boolean isDot = true;
                if (value == dot[0]) {
                    for (int z = 0; z < dotLength; z++) {
                        byte b = encodedByteList.get(i + z);
                        if (!(b == dot[z])) {
                            isDot = false;
                            break;
                        }
                    }
                } else {
                    isDot = false;
                }
                if (isDot) {
                    byte[] bytes = new byte[i - lastDot];
                    for (int z = 0; z < bytes.length; z++) {
                        bytes[z] = encodedByteList.get(lastDot + z);
                    }
                    byteList.add(bytes);
                    lastDot = i - 1 + dotLength;
                }
            }
            this.bytesToDecode = byteList;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(File file) {

        try (FileWriter writer = new FileWriter(file.getAbsolutePath() + ".txt")) {
            writer.write(this.end);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SavedValues getSavedValues() {
        return savedValues;
    }
}

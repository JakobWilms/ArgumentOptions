package com.github.jakobwilms.picoder;

import org.jetbrains.annotations.Range;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Encoder {

    private final SavedValues savedValues;
    private final String text;
    private final long initialValue;
    private final int compression;
    private final int cycles;
    private List<byte[]> encodedBytes;

    public Encoder(String text, long initialValue, int compression, int cycles) {
        this.text = text;
        this.initialValue = initialValue;
        this.compression = compression;
        this.cycles = cycles;
        this.savedValues = new SavedValues();
        this.encodedBytes = new ArrayList<>();
    }

    public void encode() {
        final String[] split = text.split("");
        final String[] split16 = partSplit(16, split);
        final List<byte[]> split16Bytes = new ArrayList<>();
        final List<int[]> split16Ints = new ArrayList<>();
        for (String s : split16) {
            byte[] bytes = s.getBytes();
            int[] ints = new int[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                ints[i] = bytes[i];
            }
            split16Ints.add(ints);
        }
        for (String s : split16) {
            split16Bytes.add(s.getBytes());
        }
        System.out.println("Given Integers:");
        for (int[] ints : split16Ints) {
            for (int i : ints) {
                System.out.println(i);
            }
        }
        try {
            SecureRandom secureRandom = SecureRandom.getInstanceStrong();
            byte[] b1 = new byte[5];
            int[] i1 = fill(5);
            secureRandom.nextBytes(b1);
            getSavedValues().setB1(b1); // TODO
            for (int i = 0; i < split16Ints.size(); i++) {
                byte[] bytes = split16Bytes.get(i);
                int[] ints = split16Ints.get(i);
                for (int j = 0; j < ints.length; j++) {
                    int integer = ints[i];
                    byte b = bytes[j];
                    int intCache = integer << 2;
                    byte cache = Integer.valueOf(b >> 2).byteValue();
                    switch (j) {
                        case 0:
                            intCache ^= i1[0];
                            break;
                        case 1:
                            intCache ^= i1[1];
                            break;
                        case 2:
                            intCache ^= i1[2];
                            break;
                        case 3:
                            intCache ^= i1[3];
                            break;
                        case 4:
                            intCache ^= i1[4];
                            break;
                        default:
                            intCache ^= ((i1[0] & i1[3]) << 1);
                            break;
                    }
                    ints[j] = intCache;
                    bytes[j] = cache;
                }
                split16Bytes.set(i, bytes);
            }
            System.out.println("\n Encoded Bytes:");
            for (byte[] bytes : split16Bytes) {
                for (byte b : bytes) {
                    System.out.println(b);
                }
            }
            this.encodedBytes = split16Bytes;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public int[] fill(@Range(from = 0, to = Integer.MAX_VALUE) int size) {
        int[] ints = new int[size];
        try {
            SecureRandom random = SecureRandom.getInstanceStrong();
            for (int i = 0; i < size; i++) {
                ints[i] = random.nextInt();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ints[0] != 0 ? ints : fill(size);
    }

    public void write(File file) {
        final List<Byte> encodedByteList = new ArrayList<>();
        byte[] dot = "↑".getBytes();
        for (byte[] bytes : this.encodedBytes) {
            for (byte b : bytes) {
                encodedByteList.add(b);
            }
            for (byte b : dot) {
                encodedByteList.add(b);
            }
        }
        encodedByteList.addAll(getSavedValues().toList());
        byte[] bytes = new byte[encodedByteList.size()];
        for (int i = 0; i < encodedByteList.size(); i++) {
            byte b = encodedByteList.get(i);
            bytes[i] = b;
        }
        try (FileOutputStream stream = new FileOutputStream(file.getAbsolutePath() + ".picoder"); ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream)) {
            objectOutputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] partSplit(int size, String[] value) {
        String[] returnValue = new String[Math.round(value.length >> 4)];
        for (int i = 0; i < returnValue.length; i++) {
            StringBuilder cache = new StringBuilder();
            for (int j = 0; j < size; j++) {
                cache.append(value[i * size + j]);
            }
            returnValue[i] = cache.toString();
        }
        return returnValue;
    }

    public byte[] longToBytes(long l) {
        byte[] result = new byte[Long.BYTES];
        for (int i = Long.BYTES - 1; i >= 0; i--) {
            result[i] = (byte) (l & 0xFF);
            l >>= Byte.SIZE;
        }
        return result;
    }

    public long bytesToLong(final byte[] b) {
        long result = 0;
        for (int i = 0; i < Long.BYTES; i++) {
            result <<= Byte.SIZE;
            result |= (b[i] & 0xFF);
        }
        return result;
    }

    public String getText() {
        return text;
    }

    public long getInitialValue() {
        return initialValue;
    }

    public int getCompression() {
        return compression;
    }

    public int getCycles() {
        return cycles;
    }

    public SavedValues getSavedValues() {
        return savedValues;
    }
}

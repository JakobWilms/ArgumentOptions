package com.github.jakobwilms.picoder;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class CodingStarter {

    final CodingType codingType;
    final Random random = new Random();
    long initialValue = random.nextLong();
    int compression = 10;
    int cycles = 10;
    String fileName = "";

    public CodingStarter(String[] args) {
        if (args.length > 0) {
            codingType = CodingType.getByName(args[0]);
        } else {
            codingType = CodingType.ENCODE;
        }
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            switch (arg) {
                case "-file":
                    fileName = args[i + 1];
                    break;
                case "-initialValue":
                    try {
                        initialValue = Long.parseLong(args[i + 1]);
                    } catch (NumberFormatException e) {
                        System.err.println("Couldn't parse initialValue (" + args[i + 1] + ")!");
                        System.exit(0);
                    }
                    break;
                case "-compression":
                    try {
                        compression = Integer.parseInt(args[i + 1]);
                    } catch (NumberFormatException e) {
                        System.err.println("Couldn't parse compression (" + args[i + 1] + ")!");
                        System.exit(0);
                    }
                    break;
                case "-cycles":
                    try {
                        cycles = Integer.parseInt(args[i + 1]);
                    } catch (NumberFormatException e) {
                        System.err.println("Couldn't parse cycles (" + args[i + 1] + ")!");
                        System.exit(0);
                    }
                    break;
            }
        }
        if (!fileName.equals("")) {
            File file = new File(fileName);
            try {
                String text = readFromFile(file);
                if (codingType == CodingType.ENCODE) {
                    Encoder encoder = new Encoder(text, initialValue, compression, cycles);
                    encoder.encode();
                    encoder.write(file);
                } else if (codingType == CodingType.DECODE) {
                    Decoder decoder = new Decoder();
                    decoder.load(file);
                    decoder.decode();
                    decoder.write(file);
                }
            } catch (IOException e) {
                System.err.println("Couldn't read file (" + file.getAbsolutePath() + ")!");
            }
        } else {
            System.err.println("No -fileName found!");
        }
    }

    @NotNull
    private String readFromFile(File file) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            String line;
            while (scanner.hasNextLine() && (line = scanner.nextLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

}

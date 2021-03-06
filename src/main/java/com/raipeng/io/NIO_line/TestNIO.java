package com.raipeng.io.nio_line;

import java.io.IOException;

public class TestNIO {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        RuntimeMemory.getMemory();
        String infile = "D:\\abc.txt";
        String outfile = "D:\\copy.txt";

        NioReader reader=new NioReader(infile);
        NioWriter writer=new NioWriter(outfile);
        String line;

        while ((line = reader.getNextLine()) != null) {
            try {
                if ((line == null) || line.trim().equals("")) {
                    continue;
                }
                writer.putln(line);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        reader.close();
        writer.close();
        long end = System.currentTimeMillis();
        System.out.println((end - start) + "ms");
        RuntimeMemory.getMemory();
    }
}

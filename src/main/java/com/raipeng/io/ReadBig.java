package com.raipeng.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

//使用NIO读取超大文件然后输出，但没有按行读取的功能


public class ReadBig {
    @Test
    public void readBig() throws IOException {
        final int BUFFER_SIZE = 3 * 1024 * 1024;//设置缓冲区大小
        File file = new File("d:\\abc.txt");

        MappedByteBuffer inputBuffer = new RandomAccessFile(file, "r").getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());

        byte[] dst = new byte[BUFFER_SIZE];// 每次读出3M的内容

        for (int offset = 0; offset < inputBuffer.capacity(); offset += BUFFER_SIZE) {
            if (inputBuffer.capacity() - offset >= BUFFER_SIZE) {
                for (int i = 0; i < BUFFER_SIZE; i++)
                    dst[i] = inputBuffer.get(offset + i);
            } else {
                for (int i = 0; i < inputBuffer.capacity() - offset; i++){
                    dst[i] = inputBuffer.get(offset + i);
                }
            }

            int length = (inputBuffer.capacity() % BUFFER_SIZE == 0) ? BUFFER_SIZE
                    : inputBuffer.capacity() % BUFFER_SIZE;

            String str = new String(dst, 0, length);
            System.out.println(str);
        }

    }
}

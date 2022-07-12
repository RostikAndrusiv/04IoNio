package org.rostik.andrusiv.FastFileMover;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Slf4j
public class FastFileMover {

    public static void inputStreamMover(String in, String out) {
        try (InputStream is = new FileInputStream(in);
             OutputStream os = new FileOutputStream(out)) {
            int r = is.read();
            while (r != -1) {
                os.write(r);
                r = is.read();
            }
        } catch (IOException e) {
            log.info(String.format("Exception : %s", e.getMessage()));
        }
    }

    public static void inputStreamBufferedMover(String in, String out) {
        try (InputStream is = new FileInputStream(in);
             OutputStream os = new FileOutputStream(out)) {
            byte[] buffer = new byte[102400];
            int r = is.read(buffer);
            while (r != -1) {
                os.write(buffer, 0, r);
                r = is.read();
            }
        } catch (IOException e) {
            log.info(String.format("Exception : %s", e.getMessage()));
        }
    }

    public static void fileChannelMover(String in, String out) {
        try (FileInputStream fis = new FileInputStream(in);
             FileOutputStream fos = new FileOutputStream(out)) {
            FileChannel inChannel = fis.getChannel();
            FileChannel outChannel = fos.getChannel();
            ByteBuffer inBuffer = ByteBuffer.allocate(4096);
            ByteBuffer outBuffer = ByteBuffer.allocate(4096);
            int r = inChannel.read(inBuffer);
            while (r != -1) {
                inBuffer.flip();
                while (inBuffer.hasRemaining()) {
                    byte get = inBuffer.get();
                    outBuffer.put(get);
                }
                outBuffer.flip();
                outChannel.write(outBuffer);
                inBuffer.clear();
                outBuffer.clear();
                r = inChannel.read(inBuffer);
            }
        } catch (IOException e) {
            log.info(String.format("Exception : %s", e.getMessage()));
        }
    }


    public static void Nio2Mover(String in, String out) {
        try {
            Files.copy(new File(in).toPath(), new File(out).toPath(),
                    REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);
        } catch (IOException e) {
            log.info(String.format("Exception : %s", e.getMessage()));
        }
    }
}

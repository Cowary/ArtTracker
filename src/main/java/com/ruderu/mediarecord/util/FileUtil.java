package com.ruderu.mediarecord.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class FileUtil {

    public static void downloadFile(String webString, String fileName) {
        System.out.println("Downloading File From: " + webString);

        try {
            URL url = new URL(webString);
            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream(fileName + ".jpeg");
            byte[] buffer = new byte[2048];

            int length = 0;
            while ((length = inputStream.read(buffer)) != -1) {
                System.out.println("Buffer Read of length: " + length);
                outputStream.write(buffer, 0, length);
            }

            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

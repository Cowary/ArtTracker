package com.ruderu.arttracker.util;

import org.springframework.util.Assert;

import java.io.*;
import java.net.URL;

public class FileUtil {

    public static final String path = "C:/Users/alexa/IdeaProjects/ArtTracker/src/main/webapp/resources/images/";

    public static File downloadFile(String webString, String fileName) {
        System.out.println("Downloading File From: " + webString);
        File file = new File(FileUtil.path + "image" + ".jpeg");
        if(file.exists()) file.delete();

        try {
            URL url = new URL(webString);
            InputStream inputStream = url.openStream();
            OutputStream outputStream = new FileOutputStream(path + fileName + ".jpeg");
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
        file = new File(FileUtil.path + "image" + ".jpeg");
        Assert.isTrue(file.exists(), "File not exist");
        return file;
    }
}

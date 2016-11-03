package com.saferich.core.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;

/**
 * <B>Description:</B> <br>
 * <B>Create on:</B> 2015/12/2<br>
 *
 * @author ke.tu
 * @version 1.0
 */

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class PicUtil {

    // TODO 部署时需要修改
    // public static final String uploadPath="F:\\YunSVN\\projects\\trunk\\wxlc3\\target\\wxlc\\upload\\";

    public static void createEwm(String path, String content) throws IOException {
        ByteArrayOutputStream out = null;
        FileOutputStream fout = null;
        try {
            out = QRCode.from(content).to(ImageType.PNG).stream();
            fout = new FileOutputStream(new File(path));
            fout.write(out.toByteArray());

            fout.flush();
            fout.close();
        } finally {
            IOUtils.closeQuietly(fout);
            IOUtils.closeQuietly(out);
        }

    }

    public static void main(String[] args) throws IOException {
        String path = "D:\\ewm.jpg";
        createEwm(path, "http://www.sina.com.cn");
    }

}
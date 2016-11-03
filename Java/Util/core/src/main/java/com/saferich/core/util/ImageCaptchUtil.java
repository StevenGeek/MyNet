package com.saferich.core.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

/**
 * @author Jason.Zhang
 */
public class ImageCaptchUtil {

    public static byte[] generateCaptchaImage(String text) throws IOException {
        int fontWidth = 13;
        int width = text.length() * fontWidth;
        int height = 22;
        int fontSize = 17;
        // 创建内存图像
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.createGraphics();

        // 设定图像背景色(因为是做背景，所以偏淡)
        g.setColor(getRandColor(180, 250));
        g.fillRect(0, 0, width, height);
        // 设置字体
        g.setFont(new Font("Times New Roman", Font.BOLD, fontSize));
        // 设置默认生成4个验证码
        int length = text.length();
        java.util.Random rand = new Random(); // 设置随机种子

        for (int i = 0; i < length; i++) {
            String tmpStr = "" + text.charAt(i);

            // 生成随机颜色(因为是做前景，所以偏深)
            g.setColor(getRandColor(10, 150));

            // 将此字画到图片上
            g.drawString(tmpStr, fontWidth * i + 2, 14 + rand.nextInt(6));

        }

        // 图象生效
        g.dispose();

        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        ImageOutputStream imOut = ImageIO.createImageOutputStream(bs);
        ImageIO.write(image, "jpg", imOut); // scaledImage1为BufferedImage，jpg为图像的类型

        return bs.toByteArray();
    }

    // 给定范围获得一个随机颜色
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}

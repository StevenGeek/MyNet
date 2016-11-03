package com.saferich.core.util;

import java.math.BigDecimal;

/**
 * @author Jason.Zhang
 */
public class CalculateUtil {

    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    public static double add(double v1, double v2, double v3) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        BigDecimal b3 = new BigDecimal(Double.toString(v3));
        return b1.add(b2).add(b3).doubleValue();
    }

    public static double sub(double v1, double v2) {// 进行减法运算
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).doubleValue();
    }

    public static double multiply(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    public static double div(double v1, double v2) {// 进行除法运算
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static double complexOne(double v1, double v2, double v3, double v4, double v5) {// 平台应收总额计算
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        BigDecimal b3 = new BigDecimal(v3);
        BigDecimal b4 = new BigDecimal(v4);
        BigDecimal b5 = new BigDecimal(v5);
        return b1.add(b2).subtract(b3).add(b4).add(b5).doubleValue();
    }

    public static double complexTwo(double v1, double v2, double v3, double v4, double v5, double v6) {// 平台应付总额计算
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        BigDecimal b3 = new BigDecimal(v3);
        BigDecimal b4 = new BigDecimal(v4);
        BigDecimal b5 = new BigDecimal(v5);
        BigDecimal b6 = new BigDecimal(v6);
        return b1.add(b2).subtract(b3).add(b4).subtract(b5).subtract(b6).doubleValue();
    }

    /**
     * 提供精确的小数位四舍五入处理。
     * 
     * @param v 需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}

package com.ruoyi.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @Author: Administrator
 * @Date: 2020\3\20 0020
 */
public class NumberFormatUtil {
    static DecimalFormat df1 = new DecimalFormat("#0.0");
    static DecimalFormat df2 = new DecimalFormat("#0.00");
    static DecimalFormat df3 = new DecimalFormat("#0.000");
    static DecimalFormat df4 = new DecimalFormat("#0.0000");

    public static String format1(double n) {
        String f = df1.format(n);
        return f;
    }

    public static String format2(double n) {
        String f = df2.format(n);
        return f;
    }

    public static String format3(double n) {
        String f = df3.format(n);
        return f;
    }

    public static String format4(double n) {
        String f = df4.format(n);
        return f;
    }

    /**
     * 保留小数位数
     *
     * @param n
     * @param digits
     * @return
     */
    public static String format(double n, int digits) {
        String format = "#0.";
        for (int i = 0; i < digits; i++) {
            format = format + "0";
        }
        if (digits == 0) {
            format = "#0";
        }
        DecimalFormat df = new DecimalFormat(format);
        df.setRoundingMode(RoundingMode.HALF_UP);
        if(n==0d){
            n = 0.00;
        }
        String f = df.format(n);
        return f;
    }

    /**
     * 格式化浮点数：总价精度和面积补差精度
     * @param n 浮点数
     * @param value 类别值：1：四舍五入取整  2：进位取整 3：四舍五入保留2位小数
     * @return
     */
    public static String format(double n, String value){
        String format = "#0.";
        String f = "0.00";
        if("1".equals(value)){//四舍五入取整
            format = "#0";
            DecimalFormat df = new DecimalFormat(format);
            df.setRoundingMode(RoundingMode.HALF_UP);
            f = df.format(n);
        }else if("2".equals(value)){//进位取整；向上取整
            n = Math.ceil(n);
            format = "#0";
            DecimalFormat df = new DecimalFormat(format);
            df.setRoundingMode(RoundingMode.HALF_UP);
            f = df.format(n);
        }else {//四舍五入保留2位小数
            format = "#0.00";
            DecimalFormat df = new DecimalFormat(format);
            df.setRoundingMode(RoundingMode.HALF_UP);
            f = df.format(n);
        }
        return f;
    }

    /**
     * 大数相乘
     *
     * @param str1
     * @param str2
     * @param retain 保留几位数
     * @return
     */
    public static BigDecimal Multiplication(String str1, String str2, int retain) {
        BigDecimal big1 = new BigDecimal(str1);
        BigDecimal big2 = new BigDecimal(str2);
        BigDecimal bigInterest = big1.multiply(big2);
        BigDecimal setScale = bigInterest.setScale(retain, BigDecimal.ROUND_HALF_DOWN);
        System.out.println("  结果  " + setScale);
        return setScale;

        /*BigDecimal bigLoanAmount = new BigDecimal("具体数值");   //创建BigDecimal对象
        BigDecimal bigInterestRate = new BigDecimal("具体数值");
        BigDecimal bigInterest = bigLoanAmount.multiply(bigInterestRate); //BigDecimal运算
        NumberFormat currency = NumberFormat.getCurrencyInstance();    //建立货币格式化引用
        NumberFormat percent = NumberFormat.getPercentInstance();     //建立百分比格式化用
        percent.setMaximumFractionDigits(3);               //百分比小数点最多3位
            //利用BigDecimal对象作为参数在format()中调用货币和百分比格式化
        System.out.println("Loan amount:\t" + currency.format(bigLoanAmount));
        System.out.println("Interest rate:\t" + percent.format(bigInterestRate));
        System.out.println("Interest:\t" + currency.format(bigInterest));

        Loan amount:  ￥129,876,534,219,876,523.12
        Interest rate: 8.765%
        Interest:  ￥11,384,239,549,149,661.69
        常见用法：
        初始化 BigDecimal a= new BigDecimal("1.35");
        对数值取值：
        1.a.setScale(1,BigDecimal.ROUND_DOWN);
        取一位小数，直接删除后面多余位数，故取值1.3.
        2.a.setScale(1,BigDecimal.ROUND_UP);
        取一位小数，删除后面位数，进一位，故取值1.4.
        3.a.setScale(1,BigDecimal.ROUND_HALF_UP);
        取一位小数，四舍五入，故取值1.4.
        4.a.setScale(1,BigDecimal.ROUND_HALF_DOWN);
        取一位小数，五舍六入，故取值1.3.
        */
    }

    public static BigDecimal addedPrice(String str1, String str2, int retain) {
        BigDecimal bignum1 = new BigDecimal(str1);
        BigDecimal bignum2 = new BigDecimal(str2);
        BigDecimal bignum3 = bignum1.add(bignum2);
        BigDecimal setScale = bignum3.setScale(retain, BigDecimal.ROUND_HALF_DOWN);
        System.out.println(" 和 " + setScale);
        return setScale;
    }


    /**
     * 判断字符串是否未为整数
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (int i = 0; i < str.length(); i++) {
            //System.out.println(str.charAt(i));
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     * @param v1 被除数
     * @param v2 除数
     * @param retain 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double divisor(double v1,double v2,int retain){

        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2,retain,BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}

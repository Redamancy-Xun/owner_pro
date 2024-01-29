package com.example.forum.util;

/**
 * 验证码生成类
 */
public class RandomVerifyCodeUtil {
    public static int getRandNum(int min, int max) {
        int randNum = min + (int)(Math.random() * ((max - min) + 1));
        return randNum;
    }

    //返回一个六位数的数字验证码
    public static String getRandomVerifyCode(){
        return getRandNum(100000,999999)+"";
    }

}

package com.example.demo.util;

public class Utils {
    //求超期几率时的通用方法
    public static Double getExpiredProbability(int expiredNumber, int allNumber){
        if (allNumber == 0){
            return 0.0d;
        }
        return (double) expiredNumber / allNumber;
    }
}

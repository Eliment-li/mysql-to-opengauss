package com.tiange.core.utils.others;

public class StringUtils {

    public static boolean isEmpty(String str) {

        return str == null || str.length() == 0;
    }

    /*字符串替换 开始*/
    //todo  优化字符串替换的方式
    public static void replace(String target, StringBuilder replacement,
                               StringBuilder builder) {
        int indexOfTarget = -1;
        while ((indexOfTarget = builder.indexOf(target)) >= 0) {
            builder.replace(indexOfTarget, indexOfTarget + target.length(), replacement.toString());
        }
    }

    public static void replace(String target, String replacement,
                               StringBuilder builder) {
        int indexOfTarget = -1;
        while ((indexOfTarget = builder.indexOf(target)) >= 0) {
            builder.replace(indexOfTarget, indexOfTarget + target.length(), replacement.toString());
        }
    }

    public static void replace(StringBuilder target, StringBuilder replacement,
                               StringBuilder builder) {
        int indexOfTarget = -1;
        while ((indexOfTarget = builder.indexOf(target.toString())) >= 0) {
            builder.replace(indexOfTarget, indexOfTarget + target.length(), replacement.toString());
        }
    }
    /*字符串替换 结束*/
}

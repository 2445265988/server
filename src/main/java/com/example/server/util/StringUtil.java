package com.example.server.util;

public class StringUtil {
    // 判断字符串为空
    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }

    // 判断字符串不为空
    public static boolean isNotEmpty(String str){
        return str != null && !"".equals(str);
    }

    // 判断是否全是英文
    public static  boolean isEnglish(String str){
        return str.replaceAll(" ","").matches("[a-zA-Z]+");
    }

    // 替换关键词中的符号
    public static String replaceAllKW(String str){
        return str.replaceAll("，","；")
                .replaceAll("、","；")
                .replaceAll(",","；")
                .replaceAll(";","；");
    }

    public static void main(String[] args) {
        String str = "java EE";
        if(StringUtil.isEnglish(str)){
            System.out.println(str+"全英文");
        }else {
            System.out.println(str+"不全英文");
        }
    }
}

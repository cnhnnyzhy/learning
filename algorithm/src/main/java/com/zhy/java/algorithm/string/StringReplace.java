package com.zhy.java.algorithm.string;


/**
 * 替换字符串中的空格
 * 题目：请实现一个函数，把字符串中的每个空格替换成“%20”。
 * 例如输入“We are happy.”，则输出“We%20are%20happy.”。
 * @author yang.zhang3
 * @create 2018/4/27
 */
public class StringReplace {
    public static void main(String[] args){
        replace("We are happy.", " ", "%20");
    }
    private static void replace(String str, String oldStr, String newStr){
        if(str == null || str.length() <= 0){
            return;
        }
        if(oldStr == null || oldStr.length() <= 0){
            return;
        }
        if(newStr == null || newStr.length() <= 0){
            return;
        }
        char oldChar = oldStr.charAt(0);
        int lenOfStr = str.length();
        int newLenOfStr = lenOfStr;
        int lenOfOldStr = 1;
        int lenOfNewStr = newStr.length();
        int addLen = lenOfNewStr - lenOfOldStr;
        for(int i=0; i<lenOfStr; i++){
            if(str.charAt(i) == oldChar){
                newLenOfStr += addLen;
            }
        }
        char[] newChars = new char[newLenOfStr];
        int p1 = lenOfStr - 1;
        int p2 = newLenOfStr - 1;
        while (p1 >= 0){
            char c = str.charAt(p1);
            if(c == oldChar){
                for(int i=lenOfNewStr - 1; i>=0; i--){
                    newChars[p2] = newStr.charAt(i);
                    p2--;
                }
            }else{
                newChars[p2] = c;
                p2--;
            }
            p1--;
        }
        System.out.println(new String(newChars));
    }

}

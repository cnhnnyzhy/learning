package com.zhy.java.algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 计算出字符串中最长的不重复字符串的长度
 *
 * @author yang.zhang3
 * @create 2018/3/8
 */
public class MaxNotRepeatingStringSearch {

    public static void main(String[] args){
        int maxLen = 0;
        List<String> maxList = new ArrayList<String>();
        StringBuilder compareStr = new StringBuilder();
        String str = "abcddefg";
        char[] chars = str.toCharArray();
        String currentChar;
        int repeatIndex;
        int compareLen = 0;
        for(int i=0; i<chars.length; i++){
            currentChar = String.valueOf(chars[i]);
            if(i == 0){
                compareStr.append(currentChar);
                maxLen = compareStr.length();
                maxList.add(compareStr.toString());
                System.out.println(compareStr);
                continue;
            }
            if((repeatIndex = compareStr.indexOf(currentChar)) != -1){
                compareLen = compareStr.length();
                if(compareLen > maxLen){
                    maxLen = compareLen;
                    maxList.clear();
                    maxList.add(compareStr.toString());
                }else if(compareLen == maxLen){
                    maxList.add(compareStr.toString());
                }
                compareStr = new StringBuilder(compareStr.substring(repeatIndex + 1));
            }
            compareStr.append(currentChar);
            System.out.println(compareStr);
        }
        compareLen = compareStr.length();
        if(compareLen > maxLen) {
            maxLen = compareLen;
            maxList.clear();
            maxList.add(compareStr.toString());
        } else if(compareLen == maxLen){
            maxList.add(compareStr.toString());
        }
        System.out.println("最长不重复字符串为：" + maxList + "，长度为：" + maxLen);
    }
}

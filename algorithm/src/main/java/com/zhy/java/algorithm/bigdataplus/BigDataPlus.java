package com.zhy.java.algorithm.bigdataplus;

/**
 * 有两个数字A,B, 他们都是有500位的数字, 如何规定不能使用BigDecimal 去运算的话怎么求的A+B的结果?
 * @author yang.zhang3
 * @create 2018/4/4
 */
public class BigDataPlus {
    public static void main(String[] args){
        String a = "9919";
        String b = "999381";
        plus1(a, b);
        plus2(a, b);
    }

    private static void plus1(String a, String b){
        int lenA = a.length();
        int lenB = b.length();
        int maxLen = lenA > lenB ? lenA : lenB;

        int count = Math.abs(lenA - lenB);
        String str_0 = "";
        for(int i=0; i<count; i++){
            str_0 += "0";
        }
        String newA = a;
        String newB = b;
        if(lenA < lenB){
            newA = str_0 + newA;
        }else if(lenA > lenB){
            newB = str_0 + newB;
        }
        char[] c_t = new char[maxLen];
        int n1, n2, t, tmp = 0;
        for(int i=maxLen-1; i>=0; i--){
            n1 = Integer.parseInt(String.valueOf(newA.charAt(i)));
            n2 = Integer.parseInt(String.valueOf(newB.charAt(i)));
            t = n1 + n2 + tmp;
            tmp = t / 10;
            c_t[i] = (char)(t % 10 + 48);
        }
        String s = new String(c_t);
        if(tmp > 0) {
            s = tmp + s;
        }
        System.out.println(s);
    }

    private static void plus2(String a, String b){
        int lenA = a.length();
        int lenB = b.length();
        int minLen = lenA > lenB ? lenB : lenA;

        int count = Math.abs(lenA - lenB);
        String newA, newB;
        String leftStr = "";
        if(lenA > lenB){
            leftStr = new String(a.substring(0, count));
            newA = new String(a.substring(count));
            newB = b;
        }else if(lenA < lenB){
            leftStr = new String(b.substring(0, count));
            newB = new String(b.substring(count));
            newA = a;
        }else{
            newA = a;
            newB = b;
        }
        char[] chars = new char[minLen];
        int nA, nB, t, tmp = 0;
        for(int i = minLen - 1; i >= 0; i--){
            nA = Integer.parseInt(String.valueOf(newA.charAt(i)));
            nB = Integer.parseInt(String.valueOf(newB.charAt(i)));
            t = nA + nB + tmp;
            if(t >= 10) {
                tmp = t / 10;
                chars[i] = (char) (t % 10 + 48);
            }else{
                tmp = 0;
                chars[i] = (char) (t + 48);
            }
        }

        if(tmp > 0){
            int n;
            char[] leftChars = leftStr.toCharArray();
            for(int i=count - 1; i>=0; i--){
                n = Integer.parseInt(String.valueOf(leftStr.charAt(i)));
                t = n + tmp;
                if(t >= 10) {
                    tmp = t / 10;
                    leftChars[i] = (char) (t % 10 + 48);
                }else{
                    tmp = 0;
                    leftChars[i] = (char) (t + 48);
                    break;
                }
            }
            leftStr = new String(leftChars);
            if(tmp > 0){
                leftStr = tmp + leftStr;
            }
        }
        String s = leftStr + new String(chars);
        System.out.println(s);
    }
}

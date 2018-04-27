package com.zhy.java.algorithm.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * TODO
 *
 * @author yang.zhang3
 * @create 2018/3/29
 */
public class TestHashMap {
    public static void main(String[] args){
        Map<BadHash, String> map = new HashMap<BadHash, String>();
        Random r = new Random();
        for(int i=0; i<10000; i++){
            map.put(new BadHash(r.nextDouble()), String.valueOf(i));
        }
    }
}

class BadHash{
    double d;
    public BadHash(double d){
        this.d = d;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}

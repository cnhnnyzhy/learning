package com.zhy.java.algorithm.tree.find;

import java.io.File;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 使用递归的方式查找树型结构的数据
 * 适用于树型结构层次不是很深的场景
 * 优点：速度相对较快
 * 缺点：由于受限于方法栈的深度，在树型结构层次较深的情况下，会出现[java.lang.StackOverflowError]异常。
 *
 * @author Ocean
 * @create 2017/10/17
 */
public class TreeFindUsingRecursion extends TreeFind {

    public TreeFindUsingRecursion(String rootPath, Set<String> matchSuffix) {
        super(rootPath, matchSuffix);
    }

    @Override
    protected void check() {
        checkDir(rootFile);
    }

    private void checkDir(File dir) {
        File[] childFiles = dir.listFiles();
        if (childFiles == null) {
            return;
        }
        for (File child : childFiles) {
            if (child.isDirectory()) {
                dirCount++;
                checkDir(child);
            } else {
                checkFile(child);
            }
        }
    }


    public static void main(String[] args) {
        String rootPath = "d:/";
        Set<String> matchSuffix = new HashSet<String>();
        matchSuffix.add(".mp3");
        matchSuffix.add(".mp4");
        matchSuffix.add(".rmvb");
        matchSuffix.add(".rm");
        TreeFindUsingRecursion treeFind = new TreeFindUsingRecursion(rootPath, matchSuffix);
        treeFind.find();
    }
}

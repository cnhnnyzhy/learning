package com.zhy.java.algorithm.tree.find;

import java.io.File;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 使用循环的方式查找树型结构的数据
 * 适用于树型结构层次比较深的场景
 * 优点：无论层次有多深，都不会出现异常。
 * 缺点：速度相对较慢。
 * @author Ocean
 * @create 2017/10/17
 */
public class TreeFindUsingLoop extends TreeFind {
    private LinkedList<File> dirList;

    public TreeFindUsingLoop(String rootPath, Set<String> matchSuffix) {
        super(rootPath, matchSuffix);
        this.dirList = new LinkedList<File>();
    }

    @Override
    public void check() {
        checkDir(rootFile);
        while (!dirList.isEmpty()) {
            checkDir(dirList.removeFirst());
        }
    }

    private void checkDir(File dir) {
        File[] childFiles = dir.listFiles();
        if (childFiles == null) {
            return;
        }
        for (File child : childFiles) {
            if (child.isDirectory()) {
                dirCount++;
                dirList.add(child);
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
        TreeFindUsingLoop treeFind = new TreeFindUsingLoop(rootPath, matchSuffix);
        treeFind.find();
    }
}

package com.zhy.java.algorithm.tree.find;

import java.io.File;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * 树型结构遍历
 *
 * @author Ocean
 * @create 2017/10/17
 */
public abstract class TreeFind {
    /**
     * 根目录路径
     */
    private String rootPath;
    protected File rootFile;
    /**
     * 要匹配的文件后缀
     */
    private Set<String> matchSuffix;
    private Set<String> suffixSet;
    protected int dirCount;
    protected int fileCount;
    private long bTime;
    private long eTime;

    public TreeFind(String rootPath, Set<String> matchSuffix) {
        this.rootPath = rootPath;
        this.rootFile = new File(rootPath);
        this.matchSuffix = matchSuffix;
        this.suffixSet = new HashSet<String>();
    }

    public void find() {
        bTime = System.currentTimeMillis();
        if (!rootFile.exists()) {
            System.out.println(MessageFormat.format("目录[{0}]不存在！", rootPath));
            return;
        }
        check();
        eTime = System.currentTimeMillis();
        printResult();
    }

    protected abstract void check();


    protected void checkFile(File file) {
        fileCount++;
        String fileName = file.getName();
        String filePath = file.getPath();
        int lastPointIndex = fileName.lastIndexOf('.');
        lastPointIndex = lastPointIndex == -1 ? 0 : lastPointIndex;
        String suffix = fileName.toLowerCase().substring(lastPointIndex);
        if (!suffixSet.contains(suffix)) {
            suffixSet.add(suffix);
        }
        if (matchSuffix != null && matchSuffix.contains(suffix)) {
            System.out.println(filePath);
        }
    }

    protected void printResult() {
        long totalSecond = (eTime - bTime) / 1000;
        long m = totalSecond / 60;
        long s = totalSecond % 60;
        String result = MessageFormat.format("文件夹[{0}]下共存在目录{1}个，文件{2}个，查找耗时{3}分{4}秒。"
                , rootPath, dirCount, fileCount, m, s);
        System.out.println(result);
        System.out.println("存在后缀：");
        System.out.println(suffixSet);
    }
}

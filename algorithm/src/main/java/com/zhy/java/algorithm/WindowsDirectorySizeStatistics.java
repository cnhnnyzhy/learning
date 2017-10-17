package com.zhy.java.algorithm;

import java.io.File;
import java.text.MessageFormat;

public class WindowsDirectorySizeStatistics {
    private String dirPath;
    private long gtSize;

    public WindowsDirectorySizeStatistics(String dirPath, long gtSize) {
        this.dirPath = dirPath;
        this.gtSize = gtSize;
    }

    public void statistics(){
        File dirFile = new File(dirPath);
        if(!dirFile.exists()){
            System.out.println("目录["+dirPath+"]不存在！");
            return;
        }
        if(!dirFile.isDirectory()){
            System.out.println("["+dirPath+"]不是目录！");
            return;
        }
        File[] childFiles = dirFile.listFiles();
        if(childFiles == null || childFiles.length <= 0){
            System.out.println("目录["+dirPath+"]为空！");
            return;
        }
        long size;
        for (File childFile : childFiles){
            if(childFile.isDirectory()){
                size = statisticsDir(childFile);
            }else{
                size = childFile.length();
            }
            if(gtSize > 0 && size > gtSize) {
                System.out.println(MessageFormat.format("{0}[{1}]", childFile.getPath(), transFileSize(size)));
            }
        }

    }

    private long statisticsDir(File dirFile){
        long size = 0;
        File[] files = dirFile.listFiles();
        if(files == null){
            return size;
        }
        for (File file : files){
            if(file.isDirectory()){
                size += statisticsDir(file);
            }else{
                size += file.length();
            }
        }
        return size;
    }

    private String transFileSize(long size){
        long t_kb = size / 1024;
        long t_mb = t_kb / 1024;
        long kb = t_kb % 1024;
        long t_gb = t_mb / 1024;
        long mb = t_mb % 1024;
        StringBuffer sb = new StringBuffer();
        if(t_gb > 0){
            sb.append(t_gb).append("G");
        }else if(mb > 0){
            sb.append(mb).append("M");
        }else {
            sb.append(kb).append("K");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String dirPath = "D:\\IdeaProjects\\learning\\java-interview";
        dirPath = "c:/";
        dirPath = "c:\\Windows";
        long gtSize = 1 * 1024 * 1024 * 1000;
        WindowsDirectorySizeStatistics windowsDirectorySizeStatistics = new WindowsDirectorySizeStatistics(dirPath, gtSize);
        windowsDirectorySizeStatistics.statistics();
    }
}

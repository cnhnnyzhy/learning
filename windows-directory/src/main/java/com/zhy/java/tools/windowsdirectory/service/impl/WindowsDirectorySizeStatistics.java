package com.zhy.java.tools.windowsdirectory.service.impl;

import com.zhy.java.tools.windowsdirectory.model.WindowsFileModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class WindowsDirectorySizeStatistics {
    private static final Logger log = LoggerFactory.getLogger(WindowsDirectorySizeStatistics.class);
    public static final DecimalFormat df = new DecimalFormat("#.##");
    private File dirPath;
    private long gtSize;

    public WindowsDirectorySizeStatistics(File dirPath, long gtSize) {
        this.dirPath = dirPath;
        this.gtSize = gtSize;
    }

    public WindowsFileModel statistics() {
        File dirFile = dirPath;
        if (!dirFile.exists()) {
            System.out.println("目录[" + dirPath + "]不存在！");
            return null;
        }
        if (!dirFile.isDirectory()) {
            System.out.println("[" + dirPath + "]不是目录！");
            return null;
        }
        WindowsFileModel fileModel = getInitFileModel(dirFile);
        File[] childFiles = dirFile.listFiles();
        if (childFiles == null || childFiles.length <= 0) {
            System.out.println("目录[" + dirPath + "]为空！");
            fileModel.setFileSize(0L);
            fileModel.setShowFileSize("0K");
        }else {
            statisticsDir(dirFile, fileModel);
        }
        return getLastFileModel(dirFile, fileModel);
    }

    private WindowsFileModel getLastFileModel(File file, WindowsFileModel fileModel){
        File parent = file.getParentFile();
        WindowsFileModel lastFileModel = fileModel;
        WindowsFileModel currentFileModel;
        List<WindowsFileModel> children;
        while(parent != null){
            currentFileModel = getInitFileModel(parent);
            currentFileModel.setFileSize(lastFileModel.getFileSize());
            currentFileModel.setShowFileSize(lastFileModel.getShowFileSize());
            children = new ArrayList<>();
            children.add(lastFileModel);
            currentFileModel.setChildren(children);
            lastFileModel = currentFileModel;
            parent = parent.getParentFile();
        }
        return lastFileModel;
    }

    private WindowsFileModel getInitFileModel(File file){
        WindowsFileModel fileModel = new WindowsFileModel();
        String fileName = file.getName();
        String filePath = file.getPath();
        if(fileName == null || "".equals(fileName)){
            fileName = filePath;
        }
        fileModel.setFileName(fileName);
        fileModel.setFilePath(filePath);
        fileModel.setDelete(false);
        if(file.isDirectory()){
            fileModel.setDirectory(true);
        }else{
            fileModel.setDirectory(false);
        }
        return fileModel;
    }

    private long statisticsDir(File dirFile, WindowsFileModel fileModel) {
        long totalSize = 0;
        File[] files = dirFile.listFiles();
        if (files == null) {
            return totalSize;
        }
        List<WindowsFileModel> children = new ArrayList<>();
        WindowsFileModel child;
        long size;
        for (File file : files) {
            child = getInitFileModel(file);
            if (file.isDirectory()) {
                size = statisticsDir(file, child);
            } else {
                size = file.length();
            }
            if (gtSize <= 0 || size > gtSize) {
                child.setFileSize(size);
                child.setShowFileSize(transFileSize(size));
                children.add(child);
                if(log.isDebugEnabled() && !file.isDirectory()){
                    log.debug(MessageFormat.format("{0}[{1}]", child.getFilePath(), child.getShowFileSize()));
                }
            }
            totalSize += size;
        }
        fileModel.setChildren(children);
        fileModel.setFileSize(totalSize);
        fileModel.setShowFileSize(transFileSize(totalSize));
        if(log.isDebugEnabled()){
            log.debug(MessageFormat.format("{0}[{1}]", fileModel.getFilePath(), fileModel.getShowFileSize()));
        }
        return totalSize;
    }

    private String transFileSize(long size) {
        double kb = (double) size / 1000;
        double mb = kb / 1024;
        double gb = kb / (1024 * 1024);
        StringBuffer sb = new StringBuffer();
        if (gb >= 1) {
            sb.append(df.format(gb)).append("G");
        } else if (mb >= 1) {
            sb.append(df.format(mb)).append("M");
        } else {
            sb.append(df.format(kb)).append("K");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String dirPath = "D:\\IdeaProjects\\learning\\java-interview";
       // dirPath = "c:/";
        //dirPath = "c:\\Windows";
//        dirPath = "c:\\Users\\yang.zhang3\\AppData\\Local";
//        dirPath = "e:/迅雷下载";
//        dirPath = "d:/";
        //dirPath = "C:\\log";
        dirPath = "D:\\blueair\\data\\processlogs";
        long gtSize = 1 * 1024 * 1000;
        gtSize = -1;
        WindowsDirectorySizeStatistics windowsDirectorySizeStatistics = new WindowsDirectorySizeStatistics(new File(dirPath), gtSize);
        WindowsFileModel fileModel =  windowsDirectorySizeStatistics.statistics();



        System.out.println("success");
    }
}

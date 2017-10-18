package com.zhy.java.tools.windowsdirectory.model;

import java.util.List;

/**
 * Windows文件实体类
 *
 * @author Ocean
 * @create 2017/10/18
 */
public class WindowsFileModel {
    private String name;
    /* 文件名称 */
    private String fileName;
    /* 文件路径 */
    private String filePath;
    /* 是否是目录 */
    private Boolean isDirectory;
    /* 文件大小（单位字节） */
    private Long fileSize;
    /* 转换单位后的文件大小 */
    private String showFileSize;
    /* 是否删除 */
    private Boolean isDelete;
    /* 子文件 */
    private List<WindowsFileModel> children;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(Boolean directory) {
        isDirectory = directory;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getShowFileSize() {
        return showFileSize;
    }

    public void setShowFileSize(String showFileSize) {
        this.showFileSize = showFileSize;
    }

    public Boolean isDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public List<WindowsFileModel> getChildren() {
        return children;
    }

    public void setChildren(List<WindowsFileModel> children) {
        this.children = children;
    }

    public String getName() {
        return this.fileName + "["+this.showFileSize+"]";
    }

    public void setName(String name) {
        this.name = name;
    }
}

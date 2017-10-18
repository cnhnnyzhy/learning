package com.zhy.java.tools.windowsdirectory.service;

import com.ifec.blueair.framework.vo.ResponseVO;
import com.zhy.java.tools.windowsdirectory.model.WindowsFileModel;

/**
 * ${DESCRIPTION}
 *
 * @author Ocean
 * @create 2017/10/18
 */
public interface IWindowsDirectoryService {
    /**
     * @Author: Ocean
     * @Description: 根据根目录，获取所有子文件
     * @MethodName: getAllFileByRootPath
     * @param rootPath
     * @return:
     * @Date: 10:51 2017/10/18
     */
    ResponseVO<WindowsFileModel> getAllFileByRootPath(String rootPath);
}

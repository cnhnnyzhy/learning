package com.zhy.java.tools.windowsdirectory.service.impl;

import com.ifec.blueair.framework.util.ResponseVOUtil;
import com.ifec.blueair.framework.vo.ResponseVO;
import com.zhy.java.tools.windowsdirectory.model.WindowsFileModel;
import com.zhy.java.tools.windowsdirectory.service.IWindowsDirectoryService;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * ${DESCRIPTION}
 *
 * @author Ocean
 * @create 2017/10/18
 */
@Service
public class WindowsDirectoryServiceImpl implements IWindowsDirectoryService {
    /**
     * @param rootPath
     * @Author: Ocean
     * @Description: 根据根目录，获取所有子文件
     * @MethodName: getAllFileByRootPath
     * @return:
     * @Date: 10:51 2017/10/18
     */
    @Override
    public ResponseVO<WindowsFileModel> getAllFileByRootPath(String rootPath) {
        if(rootPath == null || "".equals(rootPath)){
            return ResponseVOUtil.generateParameterErrorResponseVO("根目录路径不能为空！");
        }
        File rootFile = new File(rootPath);
        if(!rootFile.exists()){
            return ResponseVOUtil.generateCommonErrorResponseVO("根目录不存在！");
        }
        if(!rootFile.isDirectory()){
            return ResponseVOUtil.generateCommonErrorResponseVO("根目录不是目录！");
        }

        WindowsDirectorySizeStatistics statistics = new WindowsDirectorySizeStatistics(rootFile, -1);
        WindowsFileModel fileModel = statistics.statistics();
        return ResponseVOUtil.generateSuccessResponseVO(fileModel);
    }
}

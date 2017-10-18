package com.zhy.java.tools.windowsdirectory.service.impl;

import com.zhy.java.tools.windowsdirectory.model.WindowsFileModel;
import com.zhy.java.tools.windowsdirectory.service.IWindowsDirectoryService;
import com.zhy.java.tools.windowsdirectory.util.ResponseVOUtil;
import com.zhy.java.tools.windowsdirectory.vo.ResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log = LoggerFactory.getLogger(WindowsDirectoryServiceImpl.class);
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
        long bTime = System.currentTimeMillis();
        WindowsDirectorySizeStatistics statistics = new WindowsDirectorySizeStatistics(rootFile, -1);
        WindowsFileModel fileModel = statistics.statistics();
        long eTime = System.currentTimeMillis();
        long t_s = (eTime - bTime) / 1000;
        long t_m = t_s / 60;
        long s = t_s % 60;
        long h = t_m / 60;
        long m = t_m % 60;

        System.out.println("统计结束，耗时："+h+"时"+m+"分"+s+"秒");
        return ResponseVOUtil.generateSuccessTResponseVO(fileModel);
    }
}

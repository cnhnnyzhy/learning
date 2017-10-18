package com.zhy.java.tools.windowsdirectory.controller;

import com.ifec.blueair.framework.util.ResponseVOUtil;
import com.ifec.blueair.framework.vo.ResponseVO;
import com.zhy.java.tools.windowsdirectory.model.WindowsFileModel;
import com.zhy.java.tools.windowsdirectory.service.IWindowsDirectoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Ocean
 * @create 2017/10/18
 */
@RestController
@RequestMapping("windows")
public class WindowsDirectoryController {
    @Autowired
    private IWindowsDirectoryService windowsDirectoryService;
    @ApiOperation("获取根目录下所有的文件和文件夹")
    @GetMapping("/allfiles")
    public ResponseVO<WindowsFileModel[]> getAllFileByRootPath(@ApiParam("根目录") @RequestParam String rootPath){
        ResponseVO responseVO = windowsDirectoryService.getAllFileByRootPath(rootPath);
        if(responseVO.isBlueairSuccess()){
            return ResponseVOUtil.generateSuccessResponseVO(new WindowsFileModel[]{(WindowsFileModel)responseVO.getDatas()});
        }
        return responseVO;
    }
}

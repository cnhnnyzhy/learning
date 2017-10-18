package com.zhy.java.tools.windowsdirectory.util;

import java.util.List;
import java.util.Map;

import com.zhy.java.tools.windowsdirectory.vo.ResponsePageVO;
import com.zhy.java.tools.windowsdirectory.vo.ResponseVO;

/** 
 * <p>Title:ResponseVOUtil</p> 
 * <p>Description:生成ResponseVO对象的工具类</p>
 * <p>Company:蓝色光标天地互联科技（北京）有限公司</p>
 * @author yang.zhang3
 * @date 2016年2月16日 下午5:25:52
 */
public class ResponseVOUtil {
	/*返回编码由四位数字组成，前两位代表错误类型大类，后两位代码错误类型小类*/
	//成功编码
	public static final String CODE_0000 = "0000";
	//通用错误编码
	public static final String CODE_9999 = "9999";
	/*参数错误编码*/
	public static final String CODE_1000 = "1000";
	//权限校验失败错误编码
	public static final String CODE_3000 = "3000";
	//token校验异常错误编码
	public static final String CODE_5000 = "5000";
	//第三方返回错误编码
	public static final String CODE_2000 = "2000";
	
	/**
	 * @Method_name : generateSuccessResponseVO
	 * @Description : 生成成功返回VO
	 * @param 		: @param msg-成功信息
	 * @param 		: @param returnObj-返回数据
	 * @return 		: ResponseVO  
	 * @author 		: yang.zhang3
	 * @date        : 2016年2月16日
	 * @deprecated  用generateSuccessResponseVO(List<T> data)代替，2016年6月16日
	 */
	@Deprecated
	public static <T> ResponseVO<T> generateSuccessResponseVO(String msg, Object returnObj){
		ResponseVO<T> vo = new ResponseVO<T>(CODE_0000, true, msg);
		vo.setReturnObj(returnObj);
		return vo;
	}
	@Deprecated
	public static <T> ResponseVO<T> generateSuccessResponseVO(String msg, List<Map<String, Object>> mapList){
		ResponseVO<T> vo = new ResponseVO<T>(CODE_0000, true, msg);
		vo.setMapList(mapList);
		return vo;
	}
	@Deprecated
	public static <T> ResponseVO<T> generateSuccessResponseVO(List<T> data){
		ResponseVO<T> vo = new ResponseVO<T>(CODE_0000, true, null);
		vo.setData(data);
		return vo;
	}
	public static <T> ResponseVO<T> generateSuccessTResponseVO(T datas){
		ResponseVO<T> vo = new ResponseVO<T>(CODE_0000, true, null);
		vo.setDatas(datas);
		return vo;
	}
	public static <T> ResponseVO<T> generateSuccessResponseVO(String msg){
		return new ResponseVO<T>(CODE_0000, true, msg);
	}
	@Deprecated
	public static <T> ResponseVO<T> generateSuccessResponseVO(ResponseVO<T> vo){
		vo.setBlueairCode(CODE_0000);
		vo.setBlueairSuccess(true);
		return vo;
	}
	/**
	 * 生成带分页的vo对象
	 * @param totalCount
	 * @param pageSize
	 * @param currentPage
	 * @param data
	 * @return
	 * @deprecated 2016-08-03
	 */
	@Deprecated
	public static <T> ResponseVO<T> generateSuccessResponsePageVO(long totalCount, int pageSize, int currentPage, List<T> data){
        ResponsePageVO<T> vo = new ResponsePageVO<T>(totalCount,pageSize,currentPage,null);
		vo.setBlueairCode(CODE_0000);
		vo.setBlueairSuccess(true);
		vo.setData(data);
		return vo;
	}
	public static <T> ResponseVO<T> generateSuccessTResponsePageVO(long totalCount, int pageSize, int currentPage, T datas){
        ResponsePageVO<T> vo = new ResponsePageVO<T>(totalCount,pageSize,currentPage,null);
		vo.setBlueairCode(CODE_0000);
		vo.setBlueairSuccess(true);
		vo.setDatas(datas);
		return vo;
	}
	/**
	 * @Method_name : generateParameterErrorResponseVO
	 * @Description : 生成参数错误返回VO
	 * @param 		: @param errMsg-错误信息
	 * @return 		: ResponseVO  
	 * @author 		: yang.zhang3
	 * @date        : 2016年2月16日
	 */
	public static <T> ResponseVO<T> generateParameterErrorResponseVO(String errMsg){
		ResponseVO<T> vo = new ResponseVO<T>(CODE_1000, false, errMsg);
		return vo;
	}
	@Deprecated
	public static <T> ResponseVO<T> generateParameterErrorResponseVO(String errMsg, ResponseVO<T> vo){
		setErrorValue(vo, CODE_1000, errMsg);
		return vo;
	}
	@Deprecated
	public static <T> ResponseVO<T> generateParameterErrorResponseVO(ResponseVO<T> vo){
		setErrorValue(vo, CODE_1000, vo.getBlueairErrorMsg());
		return vo;
	}
	
	/**
	 * @Method_name : generateCommonErrorResponseVO
	 * @Description : 通用的业务逻辑错误返回VO
	 * @param 		: @param errMsg
	 * @param 		: @return 
	 * @return 		: ResponseVO  
	 * @author 		: yang.zhang3
	 * @date        : 2016年2月18日
	 */
	public static <T> ResponseVO<T> generateCommonErrorResponseVO(String errMsg){
		return new ResponseVO<T>(CODE_9999, false, errMsg);
	}
	@Deprecated
	public static <T> ResponseVO<T> generateCommonErrorResponseVO(String errMsg, ResponseVO<T> vo){
		setErrorValue(vo, CODE_9999, errMsg);
		return vo;
	}
	@Deprecated
	public static <T> ResponseVO<T> generateCommonErrorResponseVO(ResponseVO<T> vo){
		setErrorValue(vo, CODE_9999, vo.getBlueairErrorMsg());
		return vo;
	}
	/**
	 * @Method_name : generatePermissionErrorResponseVO
	 * @Description : 权限校验失败返回VO
	 * @param 		: @param errMsg
	 * @param 		: @return 
	 * @return 		: ResponseVO  
	 * @author 		: yang.zhang3
	 * @date        : 2016年3月1日
	 */
	public static <T> ResponseVO<T> generatePermissionErrorResponseVO(String errMsg){
		return new ResponseVO<T>(CODE_3000, false, errMsg);
	}
	
	private static <T> void  setErrorValue(ResponseVO<T> vo, String blueairCode, String errorMsg){
		vo.setBlueairCode(blueairCode);
		vo.setBlueairSuccess(false);
		vo.setBlueairErrorMsg(errorMsg);
	}
	/***
	 * 合作伙伴（第三方）返回错误
	 * @param errMsg
	 * @return
	 */
	public static <T> ResponseVO<T> generatePartnerErrorResponseVO(String errMsg){
		return new ResponseVO<T>(CODE_2000, false, errMsg);
	}
	@Deprecated
	public static <T> ResponseVO<T> generatePartnerErrorResponseVO(String errMsg,ResponseVO<T> vo){
		setErrorValue(vo, CODE_2000, errMsg);
		return vo;
	}
	
	/**
	 * token校验错误
	 * @param errMsg
	 * @return
	 */
	public static <T> ResponseVO<T> generateTokenErrorResponseVO(String errMsg){
		return new ResponseVO<T>(CODE_5000, false, errMsg);
	}
	

	

	
	//禁止实例化
	private ResponseVOUtil(){}
}

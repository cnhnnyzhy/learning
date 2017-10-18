package com.zhy.java.tools.windowsdirectory.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;


/** 
 * <p>ResponseVO.java</p> 
 * 
 * @author yang.zhang3
 * @date 2015年11月20日
 */
public class ResponseVO<T> extends BaseVO {
	private static final long serialVersionUID = 134474305226738346L;
	//接口响应状态码
	@ApiModelProperty(value="接口响应状态码")
	private String blueairCode;
	//接口调用结果，true|false
	@ApiModelProperty(value="接口调用结果，true|false")
	private boolean blueairSuccess;
	//接口调用成功信息
	@ApiModelProperty(value="接口调用成功信息")
	private String blueairSuccessMsg;
	//接口调用失败信息
	@ApiModelProperty(value="接口调用失败信息")
	private String blueairErrorMsg;
	//接口调用警告信息
	@ApiModelProperty(value="接口调用警告信息")
	private String blueairWarningMsg;
	@Deprecated
	@ApiModelProperty(value="接口返回数据列表，map格式")
	private List<Map<String, Object>> mapList;
	//服务与服务之间调用使用，不返回到前端
	@Deprecated
	private Object returnObj;
	
	@Deprecated
	@ApiModelProperty(value="接口调用返回数据")
	private List<T> data;
	@ApiModelProperty(value="接口调用返回数据")
	private T datas;
	
	public ResponseVO(){
	}
	
	public ResponseVO(boolean blueairSuccess,String blueairMsg) {
		this(null, blueairSuccess, blueairMsg);
	}
	
	public ResponseVO(String blueairCode, boolean blueairSuccess,String blueairMsg) {
		this.blueairCode = blueairCode;
		this.blueairSuccess = blueairSuccess;
		if(blueairSuccess){
			this.blueairSuccessMsg = blueairMsg;
		}else{
			this.blueairErrorMsg = blueairMsg;
		}
	}
	
	public ResponseVO(String blueairCode, boolean blueairSuccess,
			String blueairSuccessMsg,String blueairErrorMsg, String blueairWarningMsg,
			List<Map<String, Object>> mapList) {
		super();
		this.blueairCode = blueairCode;
		this.blueairSuccess = blueairSuccess;
		this.blueairSuccessMsg = blueairSuccessMsg;
		this.blueairErrorMsg = blueairErrorMsg;
		this.blueairWarningMsg = blueairWarningMsg;
		this.mapList = mapList;
	}
	
	public String getBlueairCode() {
		return blueairCode;
	}
	public void setBlueairCode(String blueairCode) {
		this.blueairCode = blueairCode;
	}
	public boolean isBlueairSuccess() {
		return blueairSuccess;
	}
	public void setBlueairSuccess(boolean blueairSuccess) {
		this.blueairSuccess = blueairSuccess;
	}
	public String getBlueairSuccessMsg() {
		return blueairSuccessMsg;
	}
	public void setBlueairSuccessMsg(String blueairSuccessMsg) {
		this.blueairSuccessMsg = blueairSuccessMsg;
	}
	public String getBlueairErrorMsg() {
		return blueairErrorMsg;
	}
	public void setBlueairErrorMsg(String blueairErrorMsg) {
		this.blueairErrorMsg = blueairErrorMsg;
	}
	public String getBlueairWarningMsg() {
		return blueairWarningMsg;
	}
	public void setBlueairWarningMsg(String blueairWarningMsg) {
		this.blueairWarningMsg = blueairWarningMsg;
	}

	@Deprecated
	public List<Map<String, Object>> getMapList() {
		return mapList;
	}

	@Deprecated
	public void setMapList(List<Map<String, Object>> mapList) {
		this.mapList = mapList;
	}

	@Deprecated
	public Object getReturnObj() {
		return returnObj;
	}
	@Deprecated
	public void setReturnObj(Object returnObj) {
		this.returnObj = returnObj;
	}


	@Deprecated
	public List<T> getData() {
		return data;
	}
	@Deprecated
	public void setData(List<T> data) {
		this.data = data;
	}

	public T getDatas() {
		return datas;
	}

	public void setDatas(T datas) {
		this.datas = datas;
	}
	
}

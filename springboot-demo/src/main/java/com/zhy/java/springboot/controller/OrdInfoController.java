package com.zhy.java.springboot.controller;

import com.ifec.blueair.framework.controller.RestBaseController;
import com.ifec.blueair.framework.util.ResponseVOUtil;
import com.ifec.blueair.framework.vo.ResponseVO;
import com.zhy.java.springboot.service.IOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ordinfo")
public class OrdInfoController extends RestBaseController{
	
	@Autowired
	private IOrderService orderService;
	
	@ApiOperation(value = "订单列表",notes="author：孙晓伟<br/>date：2017-03-04<br/>分页查询订单列表"
			+ "参数列表：订单号 order_code；收货人 contact_name； 手机号contact_phone；商品编码 goods_code；商品名称 goods_name；"
			+ "订单状态order_state；支付状态 pay_state；物流状态 logistics_state;锁定状态 lock_state；"
			+ "订单开始时间 order_time_begin；订单结束时间order_time_end；支付开始时间pay_time_begin；支付结束时间pay_time_end "
			+ "时间类参数 开始时间加 00:00:00 结束时间加23:59:59")
	@RequestMapping(value = "/list", method = {RequestMethod.GET})
	@ResponseBody
	public ResponseVO<List<Map<String, Object>>> getListWithPaging(HttpServletRequest request) {
		return ResponseVOUtil.generateSuccessTResponseVO(orderService.getOrderList(null));
	}

	
}
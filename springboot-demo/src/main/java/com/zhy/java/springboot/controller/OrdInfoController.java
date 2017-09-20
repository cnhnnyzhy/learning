package com.zhy.java.springboot.controller;

import com.alibaba.fastjson.JSON;
import com.ifec.blueair.framework.controller.RestBaseController;
import com.ifec.blueair.framework.util.ResponseVOUtil;
import com.ifec.blueair.framework.vo.ResponseVO;
import com.zhy.java.springboot.model.OrdInfo;
import com.zhy.java.springboot.service.IOrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("ordinfo")
public class OrdInfoController extends RestBaseController{
	
	@Autowired
	private IOrderService orderService;

	@Autowired
    private RedisTemplate redisTemplate;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private MongoTemplate mongoTemplate;
	
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
	@ApiOperation(value = "保存订单接口", notes = "保存订单")
	@PostMapping("/save")
	@ResponseBody
	public ResponseVO<String> saveOrder(@ModelAttribute("model") OrdInfo ordInfo){
		orderService.saveOrder(ordInfo);
		return ResponseVOUtil.generateSuccessResponseVO("订单保存成功！");
	}

	@ApiOperation(value = "保存订单到Redis中", notes = "保存订单到Redis中")
	@PostMapping("/savetoredis")
	@ResponseBody
	public ResponseVO<String> saveOrderToRedis(@ModelAttribute("model") OrdInfo ordInfo){
		//redisTemplate.opsForValue().set(ordInfo.getOrder_code(), ordInfo);
		stringRedisTemplate.opsForValue().set(ordInfo.getOrder_code(), JSON.toJSONString(ordInfo));
		return ResponseVOUtil.generateSuccessResponseVO("订单保存到redis成功！");
	}

	@ApiOperation(value = "根据订单编码获取订单信息", notes = "根据订单编码获取订单信息")
	@PostMapping("/get/bycode/{order_code}")
	@ResponseBody
	public ResponseVO<OrdInfo> getOrderFromRedis(@ApiParam("订单编码") @PathVariable("order_code") String orderCode){
		//OrdInfo ordInfo = (OrdInfo) redisTemplate.opsForValue().get(orderCode);
		OrdInfo ordInfo = JSON.parseObject(stringRedisTemplate.opsForValue().get(orderCode), OrdInfo.class);
		return ResponseVOUtil.generateSuccessTResponseVO(ordInfo);
	}


	@ApiOperation(value = "获取mongodb中的数据", notes = "获取mongodb中的数据")
	@GetMapping("/getcount/frommongo")
	@ResponseBody
	public ResponseVO<Long> getDataCountFromMongo(){
		Long count = mongoTemplate.getCollection("ad_log").count();
		return ResponseVOUtil.generateSuccessTResponseVO(count);
	}

	@ApiOperation(value = "获取mongodb中的数据", notes = "获取mongodb中的数据")
	@GetMapping("/getlist/frommongo")
	@ResponseBody
	public ResponseVO<List<Map>> getDataFromMongo(){
		List<Map> list = mongoTemplate.find(null, Map.class, "ad_log");
		return ResponseVOUtil.generateSuccessTResponseVO(list);
	}

	
}
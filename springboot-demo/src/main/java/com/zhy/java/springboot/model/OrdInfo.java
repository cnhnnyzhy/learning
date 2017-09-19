package com.zhy.java.springboot.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.math.BigDecimal;
import com.ifec.blueair.framework.model.BaseModel;
import io.swagger.annotations.ApiModelProperty;

/*
 * @version 2.0
 */

public class OrdInfo extends BaseModel {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="订单id")
	private Long order_id;
	
	@ApiModelProperty(value="父订单id")
	private Long parent_order_id;
	
	@ApiModelProperty(value="支付id")
	private Long payment_id;
	
	@ApiModelProperty(value="订单编码")
	private String order_code;
	
	@ApiModelProperty(value="会员id")
	private Long member_id;
	
	@ApiModelProperty(value="商品总金额")
	private Long goods_total_money;
	
	@ApiModelProperty(value="物流费用")
	private Long logistics_money;
	
	@ApiModelProperty(value="优惠金额")
	private Long preferential_money;
	
	@ApiModelProperty(value="订单总金额")
	private Long total_money;
	
	@ApiModelProperty(value="0-新增  1-待确认 2-已确认 3-已取消  4-已作废 5-已完成  6-交易关闭")
	private Integer order_state;
	
	@ApiModelProperty(value="0-未支付   1-已支付  2-已退款 3支付成功")
	private Integer pay_state;
	
	@ApiModelProperty(value="0-待配货  1-待发货  2-待取货 3-已发货 4-已取货  5-已确认收货 6-发货成功")
	private Integer logistics_state;
	
	@ApiModelProperty(value="1-待财务确认  2-财务确认已付款 3-财务确认未付款 4-待系统确认 5-系统确认有效 6-系统确认无效")
	private Integer confirm_state;
	
	@ApiModelProperty(value="1-在线支付  2-货到付款")
	private Integer pay_way;
	
	@ApiModelProperty(value="1-物流  2-自取")
	private Integer logistics_way;
	
	@ApiModelProperty(value="下单时间")
	private java.sql.Timestamp order_time;
	
	@ApiModelProperty(value="支付编码")
	private String payment_code;
	
	@ApiModelProperty(value="1-内部订单 2- 外部订单")
	private Integer source;
	
	@ApiModelProperty(value="备注")
	private String remark;
	
	@ApiModelProperty(value="创建时间")
	private java.sql.Timestamp create_time;
	
	@ApiModelProperty(value="更新时间")
	private java.sql.Timestamp update_time;
	
	@ApiModelProperty(value="操作人id")
	private Long operator_id;
	
	@ApiModelProperty(value="锁定状态")
	private Integer lock_state;
	
	@ApiModelProperty(value="锁定人ID")
	private Long lock_user_id;
	
	@ApiModelProperty(value="锁定时间")
	private java.sql.Timestamp lock_time;
	
	@ApiModelProperty(value="登录记录ID")
	private Long record_id;
	
	
	/**
	* default val cols name array
	*/	
	private static String[] defaultValColArr = {
	};
	
	/**
	* pk cols name array
	*/	
	private static String[] pkColArr = {
	  	"order_id"
	};
	
	private static String[] columnNameArr = {
		"order_id",
		"parent_order_id",
		"payment_id",
		"order_code",
		"member_id",
		"goods_total_money",
		"logistics_money",
		"preferential_money",
		"total_money",
		"order_state",
		"pay_state",
		"logistics_state",
		"confirm_state",
		"pay_way",
		"logistics_way",
		"order_time",
		"payment_code",
		"source",
		"remark",
		"create_time",
		"update_time",
		"operator_id",
		"lock_state",
		"lock_user_id",
		"lock_time",
		"record_id"
	};
  
	public Long getOrder_id () {
		return order_id;
	}
	
	public void setOrder_id (Long obj) {
		order_id = obj;
	}
	
	public Long getParent_order_id () {
		return parent_order_id;
	}
	
	public void setParent_order_id (Long obj) {
		parent_order_id = obj;
	}
	
	public Long getPayment_id () {
		return payment_id;
	}
	
	public void setPayment_id (Long obj) {
		payment_id = obj;
	}
	
	public String getOrder_code () {
		return order_code;
	}
	
	public void setOrder_code (String obj) {
		order_code = obj;
	}
	
	public Long getMember_id () {
		return member_id;
	}
	
	public void setMember_id (Long obj) {
		member_id = obj;
	}
	
	public Long getGoods_total_money () {
		return goods_total_money;
	}
	
	public void setGoods_total_money (Long obj) {
		goods_total_money = obj;
	}
	
	public Long getLogistics_money () {
		return logistics_money;
	}
	
	public void setLogistics_money (Long obj) {
		logistics_money = obj;
	}
	
	public Long getPreferential_money () {
		return preferential_money;
	}
	
	public void setPreferential_money (Long obj) {
		preferential_money = obj;
	}
	
	public Long getTotal_money () {
		return total_money;
	}
	
	public void setTotal_money (Long obj) {
		total_money = obj;
	}
	
	public Integer getOrder_state () {
		return order_state;
	}
	
	public void setOrder_state (Integer obj) {
		order_state = obj;
	}
	
	public Integer getPay_state () {
		return pay_state;
	}
	
	public void setPay_state (Integer obj) {
		pay_state = obj;
	}
	
	public Integer getLogistics_state () {
		return logistics_state;
	}
	
	public void setLogistics_state (Integer obj) {
		logistics_state = obj;
	}
	
	public Integer getConfirm_state () {
		return confirm_state;
	}
	
	public void setConfirm_state (Integer obj) {
		confirm_state = obj;
	}
	
	public Integer getPay_way () {
		return pay_way;
	}
	
	public void setPay_way (Integer obj) {
		pay_way = obj;
	}
	
	public Integer getLogistics_way () {
		return logistics_way;
	}
	
	public void setLogistics_way (Integer obj) {
		logistics_way = obj;
	}
	
	public java.sql.Timestamp getOrder_time () {
		return order_time;
	}
	
	public void setOrder_time (java.sql.Timestamp obj) {
		order_time = obj;
	}
	
	public String getPayment_code () {
		return payment_code;
	}
	
	public void setPayment_code (String obj) {
		payment_code = obj;
	}
	
	public Integer getSource () {
		return source;
	}
	
	public void setSource (Integer obj) {
		source = obj;
	}
	
	public String getRemark () {
		return remark;
	}
	
	public void setRemark (String obj) {
		remark = obj;
	}
	
	public java.sql.Timestamp getCreate_time () {
		return create_time;
	}
	
	public void setCreate_time (java.sql.Timestamp obj) {
		create_time = obj;
	}
	
	public java.sql.Timestamp getUpdate_time () {
		return update_time;
	}
	
	public void setUpdate_time (java.sql.Timestamp obj) {
		update_time = obj;
	}
	
	public Long getOperator_id () {
		return operator_id;
	}
	
	public void setOperator_id (Long obj) {
		operator_id = obj;
	}
	
	public Integer getLock_state () {
		return lock_state;
	}
	
	public void setLock_state (Integer obj) {
		lock_state = obj;
	}
	
	public Long getLock_user_id () {
		return lock_user_id;
	}
	
	public void setLock_user_id (Long obj) {
		lock_user_id = obj;
	}
	
	public java.sql.Timestamp getLock_time () {
		return lock_time;
	}
	
	public void setLock_time (java.sql.Timestamp obj) {
		lock_time = obj;
	}
	
	public Long getRecord_id () {
		return record_id;
	}
	
	public void setRecord_id (Long obj) {
		record_id = obj;
	}
	
	
	/**
	* put all columns into a map
	*/
	public void putInMap(Map<String, Object> paramMap) {
		paramMap.put("order_id", this.order_id);
		paramMap.put("parent_order_id", this.parent_order_id);
		paramMap.put("payment_id", this.payment_id);
		paramMap.put("order_code", this.order_code);
		paramMap.put("member_id", this.member_id);
		paramMap.put("goods_total_money", this.goods_total_money);
		paramMap.put("logistics_money", this.logistics_money);
		paramMap.put("preferential_money", this.preferential_money);
		paramMap.put("total_money", this.total_money);
		paramMap.put("order_state", this.order_state);
		paramMap.put("pay_state", this.pay_state);
		paramMap.put("logistics_state", this.logistics_state);
		paramMap.put("confirm_state", this.confirm_state);
		paramMap.put("pay_way", this.pay_way);
		paramMap.put("logistics_way", this.logistics_way);
		paramMap.put("order_time", this.order_time);
		paramMap.put("payment_code", this.payment_code);
		paramMap.put("source", this.source);
		paramMap.put("remark", this.remark);
		paramMap.put("create_time", this.create_time);
		paramMap.put("update_time", this.update_time);
		paramMap.put("operator_id", this.operator_id);
		paramMap.put("lock_state", this.lock_state);
		paramMap.put("lock_user_id", this.lock_user_id);
		paramMap.put("lock_time", this.lock_time);
		paramMap.put("record_id", this.record_id);
	}
	
	/**
	* return the columns map
	*/
	public Map<String, Object> toInfoMap() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		this.putInMap(paramMap);
		return paramMap;
	}
	
	/**
	* remove default value and pk if it is null
	*/
	private Map<String, Object> dealWithMap(Map<String, Object> paramMap) {
		Set<String> set = new HashSet<String>();
		for (String colName : defaultValColArr) {
			set.add(colName);
		}
		for (String colName : pkColArr) {
			set.add(colName);
		}
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			String colName = iterator.next();
			if(paramMap.get(colName) == null) {
				paramMap.remove(colName);
			}
		}
		return paramMap;
	}
}
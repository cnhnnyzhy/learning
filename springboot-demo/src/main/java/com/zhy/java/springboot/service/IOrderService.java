package com.zhy.java.springboot.service;

import com.zhy.java.springboot.model.OrdInfo;

import java.util.List;
import java.util.Map;

public interface IOrderService {
    List<Map<String, Object>> getOrderList(Map<String, Object> params);

    /**
     * 保存订单信息
     * @param ordInfo
     */
    void saveOrder(OrdInfo ordInfo);
}

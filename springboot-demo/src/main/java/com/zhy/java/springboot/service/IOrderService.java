package com.zhy.java.springboot.service;

import java.util.List;
import java.util.Map;

public interface IOrderService {
    public List<Map<String, Object>> getOrderList(Map<String, Object> params);
}

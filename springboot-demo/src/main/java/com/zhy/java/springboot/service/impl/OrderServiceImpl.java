package com.zhy.java.springboot.service.impl;

import com.zhy.java.springboot.dao.IOrdInfoDao;
import com.zhy.java.springboot.model.OrdInfo;
import com.zhy.java.springboot.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrdInfoDao ordInfoDao;
    @Override
    public List<Map<String, Object>> getOrderList(Map<String, Object> params) {
        return ordInfoDao.search(params);
    }

    @Override
    @Transactional
    public void saveOrder(OrdInfo ordInfo) {
        ordInfoDao.save(ordInfo);
        throw new RuntimeException("保存失败了！");
    }
}

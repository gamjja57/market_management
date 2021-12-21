package com.gamjja.market_management.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gamjja.market_management.mapper.DashboardMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    @Autowired DashboardMapper mapper;

    public Map<String,Object> getCounts() {
        List<Integer> productCntList = new ArrayList<Integer>();
        productCntList.add(mapper.getTotalProductCnt());
        productCntList.add(mapper.getSellProductCnt());
        productCntList.add(mapper.getSoldoutProductCnt());
        productCntList.add(mapper.getDeleteProductCnt());

        List<Integer> rocket_productCntList = new ArrayList<Integer>();
        rocket_productCntList.add(mapper.getTotalRocketCnt());
        rocket_productCntList.add(mapper.getSellRocketCnt());
        rocket_productCntList.add(mapper.getSoldoutRocketCnt());
        rocket_productCntList.add(mapper.getDeleteRocketCnt());


        List<Integer> customerCntList = new ArrayList<Integer>();
        customerCntList.add(mapper.getTotalCustomerCnt());
        customerCntList.add(mapper.getNomalCustomerCnt());
        customerCntList.add(mapper.getRocketCustomerCnt());
        customerCntList.add(mapper.getNomal2CustomerCnt());
        customerCntList.add(mapper.getApplyLeaveCustomerCnt());
        customerCntList.add(mapper.getLeaveCustomerCnt());


        List<Integer> orderCntList = new ArrayList<Integer>();
        orderCntList.add(mapper.getTotalOrderCnt());
        orderCntList.add(mapper.getReturnOrderCnt());
        orderCntList.add(mapper.getExchangeOrderCnt());
        orderCntList.add(mapper.getDecideOrderCnt());


        List<Integer> rocket_orderCntList = new ArrayList<Integer>();
        rocket_orderCntList.add(mapper.getTotalRocketOrderCnt());
        rocket_orderCntList.add(mapper.getReturnRocketOrderCnt());
        rocket_orderCntList.add(mapper.getExchangeRocketOrderCnt());
        rocket_orderCntList.add(mapper.getDecideRocketOrderCnt());
    
        
        List<Integer> eventCntList = new ArrayList<Integer>();
        eventCntList.add(mapper.getTotalEventCnt());
        eventCntList.add(mapper.getActiveEventCnt());
        eventCntList.add(mapper.getDeactiveEventCnt());
        

        Map<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("product", productCntList);
        map.put("rocket_product", rocket_productCntList);
        map.put("customer", customerCntList);
        map.put("order", orderCntList);
        map.put("rocket_order", rocket_orderCntList);
        map.put("event", eventCntList);
        return map;
    }
    public Map<String, Object> getUpdateDate()  {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        resultMap.put("member", mapper.getMemberUpdateDate());
        resultMap.put("product", mapper.getProductUpdateDate());

        return resultMap;
    }
}







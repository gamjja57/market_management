package com.gamjja.market_management.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DashboardMapper {
    public Integer getTotalProductCnt();
    public Integer getSellProductCnt();
    public Integer getSoldoutProductCnt();
    public Integer getDeleteProductCnt();


    public Integer getTotalRocketCnt();
    public Integer getSellRocketCnt();
    public Integer getSoldoutRocketCnt();
    public Integer getDeleteRocketCnt();

    public Integer getTotalCustomerCnt();
    public Integer getNomalCustomerCnt();
    public Integer getRocketCustomerCnt();
    public Integer getNomal2CustomerCnt();
    public Integer getApplyLeaveCustomerCnt();
    public Integer getLeaveCustomerCnt();

    public Integer getLevelCustomer1();
    public Integer getLevelCustomer2();

    public Integer getTotalOrderCnt();
    public Integer getReturnOrderCnt();
    public Integer getExchangeOrderCnt();
    public Integer getDecideOrderCnt();

    public Integer getTotalRocketOrderCnt();
    public Integer getReturnRocketOrderCnt();
    public Integer getExchangeRocketOrderCnt();
    public Integer getDecideRocketOrderCnt();

    public Integer getTotalEventCnt();
    public Integer getActiveEventCnt();
    public Integer getDeactiveEventCnt();


    public Date getMemberUpdateDate();
    public Date getProductUpdateDate();
}

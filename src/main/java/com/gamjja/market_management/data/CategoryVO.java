package com.gamjja.market_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class CategoryVO {
    private Integer ci_seq;
    private String ci_name;
    private String ci_sub;
    private Integer ci_status;
    private Date ci_reg_dt;
}

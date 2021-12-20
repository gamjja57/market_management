package com.gamjja.market_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class ProductVO {
    private Integer pi_seq;
    private Integer pi_ci_seq;
    private Integer pi_r_seq;
    private Integer pi_num;
    private String pi_name;
    private Integer pi_price;
    private String pi_introduce;
    private Integer pi_stock;
    private Date pi_reg_dt;
    private Date pi_mod_dt;
    private Integer pi_status;


    private String category_name;

}

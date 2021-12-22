package com.gamjja.market_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class RocketProductVO {
    private Integer rpi_seq;
    private Integer rpi_ci_seq;
    private Integer rpi_r_seq;
    private String rpi_name;
    private Integer rpi_price;
    private Integer rpi_status;
    private String rpi_image;
    private Integer rpi_stock;
    private String rpi_introduce;
    private Date rpi_reg_dt;
    private Date rpi_mod_dt;


    private String category_name;

}

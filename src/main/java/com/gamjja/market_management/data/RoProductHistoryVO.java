package com.gamjja.market_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class RoProductHistoryVO {
    private Integer rih_seq;
    private Integer rih_rpi_seq;
    private String rih_type;
    private String rih_content;
    private Date rih_reg_dt;
}

package com.gamjja.market_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class MemberHistoryVO {
    private Integer mh_seq;
    private String mh_type;
    private String mh_content;
    private Date mh_reg_dt;
    private Integer mh_c_seq;
}

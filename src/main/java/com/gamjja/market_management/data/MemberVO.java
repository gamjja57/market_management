package com.gamjja.market_management.data;

import java.util.Date;

import lombok.Data;

@Data
public class MemberVO {
    private Integer c_seq;
    private String c_name;
    private String c_id;
    private String c_pwd;
    private String c_email;
    private String c_birth;
    private Integer c_status;
    private Integer c_gen;
    private Date c_reg_dt;
    private Date c_mod_dt;
}

package com.gamjja.market_management.mapper;

import java.util.List;

import com.gamjja.market_management.data.MemberHistoryVO;
import com.gamjja.market_management.data.MemberVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public List<MemberVO> getMemberInfo(Integer offset, String keyword);

    public Integer getMemberCount();

    public void addMember(MemberVO data);

    public void deleteMember(Integer seq);

    public MemberVO getMemberInfoBySeq(Integer seq);


    public void updateMember(MemberVO data);

    public Integer getMemberCount(String keyword);

    public Integer selectLatestDataSeq();
    public Integer insertMemberHistory(MemberHistoryVO data);



    // public MemberVO getLevelCustomerSeparate1(Integer seq);
    // public MemberVO getLevelCustomerSeparate2(Integer seq);



}

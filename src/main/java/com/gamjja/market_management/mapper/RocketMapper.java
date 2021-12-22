package com.gamjja.market_management.mapper;

import java.util.List;

import com.gamjja.market_management.data.CategoryVO;
import com.gamjja.market_management.data.RocketProductVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RocketMapper {
    public void addRoProductInfo(RocketProductVO data);

    public List<RocketProductVO> getRoProductList(String type, String keyword, Integer offset);
    public Integer getRoProductCnt(String type, String keyword);
    
    public List<CategoryVO> getRoProductByKeyword(String keyword);


    public void deleteRoProductInfo(Integer seq);
    public Integer isExistRoProduct(Integer seq); //삭제 실패여부 알려주기


    public RocketProductVO getRoProductInfoBySeq (Integer seq);
    public void updateRoProductInfo(RocketProductVO data);    
}

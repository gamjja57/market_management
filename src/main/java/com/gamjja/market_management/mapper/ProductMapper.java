package com.gamjja.market_management.mapper;

import java.util.List;

import com.gamjja.market_management.data.CategoryVO;
import com.gamjja.market_management.data.ProductVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    public void addProductInfo(ProductVO data);

    public List<ProductVO> getProductList(String type, String keyword, Integer offset);
    public Integer getProductCnt(String type, String keyword);
    
    public List<CategoryVO> getProductByKeyword(String keyword);
}
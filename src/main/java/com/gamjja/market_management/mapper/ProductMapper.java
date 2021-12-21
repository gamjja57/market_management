package com.gamjja.market_management.mapper;

import java.util.List;

import com.gamjja.market_management.data.CategoryVO;
import com.gamjja.market_management.data.ProductHistoryVO;
import com.gamjja.market_management.data.ProductVO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    public void addProductInfo(ProductVO data);

    public List<ProductVO> getProductList(String type, String keyword, Integer offset);
    public Integer getProductCnt(String type, String keyword);
    
    public List<CategoryVO> getProductByKeyword(String keyword);


    public void deleteProductInfo(Integer seq);
    public Integer isExistProduct(Integer seq); //삭제 실패여부 알려주기


    public ProductVO getProductInfoBySeq (Integer seq);
    public void updateProductInfo(ProductVO data);



    /* 히스토리 */
    public void insertProductHistory(ProductHistoryVO data);
    public Integer getRecentAddedProductSeq(); 
}

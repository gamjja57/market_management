package com.gamjja.market_management.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gamjja.market_management.data.CategoryVO;
import com.gamjja.market_management.data.ProductVO;
import com.gamjja.market_management.mapper.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired ProductMapper mapper;

    public Map<String, Object> addProductInfo(ProductVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        mapper.addProductInfo(data);

        resultMap.put("status", true);
        resultMap.put("message", "제품 정보가 추가되었습니다.");
        return resultMap;
    }


    public Map<String, Object> getProductList(String type, String keyword, Integer offset)  {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(keyword == null) {
            resultMap.put("keyword", keyword);
            keyword = "%%";
        }

        else {
        resultMap.put("keyword", keyword);
        keyword = "%"+keyword+"%";
        }

        resultMap.put("type", type);

        if(offset == null) offset=0;

        List<ProductVO> list = mapper.getProductList(type, keyword, offset);
        Integer cnt = mapper.getProductCnt(type, keyword);

        Integer page = cnt/10;
        if(page%10 >0) page++;

        resultMap.put("status", true);
        resultMap.put("pageCnt", page);
        resultMap.put("list", list);

        return resultMap;
    }

    public Map<String, Object> getProductByKeyword(String keyword) {
        Map<String, Object> resultMap= new LinkedHashMap<String, Object>();

        if(keyword == null) keyword = "%%";
        keyword = "%"+keyword+"%";

        List<CategoryVO> list = mapper.getProductByKeyword(keyword);

        resultMap.put("status", true);
        resultMap.put("list", list);
        return resultMap;
    }

}

package com.gamjja.market_management.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gamjja.market_management.data.CategoryVO;
import com.gamjja.market_management.data.ProductHistoryVO;
import com.gamjja.market_management.data.ProductVO;
import com.gamjja.market_management.mapper.ProductMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired ProductMapper mapper;

    public Map<String, Object> addProductInfo(ProductVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();


        ProductHistoryVO history = new ProductHistoryVO();
        history.setPih_type("new");
        history.setPih_content(data.makeHistoryStr());
        Integer recent_seq = mapper.getRecentAddedProductSeq();
        history.setPih_pi_seq(recent_seq);

        mapper.insertProductHistory(history);

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

    public Map<String,Object>deleteProductInfo(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        Integer cnt = mapper.isExistProduct(seq);
        if(cnt == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "삭제에 실패했습니다. (존재하지 않는 제품 정보)");
        }
        else {
            mapper.deleteProductInfo(seq);
            resultMap.put("status", true);
            resultMap.put("message", "삭제 했습니다.");

            ProductHistoryVO history = new ProductHistoryVO();
            history.setPih_type("delete");
            history.setPih_pi_seq(seq);
            mapper.insertProductHistory(history);
        }
        return resultMap;
    }
    
    public ProductVO getProductInfoBySeq(Integer seq) {
        return mapper.getProductInfoBySeq(seq);
    }
    public Map<String, Object> patchProductInfo(ProductVO data)  {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        mapper.updateProductInfo(data);

        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");

        ProductHistoryVO history = new ProductHistoryVO();
        history.setPih_type("modify");
        history.setPih_content(data.makeHistoryStr());
        history.setPih_pi_seq(data.getPi_seq());

        mapper.insertProductHistory(history);

        return resultMap;
    }
}


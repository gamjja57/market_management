package com.gamjja.market_management.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gamjja.market_management.data.CategoryVO;
import com.gamjja.market_management.data.RoProductHistoryVO;
import com.gamjja.market_management.data.RocketProductVO;
import com.gamjja.market_management.mapper.RocketMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RocketProductService {
    @Autowired RocketMapper mapper;

    public Map<String, Object> addRoProductInfo(RocketProductVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        RoProductHistoryVO history = new RoProductHistoryVO();
        history.setRih_type("new");
        history.setRih_content(data.makeHistoryStr());
        Integer recent_seq = mapper.getRecentAddedRoProductSeq();
        history.setRih_rpi_seq(recent_seq);

        mapper.insertRoProductHistory(history);

        mapper.addRoProductInfo(data);

        resultMap.put("status", true);
        resultMap.put("message", "제품 정보가 추가되었습니다.");
        return resultMap;
    }


    public Map<String, Object> getRoProductList(String type, String keyword, Integer offset)  {
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

        List<RocketProductVO> list = mapper.getRoProductList(type, keyword, offset);
        Integer cnt = mapper.getRoProductCnt(type, keyword);

        Integer page = cnt/10;
        if(cnt%10 >0) page++;

        resultMap.put("status", true);
        resultMap.put("pageCnt", page);
        resultMap.put("list", list);

        return resultMap;
    }



    public Map<String, Object> getRoProductByKeyword(String keyword) {
        Map<String, Object> resultMap= new LinkedHashMap<String, Object>();

        if(keyword == null) keyword = "%%";
        keyword = "%"+keyword+"%";

        List<CategoryVO> list = mapper.getRoProductByKeyword(keyword);

        resultMap.put("status", true);
        resultMap.put("list", list);
        return resultMap;
    }

    public Map<String,Object>deleteRoProductInfo(Integer seq) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        Integer cnt = mapper.isExistRoProduct(seq);
        if(cnt == 0) {
            resultMap.put("status", false);
            resultMap.put("message", "삭제에 실패했습니다. (존재하지 않는 제품 정보)");
        }
        else {
            mapper.deleteRoProductInfo(seq);
            resultMap.put("status", true);
            resultMap.put("message", "삭제 했습니다.");

            RoProductHistoryVO history = new RoProductHistoryVO();
            history.setRih_type("delete");
            history.setRih_rpi_seq(seq);
            mapper.insertRoProductHistory(history);
        }
        return resultMap;
    }
    


    public RocketProductVO getRoProductInfoBySeq(Integer seq) {
        return mapper.getRoProductInfoBySeq(seq);
    }
    public Map<String, Object> patchRoProductInfo(RocketProductVO data)  {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        
        mapper.updateRoProductInfo(data);

        resultMap.put("status", true);
        resultMap.put("message", "수정되었습니다.");

        RoProductHistoryVO history = new RoProductHistoryVO();
        history.setRih_type("modify");
        history.setRih_content(data.makeHistoryStr());
        history.setRih_rpi_seq(data.getRpi_seq());
        mapper.insertRoProductHistory(history);

        return resultMap;
    }
}

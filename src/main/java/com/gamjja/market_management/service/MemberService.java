package com.gamjja.market_management.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gamjja.market_management.data.MemberVO;
import com.gamjja.market_management.mapper.MemberMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired MemberMapper mapper;

    public Map<String, Object> getMemberList(Integer offset) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        List<MemberVO> list = mapper.getMemberInfo(offset);

        Integer cnt = mapper.getMemberCount();

        Integer page_cnt = cnt/10+(cnt%10>0?1:0);


        resultMap.put("status", true);
        resultMap.put("total", cnt);
        resultMap.put("pageCnt", page_cnt);
        resultMap.put("list", list);
        return resultMap;
    }

    public Map<String, Object> addMember(MemberVO data) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        if(data.getC_name() == null || data.getC_name().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "회원 이름을 입력하세요.");
            return resultMap;
        }

        if(data.getC_id() == null || data.getC_id().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "회원 아이디를 입력하세요.");
            return resultMap;
        }

        if(data.getC_email() == null || data.getC_email().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "회원 이메일을 입력하세요.");
            return resultMap;
        }

        if(data.getC_birth() == null || data.getC_birth().equals("")) {
            resultMap.put("status", false);
            resultMap.put("message", "회원 생년월일을 입력하세요.");
            return resultMap;
        }

        mapper.addMember(data);

        resultMap.put("status", true);
        resultMap.put("message", "회원이 추가되었습니다.");

        return resultMap;
    }

    public Map<String, Object> deleteMember(Integer seq) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteMember(seq);
        resultMap.put("status", true);
        resultMap.put("message", "회원이 삭제되었습니다.");
        return resultMap;
    }
}

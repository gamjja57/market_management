package com.gamjja.market_management.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gamjja.market_management.data.MemberHistoryVO;
import com.gamjja.market_management.data.MemberVO;
import com.gamjja.market_management.mapper.MemberMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired MemberMapper mapper;

    public Map<String, Object> getMemberList(Integer offset, String keyword) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(offset == null) {
            offset=0;
            resultMap.put("offset", offset);
        }

        if(keyword == null) {
            keyword="%%";
            resultMap.put("keyword", "");
        }

        else {
            resultMap.put("keyword", keyword);
            keyword = "%"+keyword+"%";
        }

        List<MemberVO> list = mapper.getMemberInfo(offset, keyword);

        Integer cnt = mapper.getMemberCount(keyword);

        Integer page_cnt = cnt / 10;
        if(cnt%10 >0) page_cnt++;


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

        Integer seq = mapper.selectLatestDataSeq();

        MemberHistoryVO history = new MemberHistoryVO();
        history.setMh_c_seq(seq);
        history.setMh_type("new");
        String content = data.getC_name()+" | "+data.getC_id()+
        " | "+data.getC_pwd()+" | "+data.getC_email()+" | "+data.getC_birth()
        +" | "+data.getC_gen()+" | "+data.getC_status();
        history.setMh_content(content);
        mapper.insertMemberHistory(history);

        return resultMap;
    }

    public Map<String, Object> deleteMember(Integer seq) {
        Map<String,Object> resultMap = new LinkedHashMap<String, Object>();
        mapper.deleteMember(seq);
        resultMap.put("status", true);
        resultMap.put("message", "회원이 삭제되었습니다.");

        MemberHistoryVO history = new MemberHistoryVO();
        history.setMh_c_seq(seq);
        history.setMh_type("delete");
        // String content = data.getC_name()+" | "+data.getC_id()+
        // " | "+data.getC_pwd()+" | "+data.getC_email()+" | "+data.getC_birth()
        // +" | "+data.getC_gen()+" | "+data.getC_status();
        // history.setMh_content(content);
        mapper.insertMemberHistory(history);

        return resultMap;
    }

    public Map<String, Object> getMemberInfoBySeq(Integer seq) {
        Map<String, Object> resultMap= new LinkedHashMap<String, Object>();
        resultMap.put("status", true);
        resultMap.put("data", mapper.getMemberInfoBySeq(seq));
        return resultMap;
    }

public Map<String, Object> updateMemberInfo(MemberVO data) {
    Map<String, Object> resultMap= new LinkedHashMap<String, Object>();
    
    mapper.updateMember(data);
            
    resultMap.put("status", true);
    resultMap.put("message", "수정되었습니다.");

    MemberHistoryVO history = new MemberHistoryVO();
    history.setMh_c_seq(data.getC_seq());
    history.setMh_type("update");
    String content = data.getC_name()+" | "+data.getC_id()+
    " | "+data.getC_pwd()+" | "+data.getC_email()+" | "+data.getC_birth()
    +" | "+data.getC_gen()+" | "+data.getC_status();
    history.setMh_content(content);
    mapper.insertMemberHistory(history);

    return resultMap;
    }
}

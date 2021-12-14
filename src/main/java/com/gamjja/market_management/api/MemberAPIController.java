package com.gamjja.market_management.api;

import java.util.Map;

import com.gamjja.market_management.data.MemberVO;
import com.gamjja.market_management.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberAPIController {
    @Autowired MemberService service;

    @PostMapping("/member/add")
    public Map<String, Object> postMemberAdd(@RequestBody MemberVO data) {
        return service.addMember(data);
    }

    @DeleteMapping("/member/delete")
    public Map<String, Object> deleteDepartment(@RequestParam Integer seq) {
        return service.deleteMember(seq);
    }

    @GetMapping("/member/get")
    public Map<String, Object> getMemberInfoBySeq(@RequestParam Integer seq) {
        return service.getMemberInfoBySeq(seq);
    }

    @PatchMapping("/member/update")
    public Map<String, Object> patchMemberInfo(@RequestBody MemberVO data) {
        return service.updateMemberInfo(data);
    }
}

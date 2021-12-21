package com.gamjja.market_management.controller;

import java.util.Map;

import com.gamjja.market_management.service.MemberService;
import com.gamjja.market_management.utils.AESAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberController {
    @Autowired MemberService service;


    @GetMapping("/member")
    public String getMember(Model model, @RequestParam @Nullable Integer offset, @RequestParam @Nullable String keyword)  throws Exception {

        if(offset ==  null) offset=0;
        Map<String, Object> resultMap = service.getMemberList(offset, keyword);
        model.addAttribute("data", resultMap);

        if(keyword != null)
        System.out.println(AESAlgorithm.Encrypt(keyword));
        
        return "/member/list";
    }

    
}

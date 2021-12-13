package com.gamjja.market_management.controller;

import java.util.Map;

import com.gamjja.market_management.service.MemberService;

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
    public String getMember(Model model, @RequestParam @Nullable Integer offset) {
        if(offset ==  null) offset=0;
        Map<String, Object> resultMap = service.getMemberList(offset);
        model.addAttribute("data", resultMap);
        
        return "/member/list";
    }
}

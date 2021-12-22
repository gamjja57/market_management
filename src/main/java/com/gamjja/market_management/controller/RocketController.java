package com.gamjja.market_management.controller;

import com.gamjja.market_management.service.RocketProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RocketController {
    @Autowired RocketProductService service;

    @GetMapping("/rocket/product")
        public String getRocketProduct(Model model,
    @RequestParam @Nullable String type,
    @RequestParam @Nullable String keyword,
    @RequestParam @Nullable Integer offset)
    {
        model.addAttribute("data", service.getRoProductList(type, keyword, offset));
        return "/product/R_list";
    }
}

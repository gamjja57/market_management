package com.gamjja.market_management.api;

import java.util.Map;

import com.gamjja.market_management.data.RocketProductVO;
import com.gamjja.market_management.service.RocketProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RocketProductAPIController {
    @Autowired RocketProductService service;

    @PostMapping("/rocket/product/add")
    public Map<String, Object> postRocketRocketProductAdd(@RequestBody RocketProductVO data) {
        return service.addRoProductInfo(data);
    }

    @GetMapping("/rocket/product/keyword")
    public Map<String, Object> getRoProductByKeyword(@RequestParam @Nullable String keyword) {
        return service.getRoProductByKeyword(keyword);
    }

    @DeleteMapping("/rocket/product/delete")
    public Map<String, Object> deleteRocketProductInfo(@RequestParam Integer seq) throws Exception {
        return service.deleteRoProductInfo(seq);
    }

    @GetMapping("/rocket/product/get")
    public RocketProductVO  getRocketProductInfoBySeq(@RequestParam Integer seq)  {
        return service.getRoProductInfoBySeq(seq);
    }

    @PatchMapping("/rocket/product/modify")
    public Map<String, Object> patchRoProductInfo(@RequestBody RocketProductVO data)  {
        return service.patchRoProductInfo(data);
    }

}

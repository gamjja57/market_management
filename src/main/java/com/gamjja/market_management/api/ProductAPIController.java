package com.gamjja.market_management.api;

import java.util.Map;

import com.gamjja.market_management.data.ProductVO;
import com.gamjja.market_management.service.ProductService;

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
public class ProductAPIController {
    @Autowired ProductService service;

    @PostMapping("/product/add")
    public Map<String, Object> postTeacherAdd(@RequestBody ProductVO data) {
        return service.addProductInfo(data);
    }

    @GetMapping("/product/keyword")
    public Map<String, Object> getDepartmentByKeyword(@RequestParam @Nullable String keyword) {
        return service.getProductByKeyword(keyword);
    }

    @DeleteMapping("/product/delete")
    public Map<String, Object> deleteProductInfo(@RequestParam Integer seq) throws Exception {
        return service.deleteProductInfo(seq);
    }

    @GetMapping("/product/get")
    public ProductVO  getProductInfoBySeq(@RequestParam Integer seq)  {
        return service.getProductInfoBySeq(seq);
    }

    @PatchMapping("/product/modify")
    public Map<String, Object> patchProductInfo(@RequestBody ProductVO data)  {
        return service.patchProductInfo(data);
    }
}

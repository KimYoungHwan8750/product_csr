package com.kh.product.Controller.ProductCRUD;

import com.kh.product.Service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search_all_page")
@RequiredArgsConstructor
@Slf4j
public class search_all {
    private final ProductService productService;
    @PostMapping("")
    public String searchAll(Model model){
        model.addAttribute("product",productService.findAll());
        return "search_all/search_all.html";
    }
//    @PatchMapping("/")
}

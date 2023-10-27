package com.kh.product.Controller.ProductCRUD;

import com.kh.product.DAO.JPARepository.ProductRepository;
import com.kh.product.DTO.ProductDTO;
import com.kh.product.Entity.Product;
import com.kh.product.Service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/search_page")
@Slf4j
@RequiredArgsConstructor
public class search {
    private final ProductService productService;
    @GetMapping("/id={pname}")
    public String getProductByPname(@PathVariable String pname, Model model){
        log.info(pname);
            List<Product> product = productService.SearchToDo(pname);
            model.addAttribute("product",product);
            model.addAttribute("pname",pname);

        return "/search/search.html";
    }
}

package com.kh.product.Controller.ProductCRUD;

import com.kh.product.DAO.JPARepository.ProductRepository;
import com.kh.product.Entity.Product;
import com.kh.product.Exception.MyException;
import com.kh.product.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TypeMismatchException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/delete_page")
@RequiredArgsConstructor
@Slf4j
public class delete {
    private final ProductService productService;
    @DeleteMapping("/id={pid}")
    public String deleteByPid(@PathVariable(required = false) String pid, Model model){
        if(pid.isEmpty()){
            model.addAttribute("inputStatement","값을 입력해주세요.");
            return "delete/delete.html";
        }
        Long Lpid;
        try{
            Lpid = Long.parseLong(pid);}
        catch (NumberFormatException e){
            model.addAttribute("inputStatement","숫자를 입력해주세요.");
            return "delete/delete.html";
        }

        model.addAttribute("product",productService.FindByPid(Lpid));

        Boolean isSuccess = productService.DeleteByPid(Lpid)==1;

        model.addAttribute("isSuccess",isSuccess);
        return "delete/delete.html";
    }
}

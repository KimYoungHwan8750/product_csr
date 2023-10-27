package com.kh.product.Controller.ProductCRUD;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.kh.product.DTO.ProductDTO;
import com.kh.product.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Optional;

@Controller
@RequestMapping("/insert_page")
@RequiredArgsConstructor
@Slf4j
public class Insert {
    private final ProductService productService;
    @PutMapping("/insert_do")
    public String productInsert(@Valid @ModelAttribute("productDTO") ProductDTO productDTO, BindingResult bindingResult, Model model){



        if(productDTO.getPrice()==null){
            bindingResult.rejectValue("price","nullchk",null);

//                bindingResult.rejectValue("price","range",new Object[]{1000},null);
        }
        if(productDTO.getPname().isEmpty()){
            bindingResult.rejectValue("pname","nullchk",null);
        }
        if(productDTO.getQuantity()==null){
            bindingResult.rejectValue("quantity","nullchk",null);
        }
        if(!bindingResult.hasFieldErrors("price")){
            if(productDTO.getPrice()<1000&&productDTO.getPrice()>0) {
                bindingResult.rejectValue("price", "range", new Object[]{1000}, null);
            } else if (productDTO.getPrice()<=0){
                bindingResult.rejectValue("price","positive",null);

            }
        }
        if(!bindingResult.hasFieldErrors("quantity")){
            if(productDTO.getQuantity()<1) {
                bindingResult.rejectValue("quantity", "range", null);
            }
        }

        if(!bindingResult.hasFieldErrors()){
            productService.InsertToDo(productDTO);
            model.addAttribute("hasError",false);


        } else{

            model.addAttribute("hasError",true);
        }
        return "insert/insert.html";

    }
}

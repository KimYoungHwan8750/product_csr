package com.kh.product.Controller.Main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.kh.product.DAO.JPARepository.ProductRepository;
import com.kh.product.DTO.ProductDTO;
import com.kh.product.Entity.Product;
import com.kh.product.Service.ProductService;
import com.kh.product.api.APIResponse;
import jakarta.transaction.TransactionalException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
public class MainToProduct {
    private final ProductService productService;
    private final ProductRepository productRepository;

    //저장
    @PutMapping("/product")
    public APIResponse<ProductDTO> PutProduct(@ModelAttribute ProductDTO productDTO,BindingResult bindingResult) {
        try {
            if(productDTO.isEmpty()){

                return new APIResponse<>(productDTO).isFail().setMessage("입력을 다시 확인해주세요.");

            } else if(productDTO.getPrice()<1000L) {

                return new APIResponse<>(productDTO).isFail().setMessage("1000원 이상 입력해주세요.");

            } else if(productDTO.getQuantity()<1){
                return new APIResponse<>(productDTO).isFail().setMessage("갯수를 하나 이상 입력해주세요.");
            } else if(productDTO.getPrice()*productDTO.getQuantity()>100_000_000L) {
                return new APIResponse<>(productDTO).isFail().setMessage("가격 * 수량이 1억을 넘습니다.");

            }


            productService.InsertToDo(productDTO);

        } catch (Exception e){

            return new APIResponse<>(productDTO).isFail().setMessage("올바른 값을 입력해주세요. 입력한 값 : "+productDTO.toString());

        }

    return new APIResponse<>(productDTO).isSuccess().setMessage("저장이 완료되었습니다.");
    }


    //조회
    @GetMapping("/product/{pname}")
    public APIResponse<List<Product>> GetProduct(@PathVariable String pname){

        List<Product> emptyArr = new ArrayList<>();
        List<Product> result;
        try {
            result= productService.SearchToDo(pname);
            if(pname.isEmpty()){
                return new APIResponse<>(emptyArr).isFail().setMessage("값을 입력해주세요.");
            } else if (result.isEmpty()){
                return new APIResponse<>(emptyArr).isSuccess().setMessage("조회 결과가 없습니다.");
            };

        } catch (Exception e){
            return new APIResponse<>(emptyArr).isFail().setMessage("상품 조회에 실패했습니다. 에러 메세지 : "+e.getMessage());
        }
        return new APIResponse<>(result).isSuccess().setMessage("상품 조회에 성공했습니다.");
    }

    @GetMapping("/product")
    public APIResponse<List<Product>> GetProduct(){

        List<Product> emptyArr = new ArrayList<>();
        List<Product> result;
        try {
            result= productService.findAll();
        if (result.isEmpty()){
                return new APIResponse<>(emptyArr).isSuccess().setMessage("조회 결과가 없습니다.");
            };

        } catch (Exception e){
            return new APIResponse<>(emptyArr).isSuccess().setMessage("상품 조회에 성공했습니다.");
        }
        return new APIResponse<>(result).isSuccess().setMessage("상품 조회에 성공했습니다.");
    }

    //삭제
    @DeleteMapping("/product")
    public APIResponse<ProductDTO> DeleteProduct(@RequestBody List<Long> str,BindingResult bindingResult){
        log.info("삭제 요청 : {}",str.toString());
        ProductDTO productDTO = new ProductDTO();
        try{
//            if (str.isEmpty()){
//                return new APIResponse<>(productDTO).isFail().setMessage("정보를 입력해주세요!");
//            }

        for (Long ele:
             str) {
            productService.DeleteByPid(ele);

        }
        } catch (TransactionException e) {
            return new APIResponse<>(productDTO).isSuccess().setMessage("갱신 완료");
        } catch (Exception e){
            return new APIResponse<>(productDTO).isFail().setMessage("에러 발생 : " + e.getMessage());

        }
        return new APIResponse<>(productDTO).isSuccess().setMessage("갱신 완료");
    }

    //수정
    @PatchMapping("/product")
    public APIResponse<ProductDTO> UpdateProduct(@RequestBody List<ProductDTO> productDTOS){
        log.info("수정 요청 : {}",productDTOS.toString());
        for (ProductDTO productDTO: productDTOS
        ) {

            if(productDTO.isEmpty()){
                return new APIResponse<>(productDTO).isFail().setMessage("값을 입력해주세요.");
            }else if(productDTO.getPrice()<1000){
                return new APIResponse<>(productDTO).isFail().setMessage("1000원 미만으로 입력된 상품이 있습니다. 다시 수정해주세요.");
            }else if(productDTO.getQuantity()<1){
                return new APIResponse<>(productDTO).isFail().setMessage("수량이 하나 미만인 상품이 있습니다. 삭제를 원한다면 상품 번호를 누르세요.");
            }else if((productDTO.getPrice()*productDTO.getQuantity())>100_000_000){
                return new APIResponse<>(productDTO).isFail().setMessage("가격과 갯수를 곱한 가격이 1억을 넘을 수 없습니다.");

            }
        }


        for (ProductDTO productDTO: productDTOS
             ) {

            productService.UpdateToDo(productDTO);
        }

        return new APIResponse<>(new ProductDTO()).isSuccess().setMessage("갱신 완료");
    }
}

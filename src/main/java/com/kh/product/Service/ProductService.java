package com.kh.product.Service;

import com.kh.product.DAO.JPARepository.ProductRepository;
import com.kh.product.DTO.ProductDTO;
import com.kh.product.Entity.Product;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    public void InsertToDo(ProductDTO productDTO);
    public List<Product> SearchToDo(String pname);
    public Long DeleteByPid(Long pid);

    public Product FindByPid(Long pid);
    public List<Product> findAll();
    public void UpdateToDo(ProductDTO productDTO);

}

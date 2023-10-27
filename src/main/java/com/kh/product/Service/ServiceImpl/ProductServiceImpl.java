package com.kh.product.Service.ServiceImpl;

import com.kh.product.DAO.JPARepository.ProductRepository;
import com.kh.product.DAO.ProductDAO;
import com.kh.product.DTO.ProductDTO;
import com.kh.product.Entity.Product;
import com.kh.product.Service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final ProductDAO productDAO;
    @Override
    public void InsertToDo(ProductDTO productDTO){
        productDTO.setPid(productDAO.GeneratedKey());
        Product product = new Product(productDTO);
        productRepository.save(product);
    }

    @Override
    public List<Product> SearchToDo(String pname){
        return productRepository
                .findByPname(pname)
                .stream()
                .sorted(Comparator.comparing(Product::getPid).reversed())
                .toList();
    }

    @Override
    @Transactional
    public Long DeleteByPid(Long pid){
        return productRepository.deleteByPid(pid);
    }

    @Override
    public Product FindByPid(Long pid){

        return productRepository.findByPid(pid);
    }

    @Override
    public List<Product> findAll(){
        return productRepository.findAll(Sort.by(Sort.Direction.DESC,"pid"));
    }

    @Override
    public void UpdateToDo(ProductDTO productDTO){
        Product product = new Product(productDTO);
        productRepository.save(product);
    }
}

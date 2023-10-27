package com.kh.product.DAO.JPARepository;

import com.kh.product.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByPname(String pname);
    Long deleteByPid(Long pid);

    Product findByPid(Long pid);

    List<Product> findAll();

}

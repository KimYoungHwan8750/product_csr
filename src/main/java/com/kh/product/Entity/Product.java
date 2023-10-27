package com.kh.product.Entity;

import com.kh.product.DTO.ProductDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor

public class Product {
    @Id
    @Column(name = "PID")
    private Long pid;
    @Column(name = "PNAME")
    private String pname;
    @Column(name = "QUANTITY")
    private Long quantity;
    @Column(name="PRICE")
    private Long price;

    public Product(ProductDTO productDTO){
        this.pid = productDTO.getPid();
        this.pname = productDTO.getPname();
        this.quantity = productDTO.getQuantity();
        this.price = productDTO.getPrice();
    }

}
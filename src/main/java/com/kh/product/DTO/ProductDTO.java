package com.kh.product.DTO;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Table(name = "PRODUCT")
public class ProductDTO {
    @Column(name = "PID")
    private Long pid;

    @Column(name="PNAME")
    private String pname;

    @Column(name="QUANTITY")
    private Long quantity;

    @Column(name="PRICE")
    private Long price;

    public boolean isEmpty(){
        return this.pname==null||this.pname.isEmpty()|| this.quantity == null || this.price == null;
    }



}

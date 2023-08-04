package com.example.mediamarkbe.dto;

import com.example.mediamarkbe.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCartResponse extends ProductResponse{
    private Long quantity;
    private Long productCartId;
    private Double totalOfProductType;

    public static ProductCartResponse mapToDTO(Product product){
        ProductCartResponse productCartResponse = new ProductCartResponse();
        productCartResponse.setId(product.getId());
        productCartResponse.setName(product.getName());
        productCartResponse.setPrice(product.getPrice());
        productCartResponse.setImageLink(product.getImageLink());
        product.setShortDes(product.getShortDes());
        return productCartResponse;
    }
}

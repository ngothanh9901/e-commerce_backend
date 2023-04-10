package com.example.mediamarkbe.dto.payload;

import com.example.mediamarkbe.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductPayload {
    private Long id;
    private String name;
    private String shortDes;
    private String description;
    private String imageLink;
    private Double price;
    private List<Long> categoryIds;

    public  Product convertToModel(Product product){

        product.setName(this.getName());
        product.setDescription(this.getDescription());
        product.setPrice(this.price);
        product.setImageLink(this.getImageLink());
        product.setPrice(this.getPrice());
        product.setShortDes(this.getShortDes());

        return product;
    }
}

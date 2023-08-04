package com.example.mediamarkbe.model;

import com.example.mediamarkbe.model.support.DateAudit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String note ;
    private Double price;
    private Long quantity;
    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id")
    @ToString.Exclude
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    @JsonIgnore
    @ToString.Exclude
    private Product product;

    public OrderDetail(Product product,Orders order,Long quantity){
        this.product = product;
        this.quantity = quantity;
        this.orders = order;
    }
}

package com.example.mediamarkbe.model;

import com.example.mediamarkbe.model.support.DateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Orders order;
    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "id")
    private Product product;
}

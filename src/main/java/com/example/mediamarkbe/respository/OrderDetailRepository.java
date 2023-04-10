package com.example.mediamarkbe.respository;

import com.example.mediamarkbe.model.OrderDetail;
import com.example.mediamarkbe.model.Orders;
import com.example.mediamarkbe.model.Product;
import com.example.mediamarkbe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    @Modifying
    @Query("update OrderDetail o set o.quantity = :quantity where o.id = :id")
    void updateQuantity(@Param(value = "id") Long id, @Param(value = "quantity") Long quantity);

    boolean existsByProductAndOrders(Product product, Orders order);
    OrderDetail findByProductAndAndOrders(Product product, Orders order);

}

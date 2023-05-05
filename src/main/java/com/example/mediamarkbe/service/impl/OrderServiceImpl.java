package com.example.mediamarkbe.service.impl;

import com.example.mediamarkbe.dto.CartResponse;
import com.example.mediamarkbe.dto.ProductCartResponse;
import com.example.mediamarkbe.dto.payload.AddingToCartPayload;
import com.example.mediamarkbe.dto.payload.UpdateCartPayload;
import com.example.mediamarkbe.model.OrderDetail;
import com.example.mediamarkbe.model.Orders;
import com.example.mediamarkbe.model.Product;
import com.example.mediamarkbe.model.User;
import com.example.mediamarkbe.respository.OrderDetailRepository;
import com.example.mediamarkbe.respository.OrderRepository;
import com.example.mediamarkbe.respository.ProductRepository;
import com.example.mediamarkbe.respository.UserRepository;
import com.example.mediamarkbe.service.OrderService;
import com.example.mediamarkbe.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final ProductService productService;
    @Transactional
    public CartResponse addToCart(AddingToCartPayload payload,Long userId){
        Long productId = payload.getProductId();
        Product product = productRepository.findById(productId).get();

        User user = userRepository.findById(userId).get();

        Orders order = orderRepository.existsByStatusAndUser(false,user)? orderRepository.findByStatusAndUser(false,user) : orderRepository.save(new Orders(user));

        boolean check = orderDetailRepository.existsByProductAndOrders(product,order);

        OrderDetail orderDetail = check ? orderDetailRepository.findByProductAndAndOrders(product,order):new OrderDetail(product,order,0L);
        orderDetail.setQuantity(orderDetail.getQuantity()+1);

        orderDetailRepository.save(orderDetail);
        order.getOrderDetailList().add(orderDetail);
        return getCart(userId);
    }
    @Transactional
    public CartResponse getCart(Long userId){
        User user = userRepository.findById(userId).get();
        boolean check = orderRepository.existsByStatusAndUser(false,user);

        if(check){
            Orders orders = orderRepository.findByStatusAndUser(false,user);
            Long idOrder = orders.getId();
            List<OrderDetail> orderDetailList = orders.getOrderDetailList();

            List<ProductCartResponse> productList = orderDetailList.stream().map(x->{
                ProductCartResponse productCartResponse = new ProductCartResponse().mapToDTO(x.getProduct());
                productCartResponse.setQuantity(x.getQuantity());
                productCartResponse.setProductCartId(x.getId());
                return productCartResponse;
            }).collect(Collectors.toList());;

            double sum = productList.stream().mapToDouble(p->{return p.getQuantity()*p.getPrice();}).sum();

            return new CartResponse(productList,sum,idOrder);
        }
        return new CartResponse();
    }
    @Transactional
    public CartResponse updateCart(UpdateCartPayload payload, Long userId){
        orderDetailRepository.updateQuantity(payload.getProductCartId(),payload.getQuantity());
        return getCart(userId);
    }

    public CartResponse deleteCart(Long productCartId, Long userId){
        orderDetailRepository.deleteById(productCartId);
        return getCart(userId);
    }
    @Transactional
    public void payment(Long idOrder){
        Orders order = orderRepository.findById(idOrder).get();
        order.setStatus(true);
    }
}

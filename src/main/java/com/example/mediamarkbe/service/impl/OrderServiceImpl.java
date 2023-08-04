package com.example.mediamarkbe.service.impl;

import com.example.mediamarkbe.dto.CartResponse;
import com.example.mediamarkbe.dto.ProductCartResponse;
import com.example.mediamarkbe.dto.payload.AddingToCartPayload;
import com.example.mediamarkbe.dto.payload.UpdateCartPayload;
import com.example.mediamarkbe.dto.util.ResponseObject;
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
import org.springframework.data.domain.Pageable;
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

    public CartResponse addToCart(AddingToCartPayload payload,Long userId){
        Long productId = payload.getProductId();
        Product product = productRepository.findById(productId).get();

        User user = userRepository.findById(userId).get();

        Orders order = orderRepository.existsByStatusAndUser(false,user)? orderRepository.findByStatusAndUser(false,user) : orderRepository.save(new Orders(user));

        boolean check = orderDetailRepository.existsByProductAndOrders(product,order);

        OrderDetail orderDetail = check ? orderDetailRepository.findByProductAndOrders(product,order):new OrderDetail(product,order,0L);
        orderDetail.setQuantity(orderDetail.getQuantity()+1);

        orderDetailRepository.save(orderDetail);

        CartResponse data = getCart(userId);

        return data;
    }

    public CartResponse getCart(Long userId){
        User user = userRepository.findById(userId).get();
        boolean check = orderRepository.existsByStatusAndUser(false,user);

        if(check){
            Orders orders = orderRepository.findByStatusAndUser(false,user);
//            List<OrderDetail> orderDetailList = orders.getOrderDetailList();
//
//            List<ProductCartResponse> productList = orderDetailList.stream().map(x->{
//                ProductCartResponse productCartResponse = new ProductCartResponse().mapToDTO(x.getProduct());
//                productCartResponse.setQuantity(x.getQuantity());
//                productCartResponse.setProductCartId(x.getId());
//                return productCartResponse;
//            }).collect(Collectors.toList());;
//
//            double sum = productList.stream().mapToDouble(p->{return p.getQuantity()*p.getPrice();}).sum();
//
//            return new CartResponse(productList,sum,orders.getId());
            return cart(orders);
        }
        return new CartResponse();
    }

    public CartResponse updateCart(UpdateCartPayload payload, Long userId){

        if (payload.getQuantity() > 0) {
            OrderDetail orderDetail = orderDetailRepository.findById(payload.getProductCartId()).get();
            orderDetail.setQuantity(payload.getQuantity());
            orderDetailRepository.save(orderDetail);
        } else {
            orderDetailRepository.deleteById(payload.getProductCartId());
        }
        CartResponse data = getCart(userId);
        return data;
    }

    public CartResponse deleteCart(Long productCartId, Long userId){
        orderDetailRepository.deleteById(productCartId);
        return getCart(userId);
    }

    @Override
    public ResponseObject<CartResponse> findOrder(Pageable pageable) {
        return null;
    };

    private CartResponse cart(Orders orders){
        List<OrderDetail> orderDetailList = orders.getOrderDetailList();

        List<ProductCartResponse> productList = orderDetailList.stream().map(x->{
            ProductCartResponse productCartResponse = new ProductCartResponse().mapToDTO(x.getProduct());
            productCartResponse.setQuantity(x.getQuantity());
            productCartResponse.setProductCartId(x.getId());

            Double totalOfProductType = x.getPrice()!=null ? x.getQuantity()*x.getPrice() : x .getProduct().getPrice()*x.getQuantity();
            productCartResponse.setTotalOfProductType( totalOfProductType);
            return productCartResponse;
        }).collect(Collectors.toList());;

        double sum = productList.stream().mapToDouble(p->{return p.getQuantity()*p.getPrice();}).sum();
        Long quantity = productList.stream().mapToLong(p->{return p.getQuantity();}).sum();

        return new CartResponse(productList,quantity,sum,orders.getId());
    }
}

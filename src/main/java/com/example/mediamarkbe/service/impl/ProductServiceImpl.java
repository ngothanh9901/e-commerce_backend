package com.example.mediamarkbe.service.impl;

import com.example.mediamarkbe.dto.ProductResponse;
import com.example.mediamarkbe.dto.filter.FilterProductDTO;
import com.example.mediamarkbe.dto.payload.ProductPayload;
import com.example.mediamarkbe.dto.util.ResponseObject;
import com.example.mediamarkbe.model.Category;
import com.example.mediamarkbe.model.Product;
import com.example.mediamarkbe.respository.CategoryRepository;
import com.example.mediamarkbe.respository.ProductRepository;
import com.example.mediamarkbe.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public ResponseObject<ProductResponse> findProduct(FilterProductDTO payload, Pageable pageable) {
        Page<Product> data = productRepository.findProduct(payload,pageable);
        List<ProductResponse> content = data.getContent().stream().map(p->mapToDTO(p)).collect(Collectors.toList());
        ResponseObject<ProductResponse> responseObject = new ResponseObject<>(content,data.getNumber(),data.getSize(),
                data.getTotalElements(),data.getTotalPages(),data.isLast());

        return responseObject;
    }

    @Override
    public Product add(ProductPayload payload, Long id) {
        Product product = id!=null ? productRepository.findById(id).get():new Product();
        product = payload.convertToModel(product);

        if(payload.getCategoryIds()!=null && !payload.getCategoryIds().isEmpty()){
            List<Category> categories= payload.getCategoryIds().stream().map(d ->categoryRepository.findById(d).get()).collect(Collectors.toList());
            product.setCategories(categories);
        }
        productRepository.save(product);
        return product;
    }


    private ProductResponse mapToDTO (Product product){
        ProductResponse productResponse = new ProductResponse();
        productResponse.setPrice(product.getPrice());
        productResponse.setName(product.getName());
        productResponse.setImageLink(product.getImageLink());
        productResponse.setShortDes(product.getShortDes());
        return productResponse;
    }
}

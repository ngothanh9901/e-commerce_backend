package com.example.mediamarkbe.respository.CustomRepository.impl;

import com.example.mediamarkbe.dto.filter.FilterProductDTO;
import com.example.mediamarkbe.model.Product;
import com.example.mediamarkbe.respository.CustomRepository.CustomizedProductRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

public class CustomizedProductRepositoryImpl implements CustomizedProductRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private Query buidHqlQueryFindProduct(FilterProductDTO payload, Pageable pageable, boolean isCount){
        Map<String, Object> paramMap = new HashMap<>();
        String hqlQuery = "select p from Product p";

//        if(payload.getCategoryIds()!=null && payload.getCategoryIds().size()>0){
//            hqlQuery = "select p from Product p join p.categories c ";
//        }

        if(isCount){
            hqlQuery = "select count(p) from Product p";
        }




        hqlQuery+=" where 1=1";
        if (StringUtils.isNotBlank(payload.getText())) {
            hqlQuery += "  and ( lower(p.name) like :text or  lower(u.description) like :text) ";
            paramMap.put("text", "%"+payload.getText().toLowerCase().trim()+"%");
        }

//
        if (!isCount && pageable != null && pageable.getSort() != null) {
            String[] properties = {""};
            Sort.Direction[] directions = {Sort.Direction.ASC};

            pageable.getSort().forEach(order -> {
                properties[0] = order.getProperty();
                directions[0] = order.getDirection();
            });
            if (StringUtils.isNotBlank(properties[0])) {
                hqlQuery += " Order by " + properties[0] + " " + directions[0];
            }
        }

        Query query = entityManager.createQuery(hqlQuery);
        for (String key : paramMap.keySet()) {
            query.setParameter(key, paramMap.get(key));
        }
        if (!isCount && pageable != null) {
            Integer pageFrom = pageable.getPageNumber() * pageable.getPageSize();
            Integer pageSize = pageable.getPageSize();
            query.setFirstResult(pageFrom);
            query.setMaxResults(pageSize);

        }
        return query;
    }
    //
//
    @Override
    public Page<Product> findProduct(FilterProductDTO payload, Pageable pageable) {
        long total = (long) buidHqlQueryFindProduct(payload,pageable,true).getSingleResult();
        Query query= buidHqlQueryFindProduct(payload,pageable,false);
        return new PageImpl<>(query.getResultList(), pageable, total);
    }
}

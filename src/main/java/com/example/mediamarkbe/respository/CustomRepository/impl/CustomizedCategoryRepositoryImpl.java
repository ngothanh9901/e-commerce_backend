package com.example.mediamarkbe.respository.CustomRepository.impl;

import com.example.mediamarkbe.dto.filter.FilterProductDTO;
import com.example.mediamarkbe.dto.filter.FilteringCategoryDTO;
import com.example.mediamarkbe.model.Category;
import com.example.mediamarkbe.model.Product;
import com.example.mediamarkbe.respository.CustomRepository.CustomizedCategoryRepository;
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

public class CustomizedCategoryRepositoryImpl implements CustomizedCategoryRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private Query buidHqlQueryFindCategory(FilteringCategoryDTO payload, Pageable pageable, boolean isCount){
        Map<String, Object> paramMap = new HashMap<>();
        String hqlQuery = "select c from Category c";
        if(isCount){
            hqlQuery = "select count(c) from Category c";
        }
        hqlQuery+=" where 1=1";
        if (StringUtils.isNotBlank(payload.getText())) {
            hqlQuery += "  and ( lower(c.name) like :text or  lower(c.description) like :text) ";
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

    @Override
    public Page<Category> findCategories(FilteringCategoryDTO payload, Pageable pageable) {
        long total = (long) buidHqlQueryFindCategory(payload,pageable,true).getSingleResult();
        Query query= buidHqlQueryFindCategory(payload,pageable,false);
        return new PageImpl<>(query.getResultList(), pageable, total);
    }
}

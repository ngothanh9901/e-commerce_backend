package com.example.mediamarkbe.common.util;

import org.apache.commons.lang3.StringUtils;
//import org.dozer.DozerBeanMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class DataUtils {
    public static Pageable getPageable(Integer pageIndex, Integer pageSize, Pageable pageable){
        if(pageSize==null && pageIndex ==null){
            pageSize = Integer.MAX_VALUE;
            pageIndex = 0;
        }
        if(pageSize==null){
            pageSize=50;
        }
        return pageable == null ? PageRequest.of(pageIndex, pageSize) :
                PageRequest.of(pageIndex, pageSize, pageable.getSort());
    }
    public static Pageable getPageableForListLimit(Integer pageNumber, Integer pageSize,Sort.Direction sortDir,String... properties){
        return new Pageable() {
            @Override
            public int getPageNumber() {
                return pageNumber;
            }

            @Override
            public int getPageSize() {
                return pageSize;
            }

            @Override
            public long getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                if (properties == null || properties.length == 0 || StringUtils.isEmpty(properties[0])) return null;
                return Sort.by(sortDir!=null ? sortDir:Sort.Direction.ASC, properties);
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public Pageable withPage(int pageNumber) {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
    }

//    public static DozerBeanMapper mapper = new DozerBeanMapper();
//    static {
////        mapper.setMappingFiles(Collections.singletonList("dozerJdk8Converters.xml"));
//    }
}

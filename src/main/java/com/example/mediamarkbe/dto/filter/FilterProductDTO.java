package com.example.mediamarkbe.dto.filter;

import lombok.Data;

import java.util.List;

@Data
public class FilterProductDTO {
    String text;
    List<Long> categoryIds;

    public boolean isNull(){
        return (text==null && categoryIds==null);
    }
}

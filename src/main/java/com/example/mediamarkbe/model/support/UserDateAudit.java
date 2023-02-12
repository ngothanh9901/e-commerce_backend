package com.example.mediamarkbe.model.support;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public abstract class UserDateAudit extends DateAudit {

    @CreatedBy
    @JsonIgnore
    private Long createdBy;

    @LastModifiedBy
    @JsonIgnore
    private Long updatedBy;

    private boolean deleted;

}

package com.example.ProductCategory.domain.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ProductCategoryDTO {

    private String categoryCode;
    private String categoryNameId;
    private String categoryNameEn;
    private String categoryDescId;
    private String categoryDescEn;
    private Integer priority;
    private String isDeleted;
}

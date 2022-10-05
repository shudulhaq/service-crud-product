package com.example.ProductCategory.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_category")
public class ProductCategoryDao {

    @Id
    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "category_name_id")
    private String categoryNameId;

    @Column(name = "category_name_en")
    private String categoryNameEn;

    @Column(name = "category_desc_id")
    private String categoryDescId;

    @Column(name = "category_desc_en")
    private String categoryDescEn;

    @Column(name = "priority")
    private Integer priority;

    @Column(name = "is_deleted")
    private String isDeleted;
}

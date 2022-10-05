package com.example.ProductCategory.controller;

import com.example.ProductCategory.domain.dto.ProductCategoryDTO;
import com.example.ProductCategory.service.ProductCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService service;

    @GetMapping("/getAll")
    public ResponseEntity<Object> getAll(){
        try {
            return service.getAll();
        }catch (Exception e){
            log.error("Error when get all product category");
            throw e;
        }
    }

    @GetMapping("/data/{code}")
    public ResponseEntity<Object> findByCode(@PathVariable String code){
        try {
            return service.findByCode(code);
        }catch (Exception e){
            log.error("Error when get product category by code");
            throw e;
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createProductCategory(@RequestBody ProductCategoryDTO request){
        try {
            return service.createProductCategory(request);
        }catch (Exception e){
            log.info("Error when create product category");
            throw e;
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateProductCategory(@RequestBody ProductCategoryDTO request, @PathVariable String id){
        try {
            return service.updateProductCategory(request, id);
        }catch (Exception e){
            log.info("Error when update product category");
            throw e;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteProductCategory(@RequestBody ProductCategoryDTO request, @PathVariable String id){
        try {
            return service.deleteProductCategory(request, id);
        }catch (Exception e){
            log.info("Error when delete product category");
            throw e;
        }
    }
}

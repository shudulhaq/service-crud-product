package com.example.ProductCategory.service;

import com.example.ProductCategory.domain.dao.ProductCategoryDao;
import com.example.ProductCategory.domain.dto.ProductCategoryDTO;
import com.example.ProductCategory.repository.ProductCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    public ResponseEntity<Object> getAll(){
        List<ProductCategoryDao> result =  repository.findAll();

        if (result.isEmpty()){
            log.warn("product category list is empty : ");
            return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
        }

        result = result.stream().filter(data -> data.getIsDeleted() != null).filter(data -> data.getIsDeleted().equalsIgnoreCase("N")).collect(Collectors.toList());

        ModelMapper modelMapper = new ModelMapper();
        List<ProductCategoryDTO> response = result.stream().map(data -> modelMapper.map(data, ProductCategoryDTO.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Object> findByCode(String code){
        log.info("start find product category by code : ");

        if (code.isEmpty()) {
            return new ResponseEntity<>(code, HttpStatus.BAD_REQUEST);
        }

        Optional<ProductCategoryDao> checkAvailable = repository.findById(code);

        if (checkAvailable.isEmpty()) {
            log.error("data not found");
            return new ResponseEntity<>(checkAvailable, HttpStatus.BAD_REQUEST);
        }

        ModelMapper modelMapper = new ModelMapper();
        ProductCategoryDTO response = modelMapper.map(checkAvailable, ProductCategoryDTO.class);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Object> createProductCategory(ProductCategoryDTO request){
        log.info("start create product category : ");

        ProductCategoryDao dao = new ProductCategoryDao();
        dao.setCategoryCode(request.getCategoryCode());
        dao.setCategoryNameId(request.getCategoryNameId());
        dao.setCategoryNameEn(request.getCategoryNameId());
        dao.setCategoryDescId(request.getCategoryDescId());
        dao.setCategoryDescEn(request.getCategoryDescEn());
        dao.setPriority(request.getPriority());
        dao.setIsDeleted("N");

        repository.saveAndFlush(dao);

        ModelMapper modelMapper = new ModelMapper();
        ProductCategoryDTO response = modelMapper.map(dao, ProductCategoryDTO.class);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Object> updateProductCategory(ProductCategoryDTO request, String id){
        log.info("start update product category : ");

        if (id.isEmpty()) {
            return new ResponseEntity<>(id, HttpStatus.BAD_REQUEST);
        }

        request.setCategoryCode(id);
        Optional<ProductCategoryDao> checkAvailable = repository.findById(request.getCategoryCode());

        if (checkAvailable.isEmpty()) {
            log.error("data not found");
            return new ResponseEntity<>(checkAvailable, HttpStatus.BAD_REQUEST);
        }

        ProductCategoryDao dao = new ProductCategoryDao();
        dao.setCategoryCode(checkAvailable.get().getCategoryCode());
        dao.setCategoryNameId(request.getCategoryNameId());
        dao.setCategoryNameEn(request.getCategoryNameEn());
        dao.setCategoryDescId(request.getCategoryDescId());
        dao.setCategoryDescEn(request.getCategoryDescEn());
        dao.setPriority(request.getPriority());
        dao.setIsDeleted(request.getIsDeleted());

        repository.saveAndFlush(dao);

        ModelMapper modelMapper = new ModelMapper();
        ProductCategoryDTO response = modelMapper.map(dao, ProductCategoryDTO.class);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteProductCategory(ProductCategoryDTO request, String id){
        log.info("start delete product category : ");

        request.setCategoryCode(id);
        Optional<ProductCategoryDao> checkAvailable = repository.findById(request.getCategoryCode());

        if (checkAvailable.isEmpty()) {
            log.error("data not found");
            return new ResponseEntity<>(checkAvailable, HttpStatus.BAD_REQUEST);
        }

        ProductCategoryDao dao = checkAvailable.get();
        dao.setIsDeleted("Y");

        repository.saveAndFlush(dao);

        ModelMapper modelMapper = new ModelMapper();
        ProductCategoryDTO response = modelMapper.map(dao, ProductCategoryDTO.class);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

package com.nawid.onlineshop.controller;


import com.nawid.onlineshop.dto.ProductDto;
import com.nawid.onlineshop.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    IProductService service;

    @PostMapping
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto product){
        ProductDto pdto = service.addpdto(product);
        return new ResponseEntity<>(pdto ,HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> pdtos = service.getAllProducts();
        return new ResponseEntity<>(pdtos, HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable int id){
        ProductDto pdto = service.getProduct(id);
        return new ResponseEntity<>(pdto, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<ProductDto>  updateProduct(@RequestBody ProductDto pdto, @PathVariable int id){
        ProductDto updatedPdto = service.updateProduct(pdto, id);
        return new ResponseEntity<>(updatedPdto, HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){
        service.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

package com.nawid.onlineshop.service;

import com.nawid.onlineshop.dto.ProductDto;
import com.nawid.onlineshop.entity.Product;
import com.nawid.onlineshop.repo.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProductRepo repo;



    public ProductService() {
    }

    public ProductService(ModelMapper modelMapper, ProductRepo repo) {
        this.modelMapper = modelMapper;
        this.repo = repo;
    }

    public ModelMapper getModelMapper() {
        return modelMapper;
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductRepo getRepo() {
        return repo;
    }

    public void setRepo(ProductRepo repo) {
        this.repo = repo;
    }



    @Override
    public ProductDto addpdto(ProductDto pdto) {

        Product product = modelMapper.map(pdto, Product.class );
        Product savedProduct = repo.save(product);
        pdto = modelMapper.map(savedProduct, ProductDto.class);

        return pdto;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = repo.findAll();
        List<ProductDto> pdtos = new ArrayList<>();

        for (Product product : products) {
            pdtos.add(modelMapper.map(product, ProductDto.class));
        }

        return pdtos;
    }

    @Override
    public ProductDto getProduct(int id) {
        Product product = repo.findById(id).orElseThrow(()->new RuntimeException("Resource not found with id "+ id));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public ProductDto updateProduct(ProductDto pdto, int id) {

        Product product = repo.findById(id).orElseThrow(()->new RuntimeException("Resource not found with id "+ id));
        product.setName(pdto.getName());
        product.setDescription(pdto.getDescription());
        product.setPrice(pdto.getPrice());
        product.setAmount(pdto.getAmount());
        product.setRate(pdto.getRate());
        Product updatedProduct = repo.save(product);

        return modelMapper.map(updatedProduct, ProductDto.class);

    }

    @Override
    public void deleteProduct(int id) {
        repo.findById(id).orElseThrow(()->new RuntimeException("Resource not found with id "+ id));
        repo.deleteById(id);
    }

}

package com.nawid.onlineshop.service;

import com.nawid.onlineshop.dto.ProductDto;

import java.util.List;

public interface IProductService {



    public ProductDto addpdto(ProductDto pdto);

    public List<ProductDto> getAllProducts();

    public ProductDto getProduct(int id);

    public ProductDto updateProduct(ProductDto pdto, int id);

    public void deleteProduct(int id);
}

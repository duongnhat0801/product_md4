package com.codegym.product_baitap.service;

import com.codegym.product_baitap.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    void save(Product product);


    Product findById(Long id);

    void update(Product product);

    void remove(Long id);
}

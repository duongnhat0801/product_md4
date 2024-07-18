package com.codegym.product_baitap.repositories;

import com.codegym.product_baitap.model.Product;

import java.util.List;

public interface IProductRepo {
    List<Product> showAll();

    void save(Product product);

    Product findById(Long id);

    void delete(Long id);

    List<Product> searchProductByName(String keyword);

}
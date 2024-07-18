package com.codegym.product_baitap.service;

import com.codegym.product_baitap.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService implements IProductService {
    private static final List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1L, "A", 7.3, "asdada", "asdasdg"));
    }

    private Long nextId = 1L;


    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        product.setId(nextId++);
        products.add(product);
    }

    @Override
    public void remove(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }
    @Override
    public Product findById(Long id) {
        Optional<Product> product = products.stream().filter(p -> p.getId().equals(id)).findFirst();
        return product.orElse(null);
    }

    @Override
    public void update(Product updatedProduct) {
        products.replaceAll(product -> product.getId().equals(updatedProduct.getId()) ? updatedProduct : product);
    }
}


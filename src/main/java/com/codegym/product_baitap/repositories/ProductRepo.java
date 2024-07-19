package com.codegym.product_baitap.repositories;

import com.codegym.product_baitap.model.Product;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class ProductRepo implements IProductRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Product> showAll() {
        return entityManager.createQuery("select p from Product p", Product.class).getResultList();
    }

    @Override
    public void save(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
        } else {
            entityManager.merge(product);
        }
    }

    @Override
    public Product findById(Long id) {
        TypedQuery<Product> query = entityManager.createQuery("select p from Product p where p.id = :id", Product.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        Product product = findById(id);
        if (product != null) {
            entityManager.remove(product);
        }
    }

    @Override
    public List<Product> searchProductByName(String keyword) {
        return entityManager.createQuery("select p from Product p where p.name like :name", Product.class)
                .setParameter("name", "%" + keyword + "%")
                .getResultList();
    }
}

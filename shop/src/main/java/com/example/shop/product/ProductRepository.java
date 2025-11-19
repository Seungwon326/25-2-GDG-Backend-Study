package com.example.shop.product;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    @PersistenceContext
    private EntityManager em;

    public Product findById(long id){
        return em.find(Product.class , id);
    }

    public List<Product> findAll(){
        return em.createQuery("SELECT p from product p ", Product.class)
                .getResultList();
    }


    public Product findByProductId(String productName){
        List<Product> result = em.createQuery(
                "SELECT p FROM product p WHERE productName = :productName",Product.class
        ).setParameter("productName",productName).getResultList();

        return result.isEmpty() ? null : result.get(0);
    }

    public void save(Product product){
        em.persist(product);
    }

    public void deleteById(Long id){
        Product product = em.find(Product.class, id);
        em.remove(product);
    }
}

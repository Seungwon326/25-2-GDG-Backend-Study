package com.example.shop.order;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderRepository {

    @PersistenceContext
    private EntityManager em ;

    public Orders findById(Long orderid){
        return em.find(Orders.class, orderid);

    }

    public List<Orders> findAll(){
        return em.createQuery("SELECT o FROM orders o", Orders.class)
                .getResultList();

    }

    public Orders findOrderById(String orderId){
        List<Orders> result = em.createQuery(
                "SELECT o FROM orders WHERE orderId = :orderId", Orders.class
        ).setParameter("orderId",orderId).getResultList();

        return result.isEmpty() ? null : result.get(0);
    }


    public void save(Orders orders){
        em.persist(orders);
    }

    public void deleteById(Long id){
        Orders orders = em.find(Orders.class, id);
        em.remove(orders);
    }



}

package com.example.shop.order;

import com.example.shop.order.dto.OrderCreateRequest;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;


    @Transactional
    public long createOrder(OrderCreateRequest request) {

        Orders orders = new Orders(
                request.getOrderDate(),
                request.getTotalPrice(),
                request.getStatus(),
                request.getPointUsed(),
                request.getCashAmount()
        );

        orderRepository.save(orders);
        return orders.getOrderId();
    }


    @Transactional
    public List<Orders> getAllOrder() {

        return orderRepository.findAll();
    }

    @Transactional
    public Orders getOrderById (long orderId){
        Orders orders = orderRepository.findById(orderId);
        if( orders == null){
            throw new RuntimeException("주문정보를 찾을 수 없습니다"+orderId);
        }
        return orders;
    }

    @Transactional
    public void deleteOrderById(long orderId){
        Orders orders = orderRepository.findById(orderId);
        if( orders != null){
            throw new RuntimeException("주문정보를 찾을 수 없습니다"+orderId);
        }

        orderRepository.deleteById(orderId);
    }
}
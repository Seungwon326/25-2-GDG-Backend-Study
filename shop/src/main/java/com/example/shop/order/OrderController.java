package com.example.shop.order;


import com.example.shop.order.dto.OrderCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody OrderCreateRequest orderCreateRequest){
        Long orderId = orderService.createOrder(orderCreateRequest);
        return ResponseEntity.created(URI.create("/order"+orderId)).build();
    }

    @GetMapping
    public ResponseEntity<List<Orders>> getAllOrder(){
        List<Orders> orders = orderService.getAllOrder();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Orders> getOrder(@PathVariable long orderId){
        Orders orders = orderService.getOrderById(orderId);
        return ResponseEntity.ok(orders);
    }
    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long orderId){
        orderService.deleteOrderById(orderId);

        return ResponseEntity.ok().build();
    }


}
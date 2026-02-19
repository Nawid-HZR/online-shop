package com.nawid.onlineshop.controller;


import com.nawid.onlineshop.dto.OrderDto;
import com.nawid.onlineshop.dto.OrderRequest;
import com.nawid.onlineshop.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {


    @Autowired
    IOrderService service;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderRequest request, Principal principal){
        OrderDto odto = service.buyProduct(request,principal.getName());
        return new ResponseEntity<>(odto, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        List<OrderDto> orders = service.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @GetMapping("/my")
    public ResponseEntity<List<OrderDto>> getMyOrders(Principal principal) {
        List<OrderDto> orders = service.getOrdersByUsername(principal.getName());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Integer id, Principal principal) {
        OrderDto odto = service.getOrderById(id, principal.getName());
        return new ResponseEntity<>(odto, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer id, Principal principal) {
        service.deleteOrder(id, principal.getName());
        return ResponseEntity.noContent().build();
    }



}

package com.nawid.onlineshop.service;

import com.nawid.onlineshop.dto.OrderDto;
import com.nawid.onlineshop.dto.OrderRequest;

import java.util.List;

public interface IOrderService {


    public OrderDto buyProduct(OrderRequest request, String username);

    public List<OrderDto>  getAllOrders();

    public List<OrderDto> getOrdersByUsername(String username);

    public OrderDto getOrderById(Integer id, String name);

    public void deleteOrder(Integer id, String name);

}

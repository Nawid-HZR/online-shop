package com.nawid.onlineshop.service;

import com.nawid.onlineshop.dto.OrderDto;
import com.nawid.onlineshop.dto.OrderRequest;
import com.nawid.onlineshop.entity.Order;
import com.nawid.onlineshop.entity.Product;
import com.nawid.onlineshop.entity.User;
import com.nawid.onlineshop.exception.AccessDeniedException;
import com.nawid.onlineshop.exception.InsufficientStockException;
import com.nawid.onlineshop.exception.InvalidRequestException;
import com.nawid.onlineshop.exception.ResourceNotFoundException;
import com.nawid.onlineshop.repo.OrderRepo;
import com.nawid.onlineshop.repo.ProductRepo;
import com.nawid.onlineshop.repo.UserRepo;
import com.nawid.onlineshop.security.SecurityUtil;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderService implements IOrderService{

    private UserRepo userRepo;
    private ProductRepo productRepo;
    private OrderRepo orderRepo;
    private ModelMapper mapper;
    private SecurityUtil securityUtil;

    public OrderService(UserRepo userRepo, ProductRepo productRepo, OrderRepo orderRepo, ModelMapper mapper, SecurityUtil securityUtil) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.mapper = mapper;
        this.securityUtil = securityUtil;
    }

    @Override
    @Transactional
    public OrderDto buyProduct(OrderRequest request, String username) {

        User user = userRepo.findByUsername(username).orElseThrow(()-> new ResourceNotFoundException("user not found"));
        Product product = productRepo.findById(request.getProductId()).orElseThrow(()-> new ResourceNotFoundException("product not found") );

        if (request.getQuantity()<=0){
            throw new InvalidRequestException("Product can not be zero or less");
        }
        if (product.getAmount() < request.getQuantity()) {
            throw new InsufficientStockException("Not enough stock");
        }

        product.setAmount(product.getAmount()-request.getQuantity());
        productRepo.save(product);

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(request.getQuantity());
        order.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
        order.setCreatedAt(LocalDateTime.now());
        orderRepo.save(order);

        return mapper.map(order, OrderDto.class);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepo.findAll();
        List<OrderDto> odtos = new ArrayList<>();
        for (Order order : orders) {
            odtos.add(mapper.map(order, OrderDto.class));
        }
        return odtos;
    }

    @Override
    public List<OrderDto> getOrdersByUsername(String username) {
        List<Order> orders = orderRepo.findByUserUsername(username);
        List<OrderDto> odtos = new ArrayList<>();
        for (Order order : orders) {
            odtos.add(mapper.map(order, OrderDto.class));
        }
        return odtos;
    }

    @Override
    public OrderDto getOrderById(Integer id, String username) {
        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (!order.getUser().getUsername().equals(username) && !securityUtil.isAdmin()) {
            throw new AccessDeniedException("Access denied");
        }

        return mapper.map(order, OrderDto.class);
    }
    @Override
    @Transactional
    public void deleteOrder(Integer id, String username) {

        Order order = orderRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        if (!order.getUser().getUsername().equals(username) && !securityUtil.isAdmin()) {
            throw new AccessDeniedException("Access denied");
        }

        orderRepo.delete(order);
    }

}

package com.sanket.razorpayorderservice.ServiceImpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanket.razorpayorderservice.DTO.CreateOrderRequestDTO;
import com.sanket.razorpayorderservice.DTO.CreateOrderResponseDTO;
import com.sanket.razorpayorderservice.DTO.UpdateOrderRequestDTO;
import com.sanket.razorpayorderservice.DTO.UpdateOrderResponseDTO;
import com.sanket.razorpayorderservice.Entity.Order;
import com.sanket.razorpayorderservice.Repository.OrderRepository;
import com.sanket.razorpayorderservice.Service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    


    @Override
    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO) {

        Order order = new Order();

        order.setAmount(createOrderRequestDTO.getAmount());
        order.setCurrency(createOrderRequestDTO.getCurrency());
        order.setReceipt(createOrderRequestDTO.getReceipt());
        order.setNotes(createOrderRequestDTO.getNotes());

        // genrated id 
        order.setId(
            "order_" +
            UUID.randomUUID()
            .toString()
            .replace("-", "")
            .substring(0, 14)
        );


        // System-generated values
        order.setAmount_paid(0);
        order.setAmount_due(createOrderRequestDTO.getAmount());
        order.setStatus("created");
        order.setAttempts(0);
        order.setCreated_at(System.currentTimeMillis() / 1000);

        Order savedOrder = orderRepository.save(order);

        CreateOrderResponseDTO createOrderResponseDTO = new CreateOrderResponseDTO();

        createOrderResponseDTO.setId(savedOrder.getId());
        createOrderResponseDTO.setEntity(savedOrder.getEntity());
        createOrderResponseDTO.setAmount(savedOrder.getAmount());
        createOrderResponseDTO.setCurrency(savedOrder.getCurrency());
        createOrderResponseDTO.setReceipt(savedOrder.getReceipt());
        createOrderResponseDTO.setNotes(savedOrder.getNotes());
        createOrderResponseDTO.setAmountPaid(savedOrder.getAmount_paid());
        createOrderResponseDTO.setAmountDue(savedOrder.getAmount_due());
        createOrderResponseDTO.setCreatedAt(savedOrder.getCreated_at());
        createOrderResponseDTO.setAttempts(savedOrder.getAttempts());
        createOrderResponseDTO.setStatus(savedOrder.getStatus());
        createOrderResponseDTO.setOfferId(savedOrder.getOfferId());
       
        
        return createOrderResponseDTO;

    }

    @Override
    public UpdateOrderResponseDTO updateOrder(String id, UpdateOrderRequestDTO updateOrderRequestDTO) {

        Order order = orderRepository.findById(id).orElseThrow(() ->new RuntimeException("Order not found with id: " + id));

        
        order.setNotes(updateOrderRequestDTO.getNotes());

        Order updatedOrder = orderRepository.save(order);

        UpdateOrderResponseDTO updateOrderResponseDTO = new UpdateOrderResponseDTO();

        updateOrderResponseDTO.setId(updatedOrder.getId());
        updateOrderResponseDTO.setEntity(updatedOrder.getEntity());
        updateOrderResponseDTO.setAmount(updatedOrder.getAmount());
        updateOrderResponseDTO.setCurrency(updatedOrder.getCurrency());
        updateOrderResponseDTO.setReceipt(updatedOrder.getReceipt());
        updateOrderResponseDTO.setNotes(updatedOrder.getNotes());
        updateOrderResponseDTO.setAmountPaid(updatedOrder.getAmount_paid());
        updateOrderResponseDTO.setAmountDue(updatedOrder.getAmount_due());
        updateOrderResponseDTO.setCreatedAt(updatedOrder.getCreated_at());
        updateOrderResponseDTO.setAttempts(updatedOrder.getAttempts());
        updateOrderResponseDTO.setStatus(updatedOrder.getStatus());
        updateOrderResponseDTO.setOfferId(updatedOrder.getOfferId());

        return updateOrderResponseDTO;
    }

}

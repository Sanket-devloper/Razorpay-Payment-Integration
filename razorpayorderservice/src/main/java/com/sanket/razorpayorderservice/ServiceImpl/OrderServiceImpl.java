package com.sanket.razorpayorderservice.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sanket.razorpayorderservice.DTO.CreateOrderRequestDTO;
import com.sanket.razorpayorderservice.DTO.CreateOrderResponseDTO;
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
    
}

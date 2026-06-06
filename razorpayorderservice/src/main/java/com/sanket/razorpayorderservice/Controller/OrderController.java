package com.sanket.razorpayorderservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanket.razorpayorderservice.DTO.CreateOrderRequestDTO;
import com.sanket.razorpayorderservice.DTO.CreateOrderResponseDTO;
import com.sanket.razorpayorderservice.Service.OrderService;

@RestController
@RequestMapping("/v1")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/CreateOrder") //localhost:8080/v1/CreateOrder
    public ResponseEntity<CreateOrderResponseDTO> createOrder(@RequestBody CreateOrderRequestDTO createOrderRequestDTO) {
        CreateOrderResponseDTO createOrderResponseDTO = orderService.createOrder(createOrderRequestDTO);
        return new ResponseEntity<>(createOrderResponseDTO, HttpStatus.OK);
    }

   

    
}

package com.sanket.razorpayorderservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanket.razorpayorderservice.DTO.CreateOrderRequestDTO;
import com.sanket.razorpayorderservice.DTO.CreateOrderResponseDTO;
import com.sanket.razorpayorderservice.DTO.FetchAllOrdersResponseDTO;
import com.sanket.razorpayorderservice.DTO.UpdateOrderRequestDTO;
import com.sanket.razorpayorderservice.DTO.UpdateOrderResponseDTO;
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

    @PutMapping("/orders/{id}") //localhost:8080/v1/orders/{id}
    public ResponseEntity<UpdateOrderResponseDTO> updateOrder(@PathVariable String id, @RequestBody UpdateOrderRequestDTO updateOrderRequestDTO) {
        UpdateOrderResponseDTO updateOrderResponseDTO = orderService.updateOrder(id, updateOrderRequestDTO);
        return new ResponseEntity<>(updateOrderResponseDTO, HttpStatus.OK);
    }

    @GetMapping("/orders") //localhost:8080/v1/orders
    public ResponseEntity<FetchAllOrdersResponseDTO> fetchAllOrders() {

        FetchAllOrdersResponseDTO response = orderService.fetchAllOrders();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/order") //localhost:8080/v1/order
    public ResponseEntity<?> fetchAllOrdersWithPayments(@RequestParam(value = "expand", required = false) String expand) {

        if ("payments".equals(expand)) {
            return ResponseEntity.ok(
                    orderService.fetchAllOrdersWithPayments());
        }
        // CollectionFetchAllExpandedPaymentResponse response = orderService.fetchAllOrdersWithPayments();

        return ResponseEntity.ok(orderService.fetchAllOrders());
    }



   

    
}

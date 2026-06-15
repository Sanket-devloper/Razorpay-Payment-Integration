package com.sanket.razorpayorderservice.Service;



import com.sanket.razorpayorderservice.DTO.CollectionFetchAllExpandedPaymentResponse;
import com.sanket.razorpayorderservice.DTO.CreateOrderRequestDTO;
import com.sanket.razorpayorderservice.DTO.CreateOrderResponseDTO;
import com.sanket.razorpayorderservice.DTO.FetchAllOrdersResponseDTO;
import com.sanket.razorpayorderservice.DTO.UpdateOrderRequestDTO;
import com.sanket.razorpayorderservice.DTO.UpdateOrderResponseDTO;

public interface OrderService {
    
    CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO);

    UpdateOrderResponseDTO updateOrder(String id, UpdateOrderRequestDTO updateOrderRequestDTO);

    FetchAllOrdersResponseDTO fetchAllOrders();

    CollectionFetchAllExpandedPaymentResponse fetchAllOrdersWithPayments();

}

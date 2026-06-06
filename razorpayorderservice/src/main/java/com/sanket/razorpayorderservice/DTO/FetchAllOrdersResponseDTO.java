package com.sanket.razorpayorderservice.DTO;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FetchAllOrdersResponseDTO {
    String entity;
    int count;
    List<OrdersResponseDTO> items;
}

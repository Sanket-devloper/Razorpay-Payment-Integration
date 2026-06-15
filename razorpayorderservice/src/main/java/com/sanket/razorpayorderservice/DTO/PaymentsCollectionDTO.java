package com.sanket.razorpayorderservice.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentsCollectionDTO {
    String entity;
    int count;
    List<PaymentDTO> items;
}

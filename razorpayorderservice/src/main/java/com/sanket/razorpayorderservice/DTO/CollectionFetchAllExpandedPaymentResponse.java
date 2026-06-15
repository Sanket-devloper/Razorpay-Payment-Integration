package com.sanket.razorpayorderservice.DTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionFetchAllExpandedPaymentResponse {
    String entity;
    int count;
    List<FetchAllExpandedPaymentsResponseDTO> items;
}

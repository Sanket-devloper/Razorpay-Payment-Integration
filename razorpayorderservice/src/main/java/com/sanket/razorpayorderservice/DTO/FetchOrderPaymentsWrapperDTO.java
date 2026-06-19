package com.sanket.razorpayorderservice.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FetchOrderPaymentsWrapperDTO {

    private String entity;

    private Integer count;

    private List<FetchPaymentsResponseDTO> items;
}
package com.sanket.razorpayorderservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcquirerDataDTO {

    String rrn;

    String authenticationReferenceNumber;

    String bankTransactionId;
}

package com.sanket.razorpayorderservice.ServiceImpl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sanket.razorpayorderservice.DTO.CapturePaymentRequestDTO;
import com.sanket.razorpayorderservice.DTO.PaymentDTO;
import com.sanket.razorpayorderservice.Entity.Order;
import com.sanket.razorpayorderservice.Entity.Payment;
import com.sanket.razorpayorderservice.Repository.OrderRepository;
import com.sanket.razorpayorderservice.Repository.PaymentRepository;
import com.sanket.razorpayorderservice.Service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    public PaymentDTO capturePayment(
            String paymentId,
            CapturePaymentRequestDTO requestDTO) {

        // Find order first
        Order order = orderRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        Payment payment = new Payment();

        payment.setId(paymentId);

        payment.setEntity("payment");

        payment.setAmount(requestDTO.getAmount());

        payment.setCurrency(requestDTO.getCurrency());

        payment.setStatus("captured");

        payment.setOrderId(order.getId());

        payment.setMethod("card");

        payment.setCaptured(true);

        payment.setInternational(false);

        payment.setAmountRefunded(0);

        payment.setRefundStatus(null);

        payment.setDescription("Payment captured successfully");

        payment.setEmail("test@gmail.com");

        payment.setContact("9876543210");

        payment.setFee(10);

        payment.setTax(2);

        payment.setCreatedAt(
                System.currentTimeMillis() / 1000);

        payment.setCardId(
                "card_" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 10));

        Payment savedPayment =
                paymentRepository.save(payment);

        // UPDATE ORDER
        order.setAmount_paid(savedPayment.getAmount());

        order.setAmount_due(0);

        order.setStatus("attempted");

        order.setAttempts(order.getAttempts() + 1);

        orderRepository.save(order);

        return convertToDTO(savedPayment);
    }

    private PaymentDTO convertToDTO(Payment payment) {

        PaymentDTO dto = new PaymentDTO();

        dto.setId(payment.getId());

        dto.setEntity(payment.getEntity());

        dto.setAmount(payment.getAmount());

        dto.setCurrency(payment.getCurrency());

        dto.setStatus(payment.getStatus());

        dto.setOrderId(payment.getOrderId());

        dto.setMethod(payment.getMethod());

        dto.setCaptured(payment.getCaptured());

        dto.setInternational(payment.getInternational());

        dto.setAmountRefunded(
                payment.getAmountRefunded());

        dto.setRefundStatus(
                payment.getRefundStatus());

        dto.setDescription(
                payment.getDescription());

        dto.setEmail(payment.getEmail());

        dto.setContact(payment.getContact());

        dto.setFee(payment.getFee());

        dto.setTax(payment.getTax());

        dto.setCreatedAt(payment.getCreatedAt());

        dto.setCardId(payment.getCardId());

        return dto;
    }
}

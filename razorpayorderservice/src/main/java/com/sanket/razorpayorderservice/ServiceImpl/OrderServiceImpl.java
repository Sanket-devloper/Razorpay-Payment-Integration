package com.sanket.razorpayorderservice.ServiceImpl;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sanket.razorpayorderservice.DTO.CollectionFetchAllExpandedPaymentResponse;
import com.sanket.razorpayorderservice.DTO.CreateOrderRequestDTO;
import com.sanket.razorpayorderservice.DTO.CreateOrderResponseDTO;
import com.sanket.razorpayorderservice.DTO.FetchAllExpandedPaymentsResponseDTO;
import com.sanket.razorpayorderservice.DTO.FetchAllOrdersResponseDTO;
import com.sanket.razorpayorderservice.DTO.OrdersResponseDTO;
import com.sanket.razorpayorderservice.DTO.PaymentDTO;
import com.sanket.razorpayorderservice.DTO.PaymentsCollectionDTO;
import com.sanket.razorpayorderservice.DTO.UpdateOrderRequestDTO;
import com.sanket.razorpayorderservice.DTO.UpdateOrderResponseDTO;
import com.sanket.razorpayorderservice.Entity.Order;
import com.sanket.razorpayorderservice.Entity.Payment;
import com.sanket.razorpayorderservice.Repository.OrderRepository;
import com.sanket.razorpayorderservice.Repository.PaymentRepository;
import com.sanket.razorpayorderservice.Service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    // @Autowired
    // public OrderServiceImpl(OrderRepository orderRepository) {
    //     this.orderRepository = orderRepository;
    // }
    


    @Override
    public CreateOrderResponseDTO createOrder(CreateOrderRequestDTO createOrderRequestDTO) {

        Order order = new Order();

        order.setAmount(createOrderRequestDTO.getAmount());
        order.setCurrency(createOrderRequestDTO.getCurrency());
        order.setReceipt(createOrderRequestDTO.getReceipt());
        order.setNotes(createOrderRequestDTO.getNotes());

        // genrated id 
        order.setId(
            "order_" +
            UUID.randomUUID()
            .toString()
            .replace("-", "")
            .substring(0, 14)
        );


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

    @Override
    public UpdateOrderResponseDTO updateOrder(String id, UpdateOrderRequestDTO updateOrderRequestDTO) {

        Order order = orderRepository.findById(id).orElseThrow(() ->new RuntimeException("Order not found with id: " + id));

        
        order.setNotes(updateOrderRequestDTO.getNotes());

        Order updatedOrder = orderRepository.save(order);

        UpdateOrderResponseDTO updateOrderResponseDTO = new UpdateOrderResponseDTO();

        updateOrderResponseDTO.setId(updatedOrder.getId());
        updateOrderResponseDTO.setEntity(updatedOrder.getEntity());
        updateOrderResponseDTO.setAmount(updatedOrder.getAmount());
        updateOrderResponseDTO.setCurrency(updatedOrder.getCurrency());
        updateOrderResponseDTO.setReceipt(updatedOrder.getReceipt());
        updateOrderResponseDTO.setNotes(updatedOrder.getNotes());
        updateOrderResponseDTO.setAmountPaid(updatedOrder.getAmount_paid());
        updateOrderResponseDTO.setAmountDue(updatedOrder.getAmount_due());
        updateOrderResponseDTO.setCreatedAt(updatedOrder.getCreated_at());
        updateOrderResponseDTO.setAttempts(updatedOrder.getAttempts());
        updateOrderResponseDTO.setStatus(updatedOrder.getStatus());
        updateOrderResponseDTO.setOfferId(updatedOrder.getOfferId());

        return updateOrderResponseDTO;
    }

    @Override
    public FetchAllOrdersResponseDTO fetchAllOrders() {
        
        List<Order> orders = orderRepository.findAll();
        
        List<OrdersResponseDTO> orderResponseList = orders.stream()
            .map(this::convertToOrderResponseDTO)
            .collect(Collectors.toList());
        
        return new FetchAllOrdersResponseDTO(
            "collection",
            orderResponseList.size(),
            orderResponseList);
    }

    private OrdersResponseDTO convertToOrderResponseDTO(Order order) {
        OrdersResponseDTO orderResponseDTO = new OrdersResponseDTO();
        orderResponseDTO.setId(order.getId());
        orderResponseDTO.setEntity(order.getEntity());
        orderResponseDTO.setAmount(order.getAmount());
        orderResponseDTO.setCurrency(order.getCurrency());
        orderResponseDTO.setReceipt(order.getReceipt());
        orderResponseDTO.setNotes(order.getNotes());
        orderResponseDTO.setAmountPaid(order.getAmount_paid());
        orderResponseDTO.setAmountDue(order.getAmount_due());
        orderResponseDTO.setCreatedAt(order.getCreated_at());
        orderResponseDTO.setAttempts(order.getAttempts());
        orderResponseDTO.setStatus(order.getStatus());
        orderResponseDTO.setOfferId(order.getOfferId());
        
        //set token to null as it is not required in the response
        orderResponseDTO.setToken(null);

        return orderResponseDTO;
    }

    @Override
    public CollectionFetchAllExpandedPaymentResponse fetchAllOrdersWithPayments() {
        List<Order> orders = orderRepository.findAll();
        
        List<FetchAllExpandedPaymentsResponseDTO> orderResponseList = orders.stream()
            .map(this::convertToFetchAllExpandedPaymentsResponseDTO)
            .collect(Collectors.toList());
        
        return new CollectionFetchAllExpandedPaymentResponse(
            "collection",
            orderResponseList.size(),
            orderResponseList);
    }

    private FetchAllExpandedPaymentsResponseDTO convertToFetchAllExpandedPaymentsResponseDTO(Order order) {


        List<Payment> payments = paymentRepository.findByOrderId(order.getId());

        List<PaymentDTO> paymentDTOs = payments.stream()
            .map(this::convertToPaymentDTO)
            .collect(Collectors.toList());

        PaymentsCollectionDTO paymentsCollection =
                new PaymentsCollectionDTO(
                    "collection",
                    paymentDTOs.size(),
                    paymentDTOs
                );

        FetchAllExpandedPaymentsResponseDTO fetchAllExpandedPaymentsResponseDTO = new FetchAllExpandedPaymentsResponseDTO();

        fetchAllExpandedPaymentsResponseDTO.setId(order.getId());
        fetchAllExpandedPaymentsResponseDTO.setEntity(order.getEntity());

        fetchAllExpandedPaymentsResponseDTO.setAmount(order.getAmount());
        fetchAllExpandedPaymentsResponseDTO.setAmountPaid(order.getAmount_paid());
        fetchAllExpandedPaymentsResponseDTO.setAmountDue(order.getAmount_due());

        fetchAllExpandedPaymentsResponseDTO.setCurrency(order.getCurrency());

        fetchAllExpandedPaymentsResponseDTO.setReceipt(order.getReceipt());

        fetchAllExpandedPaymentsResponseDTO.setStatus(order.getStatus());

        fetchAllExpandedPaymentsResponseDTO.setAttempts(order.getAttempts());

        fetchAllExpandedPaymentsResponseDTO.setCreatedAt(order.getCreated_at());

        fetchAllExpandedPaymentsResponseDTO.setOfferId(order.getOfferId());

        fetchAllExpandedPaymentsResponseDTO.setNotes(order.getNotes());

        fetchAllExpandedPaymentsResponseDTO.setToken(null); // implement if token exists

        fetchAllExpandedPaymentsResponseDTO.setPayments(paymentsCollection);

        return fetchAllExpandedPaymentsResponseDTO;
    }

    private PaymentDTO convertToPaymentDTO(Payment payment) {

        PaymentDTO paymentDTO = new PaymentDTO();

        paymentDTO.setId(payment.getId());
        paymentDTO.setEntity(payment.getEntity());
        paymentDTO.setAmount(payment.getAmount());
        paymentDTO.setCurrency(payment.getCurrency());
        paymentDTO.setStatus(payment.getStatus());
        paymentDTO.setOrderId(payment.getOrderId());
        paymentDTO.setInvoiceId(null); // implement if invoiceId exists
        paymentDTO.setInternational(payment.getInternational());
        paymentDTO.setMethod(payment.getMethod());
        paymentDTO.setAmountRefunded(payment.getAmountRefunded());
        paymentDTO.setRefundStatus(payment.getRefundStatus());
        paymentDTO.setCaptured(payment.getCaptured());
        paymentDTO.setDescription(payment.getDescription());
        paymentDTO.setCardId(payment.getCardId());
        paymentDTO.setBank(payment.getBank());
        paymentDTO.setWallet(payment.getWallet());
        paymentDTO.setVpa(payment.getVpa());
        paymentDTO.setEmail(payment.getEmail());
        paymentDTO.setContact(payment.getContact());
        paymentDTO.setNotes(payment.getNotes());
        paymentDTO.setFee(payment.getFee());
        paymentDTO.setTax(payment.getTax());
        paymentDTO.setErrorCode(payment.getErrorCode());
        paymentDTO.setErrorDescription(payment.getErrorDescription());
        paymentDTO.setCreatedAt(payment.getCreatedAt());

        return paymentDTO;
    }

}

package com.sanket.razorpayorderservice.ServiceImpl;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sanket.razorpayorderservice.DTO.CardDTO;
import com.sanket.razorpayorderservice.DTO.CardPaymentCollectionDTO;
import com.sanket.razorpayorderservice.DTO.CardPaymentDTO;
import com.sanket.razorpayorderservice.DTO.CollectionFetchAllExpandedCardPaymentsResponse;
import com.sanket.razorpayorderservice.DTO.CollectionFetchAllExpandedPaymentResponse;
import com.sanket.razorpayorderservice.DTO.CreateOrderRequestDTO;
import com.sanket.razorpayorderservice.DTO.CreateOrderResponseDTO;
import com.sanket.razorpayorderservice.DTO.FetchAllExpandedCardPaymentsResponseDTO;
import com.sanket.razorpayorderservice.DTO.FetchAllExpandedPaymentsResponseDTO;
import com.sanket.razorpayorderservice.DTO.FetchAllOrdersResponseDTO;
import com.sanket.razorpayorderservice.DTO.FetchOrderPaymentsWrapperDTO;
import com.sanket.razorpayorderservice.DTO.FetchOrderWithIDResponseDTO;
import com.sanket.razorpayorderservice.DTO.FetchPaymentsResponseDTO;
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
        paymentDTO.setNotes(null); // implement if notes exist
        paymentDTO.setFee(payment.getFee());
        paymentDTO.setTax(payment.getTax());
        paymentDTO.setErrorCode(payment.getErrorCode());
        paymentDTO.setErrorDescription(payment.getErrorDescription());
        paymentDTO.setCreatedAt(payment.getCreatedAt());

        return paymentDTO;
    }

   @Override
    public CollectionFetchAllExpandedCardPaymentsResponse fetchAllOrdersWithPaymentsAndCard() {

        List<Order> orders = orderRepository.findAll();

        List<FetchAllExpandedCardPaymentsResponseDTO>
                orderDTOList = new ArrayList<>();

        for (Order order : orders) {

            FetchAllExpandedCardPaymentsResponseDTO orderDTO =
                    new FetchAllExpandedCardPaymentsResponseDTO();

            orderDTO.setId(order.getId());

            orderDTO.setEntity(order.getEntity());

            orderDTO.setAmount(order.getAmount());

            orderDTO.setAmountPaid(order.getAmount_paid());

            orderDTO.setAmountDue(order.getAmount_due());

            orderDTO.setCurrency(order.getCurrency());

            orderDTO.setReceipt(order.getReceipt());

            orderDTO.setStatus(order.getStatus());

            orderDTO.setAttempts(order.getAttempts());

            orderDTO.setNotes(order.getNotes());

            orderDTO.setCreatedAt(order.getCreated_at());

            // FETCH PAYMENTS

            List<Payment> payments =
                    paymentRepository
                            .findByOrderId(order.getId());

            List<CardPaymentDTO> paymentDTOList =
                    new ArrayList<>();

            for (Payment payment : payments) {

                CardPaymentDTO paymentDTO =
                        new CardPaymentDTO();

                paymentDTO.setId(payment.getId());

                paymentDTO.setEntity(payment.getEntity());

                paymentDTO.setAmount(payment.getAmount());

                paymentDTO.setCurrency(
                        payment.getCurrency());

                paymentDTO.setStatus(
                        payment.getStatus());

                paymentDTO.setMethod(
                        payment.getMethod());

                paymentDTO.setOrderId(
                        payment.getOrderId());

                paymentDTO.setDescription(
                        payment.getDescription());

                paymentDTO.setInternational(
                        payment.getInternational());

                paymentDTO.setRefundStatus(
                        payment.getRefundStatus());

                paymentDTO.setAmountRefunded(
                        payment.getAmountRefunded());

                paymentDTO.setCaptured(
                        payment.getCaptured());

                paymentDTO.setEmail(
                        payment.getEmail());

                paymentDTO.setContact(
                        payment.getContact());

                paymentDTO.setFee(
                        payment.getFee());

                paymentDTO.setTax(
                        payment.getTax());

                paymentDTO.setCreatedAt(
                        payment.getCreatedAt());

                paymentDTO.setCardId(
                        payment.getCardId());

                // CARD

                if (payment.getCard() != null) {

                    CardDTO cardDTO = new CardDTO();

                    cardDTO.setId(
                            payment.getCard().getId());

                    cardDTO.setEntity(
                            payment.getCard().getEntity());

                    cardDTO.setName(
                            payment.getCard().getName());

                    cardDTO.setLast4(
                            payment.getCard().getLast4());

                    cardDTO.setNetwork(
                            payment.getCard().getNetwork());

                    cardDTO.setType(
                            payment.getCard().getType());

                    cardDTO.setIssuer(
                            payment.getCard().getIssuer());

                    cardDTO.setInternational(
                            payment.getCard()
                                    .getInternational());

                    cardDTO.setEmi(
                            payment.getCard()
                                    .getEmi());

                    cardDTO.setSubType(
                            payment.getCard()
                                    .getSubType());

                    paymentDTO.setCard(cardDTO);
                }

                paymentDTOList.add(paymentDTO);
            }

            CardPaymentCollectionDTO paymentsCollection =
                    new CardPaymentCollectionDTO();

            paymentsCollection.setEntity("collection");

            paymentsCollection.setCount(
                    paymentDTOList.size());

            paymentsCollection.setItems(paymentDTOList);

            orderDTO.setPayments(paymentsCollection);

            orderDTOList.add(orderDTO);
        }

        CollectionFetchAllExpandedCardPaymentsResponse
                response =
                new CollectionFetchAllExpandedCardPaymentsResponse();

        response.setEntity("collection");

        response.setCount(orderDTOList.size());

        response.setItems(orderDTOList);

        return response;
    }

    @Override
    public FetchOrderWithIDResponseDTO fetchOrderWithID(String id) {
        
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        FetchOrderWithIDResponseDTO responseDTO = new FetchOrderWithIDResponseDTO();

        responseDTO.setId(order.getId());
        responseDTO.setEntity(order.getEntity());
        responseDTO.setAmount(order.getAmount());
        responseDTO.setAmountPaid(order.getAmount_paid());
        responseDTO.setAmountDue(order.getAmount_due());
        responseDTO.setCurrency(order.getCurrency());
        responseDTO.setReceipt(order.getReceipt());
        responseDTO.setOfferId(order.getOfferId());
        responseDTO.setOffers(null); // implement if offers exist
        responseDTO.setStatus(order.getStatus());
        responseDTO.setAttempts(order.getAttempts());
        responseDTO.setNotes(order.getNotes());
        responseDTO.setCreatedAt(order.getCreated_at());

        return responseDTO;
    }

    @Override
    public FetchOrderPaymentsWrapperDTO fetchOrderPaymentsWithID(String id) {

        // Check order exists
        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found with id: " + id));

        // Fetch all payments using order id
        List<Payment> payments = paymentRepository.findByOrderId(order.getId());

        // Convert Payment Entity -> DTO
        List<FetchPaymentsResponseDTO> paymentDTOs = payments.stream()
                .map(this::convertToPaymentDTO1)
                .collect(Collectors.toList());

        // Create Wrapper Response
        FetchOrderPaymentsWrapperDTO responseDTO =
                new FetchOrderPaymentsWrapperDTO();

        responseDTO.setEntity("collection");

        responseDTO.setCount(paymentDTOs.size());

        responseDTO.setItems(paymentDTOs);

        return responseDTO;
    }

    private FetchPaymentsResponseDTO convertToPaymentDTO1(Payment payment) {

        FetchPaymentsResponseDTO dto = new FetchPaymentsResponseDTO();

        dto.setId(payment.getId());

        dto.setEntity(payment.getEntity());

        dto.setAmount(payment.getAmount());

        dto.setCurrency(payment.getCurrency());

        dto.setStatus(payment.getStatus());

        dto.setOrderId(payment.getOrderId());

        dto.setInvoiceId(payment.getInvoiceId());

        dto.setInternational(payment.getInternational());

        dto.setMethod(payment.getMethod());

        dto.setAmountRefunded(payment.getAmountRefunded());

        dto.setRefundStatus(payment.getRefundStatus());

        dto.setCaptured(payment.getCaptured());

        dto.setDescription(payment.getDescription());

        dto.setCardId(payment.getCardId());

        dto.setBank(payment.getBank());

        dto.setWallet(payment.getWallet());

        dto.setVpa(payment.getVpa());

        dto.setEmail(payment.getEmail());

        dto.setContact(payment.getContact());

        dto.setNotes(payment.getNotes());

        dto.setFee(payment.getFee());

        dto.setTax(payment.getTax());

        dto.setErrorCode(payment.getErrorCode());

        dto.setErrorDescription(payment.getErrorDescription());

        dto.setErrorSource(payment.getErrorSource());

        dto.setErrorStep(payment.getErrorStep());

        dto.setErrorReason(payment.getErrorReason());

        dto.setCreatedAt(payment.getCreatedAt());

        return dto;
    }
} 

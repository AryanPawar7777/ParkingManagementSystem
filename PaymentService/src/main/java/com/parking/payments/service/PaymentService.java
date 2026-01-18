package com.parking.payments.service;

import com.parking.payments.dto.PaymentDto;
import com.parking.payments.irepository.IPaymentRepository;
import com.parking.payments.model.Payment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.parking.payments.dto.PaginatedResponse;
import org.springframework.beans.factory.annotation.Value;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final IPaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PaymentService(IPaymentRepository paymentRepository, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }

    // Make a payment
    public void makePayment(PaymentDto dto) {
        Payment payment = modelMapper.map(dto, Payment.class);
        paymentRepository.makePayment(payment);
    }

    // Get payment by ID
    public PaymentDto getPaymentById(Long id) {
        Payment payment = paymentRepository.getPaymentById(id);
        return payment != null ? modelMapper.map(payment, PaymentDto.class) : null;
    }

    // Get payments by reservation ID
    public List<PaymentDto> getPaymentsByReservationId(Long reservationId) {
        return paymentRepository.getPaymentsByReservationId(reservationId)
                .stream()
                .map(payment -> modelMapper.map(payment, PaymentDto.class))
                .collect(Collectors.toList());
    }

    // ✅ Get payments by user ID (for ParkingSlotService)
    public List<PaymentDto> getPaymentsByUserId(Long userId) {
        return paymentRepository.findByUserId(userId)
                .stream()
                .map(payment -> modelMapper.map(payment, PaymentDto.class))
                .collect(Collectors.toList());
    }

    // Get all payments
    public List<PaymentDto> getAllPayments() {
        return paymentRepository.getAllPayments()
                .stream()
                .map(payment -> modelMapper.map(payment, PaymentDto.class))
                .collect(Collectors.toList());
    }

    @Value("${com.parking.payments.pagination.size:10}")
private int defaultPageSize;

public PaginatedResponse<PaymentDto> getPaginatedPayments(int page, Integer size) {
    int pageSize = (size != null && size > 0) ? size : defaultPageSize;
    int offset = page * pageSize;

    int totalElements = paymentRepository.getTotalPaymentsCount();
    int totalPages = (int) Math.ceil((double) totalElements / pageSize);

    List<Payment> payments = paymentRepository.getPaymentsByPage(offset, pageSize);
    List<PaymentDto> paymentDtos = new ArrayList<>();
    for (Payment payment : payments) {
        paymentDtos.add(modelMapper.map(payment, PaymentDto.class));
    }

    return new PaginatedResponse<>(page, pageSize, totalPages, totalElements, paymentDtos);
}

}


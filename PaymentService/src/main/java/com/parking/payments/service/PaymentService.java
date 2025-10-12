package com.parking.payments.service;

import com.parking.payments.dto.PaymentDto;
import com.parking.payments.irepository.IPaymentRepository;
import com.parking.payments.model.Payment;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

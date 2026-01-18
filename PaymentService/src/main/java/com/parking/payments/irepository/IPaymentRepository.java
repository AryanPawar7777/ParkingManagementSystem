package com.parking.payments.irepository;

import com.parking.payments.model.Payment;
import java.util.List;

public interface IPaymentRepository {
    void makePayment(Payment payment);
    Payment getPaymentById(Long id);
    List<Payment> getPaymentsByReservationId(Long reservationId);
      List<Payment> getAllPayments();
      List<Payment> findByUserId(Long userId);
      
List<Payment> getPaymentsByPage(int offset, int limit);
int getTotalPaymentsCount();

}


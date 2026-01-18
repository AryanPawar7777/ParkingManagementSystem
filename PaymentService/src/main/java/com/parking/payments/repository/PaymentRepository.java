package com.parking.payments.repository;

import com.parking.payments.irepository.IPaymentRepository;
import com.parking.payments.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PaymentRepository implements IPaymentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PaymentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void makePayment(Payment payment) {
        String sql = "INSERT INTO payments (reservation_id, user_id, amount, status, method, timestamp) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                
                payment.getReservationId(),
                payment.getUserId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getMethod(),
                payment.getTimestamp()
        );
    }

    @Override
    public Payment getPaymentById(Long id) {
        String sql = "SELECT * FROM payments WHERE payment_id = ?";
        return jdbcTemplate.queryForObject(sql, this::mapRowToPayment, id);
    }

    @Override
    public List<Payment> getPaymentsByReservationId(Long reservationId) {
        String sql = "SELECT * FROM payments WHERE reservation_id = ?";
        return jdbcTemplate.query(sql, this::mapRowToPayment, reservationId);
    }

    @Override
    public List<Payment> getAllPayments() {
        String sql = "SELECT * FROM payments";
        return jdbcTemplate.query(sql, this::mapRowToPayment);
    }

    @Override
    public List<Payment> findByUserId(Long userId) {
        String sql = "SELECT * FROM payments WHERE user_id = ?";
        return jdbcTemplate.query(sql, this::mapRowToPayment, userId);
    }

    // Row mapper
    private Payment mapRowToPayment(ResultSet rs, int rowNum) throws SQLException {
        Payment payment = new Payment();
        payment.setPaymentId(rs.getLong("payment_id"));
        payment.setReservationId(rs.getLong("reservation_id"));
        payment.setUserId(rs.getLong("user_id")); // ✅ Include userId
        payment.setAmount(rs.getBigDecimal("amount"));
        payment.setStatus(rs.getString("status"));
        payment.setMethod(rs.getString("method"));       // ✅ Include method
        payment.setTimestamp(rs.getTimestamp("timestamp")); // ✅ Include timestamp
        return payment;
    }

    @Override
public List<Payment> getPaymentsByPage(int offset, int limit) {
    String sql = "SELECT * FROM payments LIMIT ? OFFSET ?";
    return jdbcTemplate.query(sql, new PaymentRowMapper(), limit, offset);
}

@Override
public int getTotalPaymentsCount() {
    String sql = "SELECT COUNT(*) FROM payments";
    return jdbcTemplate.queryForObject(sql, Integer.class);
}
}


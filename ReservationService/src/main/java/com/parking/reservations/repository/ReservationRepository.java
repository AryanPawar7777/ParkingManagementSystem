package com.parking.reservations.repository;

import com.parking.reservations.model.Reservation;
import com.parking.reservations.rowmapper.ReservationRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationRepository implements IReservationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Reservation> getAllReservations() {
        String sql = "SELECT * FROM reservations";
        return jdbcTemplate.query(sql, new ReservationRowMapper());
    }

    @Override
    public Reservation getReservationById(Long reservationId) {
        String sql = "SELECT * FROM reservations WHERE reservation_id = ?";
        return jdbcTemplate.queryForObject(sql, new ReservationRowMapper(), reservationId);
    }

    @Override
    public int addReservation(Reservation reservation) {
        String sql = "INSERT INTO reservations (user_id, slot_id, start_time, end_time, vehicle_number, status, total_amount) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                reservation.getUserId(),
                reservation.getSlotId(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getVehicleNumber(),
                reservation.getStatus(),
                reservation.getTotalAmount());
    }

    @Override
    public int deleteReservation(Long reservationId) {
        String sql = "DELETE FROM reservations WHERE reservation_id = ?";
        return jdbcTemplate.update(sql, reservationId);
    }

    @Override
public List<Reservation> getReservationsByPage(int offset, int limit) {
    String sql = "SELECT * FROM reservations LIMIT ? OFFSET ?";
    return jdbcTemplate.query(sql, new ReservationRowMapper(), limit, offset);
}

@Override
public List<Reservation> getReservationsByUserId(Long userId) {
    String sql = "SELECT * FROM reservations WHERE user_id = ?";
    return jdbcTemplate.query(sql, new ReservationRowMapper(), userId);
}


@Override
public int getTotalReservationsCount() {
    String sql = "SELECT COUNT(*) FROM reservations";
    return jdbcTemplate.queryForObject(sql, Integer.class);
}
}

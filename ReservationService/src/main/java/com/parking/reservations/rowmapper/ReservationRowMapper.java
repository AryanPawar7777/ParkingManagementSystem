package com.parking.reservations.rowmapper;

import com.parking.reservations.model.Reservation;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ReservationRowMapper implements RowMapper<Reservation> {

    @Override
    public Reservation mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reservation reservation = new Reservation();

        reservation.setReservationId(rs.getLong("reservation_id"));
        reservation.setUserId(rs.getLong("user_id"));
        reservation.setSlotId(rs.getLong("slot_id"));
        reservation.setStartTime(rs.getTimestamp("start_time"));
        reservation.setEndTime(rs.getTimestamp("end_time"));
        reservation.setVehicleNumber(rs.getString("vehicle_number"));
        reservation.setStatus(rs.getString("status"));
        reservation.setTotalAmount(rs.getDouble("total_amount"));

        return reservation;
    }
}

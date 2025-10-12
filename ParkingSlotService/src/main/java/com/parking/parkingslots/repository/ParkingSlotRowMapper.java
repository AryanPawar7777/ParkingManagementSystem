package com.parking.parkingslots.repository;

import org.springframework.jdbc.core.RowMapper;
import com.parking.parkingslots.model.ParkingSlot;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParkingSlotRowMapper implements RowMapper<ParkingSlot> {

    @Override
    public ParkingSlot mapRow(ResultSet rs, int rowNum) throws SQLException {
        ParkingSlot slot = new ParkingSlot();

        slot.setSlotId(rs.getLong("slot_id"));
        slot.setSlotNumber(rs.getString("slot_number"));
        slot.setAvailable(rs.getBoolean("is_available"));
        slot.setVehicleType(rs.getString("vehicle_type"));

        return slot;
    }
}
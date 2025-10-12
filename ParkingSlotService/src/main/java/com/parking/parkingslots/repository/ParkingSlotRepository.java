package com.parking.parkingslots.repository;

import com.parking.parkingslots.irepository.IParkingSlotRepository;
import com.parking.parkingslots.model.ParkingSlot;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Repository(value = "ParkingSlotRepository")
public class ParkingSlotRepository implements IParkingSlotRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ParkingSlotRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ParkingSlot> getAllParkingSlots() {
        List<ParkingSlot> slots;
        String sql = "SELECT * FROM parking_slots";
        try {
            slots = jdbcTemplate.query(sql, new ParkingSlotRowMapper());
        } catch (Exception e) {
            System.out.println("Error fetching parking slots: " + e.getMessage());
            slots = new ArrayList<>();
        }
        return slots;
    }

    @Override
    public ParkingSlot getParkingSlotById(Long slotId) {
        String sql = "SELECT * FROM parking_slots WHERE slot_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new ParkingSlotRowMapper(), slotId);
        } catch (Exception e) {
            System.out.println("Error fetching slot by ID: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean isSlotAvailable(String slotNumber) {
        String sql = "SELECT is_available FROM parking_slots WHERE slot_number = ?";
        try {
            return jdbcTemplate.queryForObject(sql, Boolean.class, slotNumber);
        } catch (Exception e) {
            System.out.println("Error checking slot availability: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void addParkingSlot(ParkingSlot slot) {
        String sql = "INSERT INTO parking_slots (slot_number, is_available, vehicle_type) VALUES (?, ?, ?)";
        try {
            jdbcTemplate.update(sql, slot.getSlotNumber(), slot.isAvailable(), slot.getVehicleType());
        } catch (Exception e) {
            System.out.println("Error adding parking slot: " + e.getMessage());
        }
    }

    @Override
    public void updateSlotAvailability(Long slotId, boolean isAvailable) {
        String sql = "UPDATE parking_slots SET is_available = ? WHERE slot_id = ?";
        try {
            jdbcTemplate.update(sql, isAvailable, slotId);
        } catch (Exception e) {
            System.out.println("Error updating slot availability: " + e.getMessage());
        }
    }
}

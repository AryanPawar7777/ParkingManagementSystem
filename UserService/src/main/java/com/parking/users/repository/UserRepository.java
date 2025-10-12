package com.parking.users.repository;

import com.parking.users.irepository.IUserRepository;
import com.parking.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository implements IUserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public User getUserById(Long userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), userId);
    }

    @Override
    public User getUserByVehicleNumber(String vehicleNumber) {
        String sql = "SELECT * FROM users WHERE vehicle_number = ?";
        return jdbcTemplate.queryForObject(sql, new UserRowMapper(), vehicleNumber);
    }

    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users (name, email, phone, vehicle_number) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getEmail(), user.getPhone(), user.getVehicleNumber());
    }

    // ✅ NEW: Get all users assigned to a specific parking slot
    @Override
    public List<User> findBySlotId(Long slotId) {
        String sql = """
            SELECT u.* 
            FROM users u
            JOIN reservations r ON u.user_id = r.user_id
            WHERE r.slot_id = ?
        """;
        return jdbcTemplate.query(sql, new UserRowMapper(), slotId);
    }
}

package com.parking.authservice.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.parking.authservice.dto.SignupDto;

@Repository
public class AuthRepository {

    private final JdbcTemplate _JdbcTemplate;

    @Autowired
    public AuthRepository(JdbcTemplate jdbcTemplate) {
        this._JdbcTemplate = jdbcTemplate;
    }

    public boolean SignUp(SignupDto cred, StringBuffer error) {
        try {
            String query = "INSERT INTO user (u_name, u_email, u_password) VALUES (?, ?, ?)";
            _JdbcTemplate.update(query, cred.get_Name(), cred.get_Email(), cred.get_Password());
            return true;
        } catch (Exception ex) {
            error.append(ex.getMessage());
            System.out.println("Error during user signup: " + error);
            return false;
        }
    }

    public boolean getPasswordFromEmail(String email, StringBuffer password, StringBuffer error) {
        try {
            String query = "SELECT u_password FROM user WHERE u_email = ?";
            password.append(_JdbcTemplate.queryForObject(query, String.class, email));
            return true;
        } catch (Exception ex) {
            error.append(ex.getMessage());
            System.out.println("Error during user authentication: " + error);
            return false;
        }
    }
}

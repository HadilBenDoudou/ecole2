package com.ecole.ecole.auth;
import com.ecole.ecole.auth.User;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }





    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public boolean checkPassword(User user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }


    public void updatePassword(String email, String newPassword) {
        // Find user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
        // Update password and save
        user.setPassword(passwordEncoder.encode(newPassword)); // Encrypt the new password
        userRepository.save(user);
    }






    public void deleteUserById(Long id) {
        System.out.println("Service: Attempting to delete user with ID: " + id); // Log l'ID
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        System.out.println("Service: User with ID: " + id + " deleted successfully");
    }


    private BCryptPasswordEncoder passwordEncoderr = new BCryptPasswordEncoder();

    public void saveUser(User user) {
        user.setPassword(passwordEncoderr.encode(user.getPassword()));
        // sauvegarder l'utilisateur avec le mot de passe haché
    }








}

package com.ecole.ecole.auth;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender; // Assurez-vous que ceci est importé
import org.springframework.mail.SimpleMailMessage;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailSender mailSender; // Déclaration de mailSender

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        try {
            User newUser = userService.registerUser(user);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.findByEmail(loginRequest.getEmail());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (userService.checkPassword(user, loginRequest.getPassword())) {
                // Generate token or session and return it
                return ResponseEntity.ok(new LoginResponse("Login successful", user));
            }
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @Autowired
    private EmailService emailService; // Assuming you have an email service
// Assuming you have a user service

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        String email = request.getEmail();

        // Generate a new temporary password
        String newPassword = generateNewPassword(); // Implement this method

        // Update the user's password in the database (make sure to encrypt it)
        userService.updatePassword(email, newPassword); // Implement this method to update password in DB

        // Send the new password to the user's email
        emailService.sendPasswordResetEmail(email, newPassword); // Implement this method to send email

        return ResponseEntity.ok().body("New password has been sent to your email.");
    }

    private final PasswordGenerator passwordGenerator = new PasswordGenerator(); // Create an instance

    // Your other methods...

    private String generateNewPassword() {
        return passwordGenerator.generateNewPassword(); // Use the generator
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        System.out.println("Attempting to delete user with ID: " + id); // Log l'ID
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; // Injection du BCryptPasswordEncoder

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest userUpdateRequest) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userUpdateRequest.getUsername());
                    user.setEmail(userUpdateRequest.getEmail());

                    // Hachage du mot de passe si fourni
                    if (userUpdateRequest.getPassword() != null && !userUpdateRequest.getPassword().isEmpty()) {
                        user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
                    }

                    User updatedUser = userRepository.save(user);
                    return ResponseEntity.ok(updatedUser);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public void deleteUserById(Long id) {
        System.out.println("Service: Attempting to delete user with ID: " + id);
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
        System.out.println("Service: User with ID: " + id + " deleted successfully");
    }


    @PutMapping("/update-profile")
    public ResponseEntity<User> updateProfile(@RequestBody UserUpdateDTO userUpdateDTO, @RequestParam Long userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body(null);  // Retourner une erreur si userId est manquant
        }

        User user = userRepository.findById(userId).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        // Mettre à jour les données de l'utilisateur
        user.setUsername(userUpdateDTO.getUsername());
        user.setEmail(userUpdateDTO.getEmail());

        // Sauvegarder les modifications
        User updatedUser = userRepository.save(user);

        return ResponseEntity.ok(updatedUser);
    }

}

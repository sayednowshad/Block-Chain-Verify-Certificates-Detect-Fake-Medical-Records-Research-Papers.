package com.blockchain.blockchain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final String ADMIN_PASSWORD = "secret123"; // ✅ Change this

    @PostMapping("/verify-password")
    public ResponseEntity<?> verifyPassword(@RequestBody PasswordRequest req) {
        if (ADMIN_PASSWORD.equals(req.getPassword())) {
            return ResponseEntity.ok("Verified");
        }
        return ResponseEntity.status(401).body("Invalid Password");
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }
        // TODO: Save file logic
        return ResponseEntity.ok("File uploaded successfully!");
    }
}

class PasswordRequest {
    private String password;
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}

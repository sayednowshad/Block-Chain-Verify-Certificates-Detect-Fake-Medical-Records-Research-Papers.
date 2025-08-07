package com.blockchain.blockchain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ownerName;
    private String email;
    private String fileHash;
    private String timestamp;

    // Getters
    public Long getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getEmail() {
        return email;
    }

    public String getFileHash() {
        return fileHash;
    }

    public String getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setOwnerName(String name) {
        this.ownerName = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

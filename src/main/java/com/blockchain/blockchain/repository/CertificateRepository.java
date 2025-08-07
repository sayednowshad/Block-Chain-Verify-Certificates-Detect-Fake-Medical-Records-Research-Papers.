package com.blockchain.blockchain.repository;

import com.blockchain.blockchain.model.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<Certificate, Long> {
    // No need to redeclare save(), it's already provided
}

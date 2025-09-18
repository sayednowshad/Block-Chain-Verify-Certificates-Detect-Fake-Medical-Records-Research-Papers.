package com.blockchain.blockchain.controller;

import com.blockchain.blockchain.model.Block;
import com.blockchain.blockchain.model.Certificate;
import com.blockchain.blockchain.repository.CertificateRepository;
import com.blockchain.blockchain.service.BlockchainService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/certificates")
public class CertificateController {

    @Autowired
    private BlockchainService blockchainService;

    @Autowired
    private CertificateRepository repo;

    // 🔄 UPLOAD endpoint
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping
    public ResponseEntity<?> uploadCertificate(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("email") String email
    ) throws Exception {

        String fileHash = DigestUtils.sha256Hex(file.getBytes());

        Block block = blockchainService.addBlock(fileHash);

        Certificate cert = new Certificate();
        cert.setOwnerName(name);
        cert.setEmail(email);
        cert.setFileHash(fileHash);
        cert.setTimestamp(block.getTimestamp());
        repo.save(cert);
        return ResponseEntity.ok("✅ Certificate Block Added!");
    }

    // 🔍 VERIFY endpoint
    @PostMapping("/verify")
    public ResponseEntity<?> verifyCertificate(@RequestParam("file") MultipartFile file) throws Exception {
        String fileHash = DigestUtils.sha256Hex(file.getBytes());

        boolean isValid = blockchainService.verifyData(fileHash);

        if (isValid) {
            return ResponseEntity.ok("✔️ Certificate is VALID");
        } else {
            return ResponseEntity.status(403).body(" ❌ Doc is Not Verified");
        }
    }

    // 📄 LIST BLOCKS endpoint
    @GetMapping("/blocks")
    public ResponseEntity<?> getAllBlocks() {
        return ResponseEntity.ok(blockchainService.getBlockchain());
    }
}

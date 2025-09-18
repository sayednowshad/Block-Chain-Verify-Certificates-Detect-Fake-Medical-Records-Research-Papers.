package com.blockchain.blockchain.service;

import com.blockchain.blockchain.model.Block;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlockchainService {

    private final List<Block> blockchain = new ArrayList<>();

    public BlockchainService() {
        blockchain.add(createGenesisBlock());
    }

    // Genesis block (block 0) 1
    private Block createGenesisBlock() {
        return new Block(0, "Genesis Block", "0");
    }

    // Add new block with data 2
    public Block addBlock(String data) {
        Block lastBlock = blockchain.get(blockchain.size() - 1);
        Block newBlock = new Block(blockchain.size(), data, lastBlock.getHash());
        blockchain.add(newBlock);
        return newBlock;
    }

    // Verify if input data matches any block's stored data hash (not just sha256 of raw data) 3
    public boolean verifyData(String data) {
        // Check whether this data is already present in any block
        return blockchain.stream()
                .anyMatch(block -> block.getData().equals(data));
    }

    // Verify entire blockchain integrity 4
    public boolean isChainValid() {
        for (int i = 1; i < blockchain.size(); i++) {
            Block current = blockchain.get(i);
            Block previous = blockchain.get(i - 1);

            // Recalculate current hash
            String recalculatedHash = current.calculateHash();

            if (!current.getHash().equals(recalculatedHash)) {
                return false; // Current hash mismatch
            }

            if (!current.getPreviousHash().equals(previous.getHash())) {
                return false; // Previous hash link broken
            }
        }
        return true;
    }

    public List<Block> getBlockchain() {
        return blockchain;
    }
}

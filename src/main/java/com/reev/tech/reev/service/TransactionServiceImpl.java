package com.reev.tech.reev.service;

import com.reev.tech.reev.model.Transaction;
import com.reev.tech.reev.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repository;
    @Override
    public String startNewTransaction() {
        Transaction transaction = new Transaction();
        transaction.setStatus("started");
        transaction = repository.save(transaction);
        return "Transaction started with ID: " + transaction.getId();
    }

    @Override
    public String commitTransaction(Long transactionId) {
        Transaction transaction = repository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setStatus("committed");
        repository.save(transaction);
        return "Transaction committed: " + transactionId;
    }

    @Override
    public String rollbackTransaction(Long transactionId) {
        Transaction transaction = repository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setStatus("rolled back");
        repository.save(transaction);
        return "Transaction rolled back: " + transactionId;
    }
}

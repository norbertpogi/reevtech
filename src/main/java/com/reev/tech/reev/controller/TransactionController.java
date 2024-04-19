package com.reev.tech.reev.controller;

import com.reev.tech.reev.model.Transaction;
import com.reev.tech.reev.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/start-transaction")
    public ResponseEntity<String> startTransaction() {
        return ResponseEntity.ok(transactionService.startNewTransaction());
    }
    @PostMapping("/commit-transaction/{transactionId}")
    public ResponseEntity<String> commitTransaction(@PathVariable Long transactionId) {
        return ResponseEntity.ok(transactionService.commitTransaction(transactionId));
    }
    @PostMapping("/rollback-transaction/{transactionId}")
    public ResponseEntity<String> rollbackTransaction(@PathVariable Long transactionId) {
        return ResponseEntity.ok(transactionService.rollbackTransaction(transactionId));
    }

}

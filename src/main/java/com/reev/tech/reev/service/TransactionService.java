package com.reev.tech.reev.service;

public interface TransactionService {

    String startNewTransaction();

    String commitTransaction(Long transactionId);

    String rollbackTransaction(Long transactionId);
}

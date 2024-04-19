package com.reev.tech.reev.service;

import com.reev.tech.reev.model.Transaction;
import com.reev.tech.reev.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {

    @Mock
    private TransactionRepository repository;

    @InjectMocks
    private TransactionServiceImpl service;
    @Test
    public void testStartNewTransaction() {
        Transaction mockTransaction = new Transaction();
        mockTransaction.setId(1L);
        mockTransaction.setStatus("started");
        when(repository.save(any(Transaction.class))).thenReturn(mockTransaction);
        String response = service.startNewTransaction();
        assertThat(response).contains("Transaction started with ID: 1");
    }
    @Test
    public void testCommitTransaction() {
        Transaction mockTransaction = new Transaction();
        mockTransaction.setId(1L);
        mockTransaction.setStatus("started");
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(mockTransaction));
        when(repository.save(any(Transaction.class))).thenAnswer(i -> i.getArguments()[0]);

        String response = service.commitTransaction(1L);
        assertThat(response).isEqualTo("Transaction committed: 1");
    }

    @Test
    public void testRollbackTransaction() {
        Transaction mockTransaction = new Transaction();
        mockTransaction.setId(1L);
        mockTransaction.setStatus("started");
        when(repository.findById(1L)).thenReturn(java.util.Optional.of(mockTransaction));
        when(repository.save(any(Transaction.class))).thenAnswer(i -> i.getArguments()[0]);

        String response = service.rollbackTransaction(1L);
        assertThat(response).isEqualTo("Transaction rolled back: 1");
    }
}

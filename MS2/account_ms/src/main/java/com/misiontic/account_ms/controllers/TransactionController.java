package com.misiontic.account_ms.controllers;

import com.misiontic.account_ms.exceptions.AccountNotFoundException;
import com.misiontic.account_ms.models.Account;
import com.misiontic.account_ms.models.Transaction;
import com.misiontic.account_ms.repositories.AccountRepository;
import com.misiontic.account_ms.repositories.TransactionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
    // Attributes
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    // Constructor
    public TransactionController(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    // Methods
    @GetMapping("/transactions/{usernameOrigin}")
    List<Transaction> getTransactionByUsernameOrigin(String usernameOrigin){
        return transactionRepository.getByUsernameOrigin(usernameOrigin);
    }

    @PostMapping("/transaction/")
    Transaction newTransaction(@RequestBody Transaction transaction){
        Account accountDestiny = accountRepository.findById(transaction.getUsernameDestiny()).orElse(null);
        Account accountOrigin = accountRepository.findById(transaction.getUsernameOrigin()).orElse(null);
        if(accountOrigin == null || accountDestiny == null){
            throw new AccountNotFoundException("La cuenta origen o la cuenta destino no existen");
        }
        accountDestiny.setBalance(accountDestiny.getBalance() + transaction.getValue());
        accountOrigin.setBalance(accountOrigin.getBalance() - transaction.getValue());
        accountRepository.save(accountDestiny);
        accountRepository.save(accountOrigin);
        return transactionRepository.save(transaction);
    }
}

package com.nttdata.bootcamp.ms.bankaccount.service;

import com.nttdata.bootcamp.ms.bankaccount.entity.BankAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankAccountService {

    Flux<BankAccount> getAll();
    Mono<BankAccount> getAccountById(Integer accountId);
    Mono<BankAccount> saveAccount(BankAccount bankAccount);
    Mono<BankAccount> updateAccount(BankAccount bankAccount);
    Mono<BankAccount> deleteAccount(Integer accountId);
    Flux<BankAccount> getAccountByCustomerId(Integer customerId);
}

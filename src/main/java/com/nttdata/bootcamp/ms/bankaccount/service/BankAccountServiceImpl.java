package com.nttdata.bootcamp.ms.bankaccount.service;

import com.nttdata.bootcamp.ms.bankaccount.entity.BankAccount;
import com.nttdata.bootcamp.ms.bankaccount.repository.BankAccountRepository;
import com.nttdata.bootcamp.ms.bankaccount.repository.RedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Slf4j
public class BankAccountServiceImpl implements BankAccountService{

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private RedisRepository redisRepository;
    @Override
    public Flux<BankAccount> getAll(){
        return bankAccountRepository.findAll();
    }


    @Override
    public Mono<BankAccount> getAccountById(Integer accountId){

        Mono<BankAccount>  monoRedis = redisRepository.findById(accountId);
        return Objects.requireNonNullElseGet(monoRedis, () -> bankAccountRepository.findById(accountId));

    }

    @Override
    public Mono<BankAccount> saveAccount(BankAccount bankAccount){
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public Mono<BankAccount> updateAccount(BankAccount bankAccount){
        return bankAccountRepository.findById(bankAccount.getAccountId())
                .flatMap(newBankAccount -> {
                    newBankAccount.setAccountId(bankAccount.getAccountId());
                    newBankAccount.setAccountNumber(bankAccount.getAccountNumber());
                    newBankAccount.setAccountType(bankAccount.getAccountType());
                    newBankAccount.setCustomerId(bankAccount.getCustomerId());
                    newBankAccount.setAccountBalance(bankAccount.getAccountBalance());
                    newBankAccount.setTransactionLimit(bankAccount.getTransactionLimit());
                    newBankAccount.setMaintenanceFee(bankAccount.getMaintenanceFee());
                    newBankAccount.setTransactionFee(bankAccount.getTransactionFee());
                    return bankAccountRepository.save(newBankAccount);
                });
    }

    @Override
    public Mono<BankAccount> deleteAccount(Integer accountId){
        return bankAccountRepository.findById(accountId)
                .flatMap(bankAccount -> bankAccountRepository.delete(bankAccount).then(Mono.just(bankAccount)));
    }

    @Override
    public Flux<BankAccount> getAccountByCustomerId(String customerId){
        return bankAccountRepository.findAll()
                .filter(bankAccount -> bankAccount.getCustomerId().equals(customerId));

    }
}

package com.nttdata.bootcamp.ms.bankaccount.repository;

import com.nttdata.bootcamp.ms.bankaccount.entity.BankAccount;
import jdk.jshell.Snippet;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class RedisRepository {

    ReactiveRedisOperations<Integer, BankAccount> template;

    public RedisRepository(ReactiveRedisOperations<Integer, BankAccount> template) {
        this.template = template;
    }

    public Flux<BankAccount> findAll() {
        return template.<Integer, BankAccount>opsForHash().values(1);
    }

    public Mono<BankAccount> findById(Integer id) {
        return template.<Integer, BankAccount>opsForHash().get(1, id);
    }

    public Mono<BankAccount> save(BankAccount bankAccount) {
        if (bankAccount.getAccountId() != null) {
            Integer id = 1;
            bankAccount.setAccountId(1);
        }
        return template.<Integer, BankAccount>opsForHash().put(1, bankAccount.getAccountId(), bankAccount)
                .log()
                .map(p -> bankAccount);

    }

    public Mono<Void> deleteById(Integer id) {
        return template.<Integer, BankAccount>opsForHash().remove(1, id)
                .flatMap(p -> Mono.<Void>empty());
    }

    public Mono<Boolean> deleteAll() {
        return template.<Integer, BankAccount>opsForHash().delete(1);
    }

}

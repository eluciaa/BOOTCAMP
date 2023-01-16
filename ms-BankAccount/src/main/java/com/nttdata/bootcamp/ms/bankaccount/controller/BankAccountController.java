package com.nttdata.bootcamp.ms.bankaccount.controller;

import com.nttdata.bootcamp.ms.bankaccount.BankAccountApplication;
import com.nttdata.bootcamp.ms.bankaccount.entity.BankAccount;
import com.nttdata.bootcamp.ms.bankaccount.service.BankAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping(value = "/bankaccount")
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    /**
     *
     * Obtener todas las cuentas bancarias
     * @return
     */
    @GetMapping
    public Flux<BankAccount> getAll(){
        return bankAccountService.getAll();
    }

    /**
     *
     * Obtene cuenta bancaria por Id
     * @param id
     * @return
     */
    @GetMapping ("/account/{id}")
    public Mono<ResponseEntity<BankAccount>> getAccountById(@PathVariable Integer id){
        return bankAccountService.getAccountById(id).map(response -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                .body(response))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    /**
     * Obtener cuenta por id de cliente
     * @param customerId
     * @return
     */
    @GetMapping ("/customer/{customerId}")
    public Flux<BankAccount> getAccountByCustomerId(@PathVariable Integer customerId){
        return bankAccountService.getAccountByCustomerId(customerId);
    }

    /**
     * Registrar cuenta bancaria
     * @param bankAccount
     * @return
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<BankAccount> saveAccount(@RequestBody BankAccount bankAccount){
        return bankAccountService.saveAccount(bankAccount);
    }

    /**
     * Modificar cuenta bancaria
     * @param bankAccount
     * @return
     */
    @PutMapping
    public Mono<BankAccount> updateAccount(@RequestBody BankAccount bankAccount){
        return bankAccountService.updateAccount(bankAccount);
    }

    /**
     * Eliminar cuenta bancaria
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<BankAccount> deleteAccount(@PathVariable Integer id){
        return bankAccountService.deleteAccount(id);
    }



}

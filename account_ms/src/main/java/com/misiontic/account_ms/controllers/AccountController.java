package com.misiontic.account_ms.controllers;

import com.misiontic.account_ms.models.Account;
import com.misiontic.account_ms.repositories.AccountRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class AccountController {
    // Attributes
    private final AccountRepository repository;

    // Constructor
    public AccountController(AccountRepository repository) {
        this.repository = repository;
    }


    // Methods
    /*
    OBTENER UNA CUENTA POR username

    Tipo: GET
    url: "/accounts/{username}"
    Path Variable: le mando el username
    Qué me debe devolver: Cuenta
     */

    @GetMapping("/accounts/{username}")
    public Optional<Account> getAccount(@PathVariable String username){
        return this.repository.findById(username);
    }

    /*
    CREAR UNA CUENTA POR username

    Tipo: POST
    url: "/accounts"
    Body: le mando datos de la cuenta
    Qué me debe devolver: Cuenta
     */

    @PostMapping("/accounts")
    public Account newAccount(@RequestBody Account account){
        return this.repository.save(account);
    }

    /*
    BORRAR UNA CUENTA POR id: username

    tipo: DELETE
    url: "/accounts/{username}"
    recibe: username
    como recibe: Path

    que retorna: NO
    */

    @DeleteMapping("/accounts/{username}")
    public void deleteAccount(@PathVariable String username){
        repository.deleteById(username);
    }

    /*
    ACTUALIZAR UNA CUENTA POR id

    tipo: PUT
    url: "/accounts/{username}"
    recibe: username, Account
    como recibe: Path, Body

    que retorna: Account
     */

    @PutMapping
    public Account updateAccount(@PathVariable String username, @RequestBody Account new_account){
        Account old_account = repository.findById(username).orElse(null);
        // ESTO ACTUALIZA EL OBJETO JAVA
        old_account.setLastChange(new_account.getLastChange());
        old_account.setBalance(new_account.getBalance());
        // ESTO SÍ ACTUALIZA LA BASE DE DATOS
        return repository.save(old_account);

    }
}

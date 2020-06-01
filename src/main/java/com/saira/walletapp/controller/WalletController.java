package com.saira.walletapp.controller;

import com.saira.walletapp.Service.ValidationErrorService;
import com.saira.walletapp.Service.WalletService;
import com.saira.walletapp.enitity.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wallet")
@CrossOrigin
public class WalletController {
  @Autowired
  private WalletService walletService;

@Autowired
  private ValidationErrorService validationService;
@GetMapping ResponseEntity<?> getAll()
{
    return new ResponseEntity<>(walletService.getAll(),HttpStatus.OK);
}
@GetMapping("/id")
public ResponseEntity<?> getById(@PathVariable Long id)
{
    return new ResponseEntity<>(walletService.getById(id),HttpStatus.OK);
}
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Wallet wallet, BindingResult result)
    {
         ResponseEntity errors= validationService.vsalidate(result);
         if(errors !=null) return errors;
        Wallet walletSaved = walletService.createOrUpdate(wallet);
        return new ResponseEntity<Wallet>(walletSaved,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Wallet wallet, BindingResult result)
    {
        ResponseEntity errors= validationService.vsalidate(result);
        if(errors !=null) return errors;
        wallet.setId(id);
        Wallet walletSaved = walletService.createOrUpdate(wallet);
        return new ResponseEntity<Wallet>(walletSaved,HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id)
    {
        walletService.delete(id);
return new ResponseEntity(HttpStatus.OK);
    }
}

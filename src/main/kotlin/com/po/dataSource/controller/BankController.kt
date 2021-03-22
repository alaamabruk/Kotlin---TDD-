package com.po.dataSource.controller

import com.po.model.Bank
import com.po.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/banks")
class BankController (private val service: BankService){

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNoSuchElementException(e:NoSuchElementException) : ResponseEntity<String> =
        ResponseEntity(e.message , HttpStatus.NOT_FOUND)


    @GetMapping
    fun getBanks() :Collection<Bank> = service.retriveBanks()

    @GetMapping("{accNum}")
    fun getBank(@PathVariable accNum:String ) : Bank = service.retriveBankbyAccNum(accNum)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveBank(@RequestBody bank:Bank ) : Bank = service.saveBank(bank)

    @PatchMapping
    fun updateBank(@RequestBody bank:Bank ) : Bank = service.updateBank(bank)

    @DeleteMapping("{accNum}")
    fun deleteBank(@PathVariable accNum: String ) : Unit = service.deleteBank(accNum)
}
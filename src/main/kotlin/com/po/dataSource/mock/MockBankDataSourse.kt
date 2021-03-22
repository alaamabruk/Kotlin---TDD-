package com.po.dataSource.mock

import com.po.dataSource.BankDataSourse
import com.po.model.Bank
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

@Repository
class MockBankDataSourse : BankDataSourse {


    val banks = mutableListOf(
    Bank("1234",1.7,0),
    Bank("3434", 3.5 , 4),
    Bank("666", 3.8 , 7)
    )
    override fun retriveBanks(): Collection<Bank> =banks


    override fun retriveBankByAccNum(accNum: String): Bank {
        return banks.firstOrNull() { it.accountNum == accNum }
        ?: throw NoSuchElementException("Bank not found with this AccountNumber $accNum")
    }

    override fun createBank(bank: Bank): Bank {
        if (banks.any{it.accountNum == bank.accountNum}){
            throw IllegalArgumentException("bank with this account number ${bank.accountNum} is already exists")
        }
        banks.add(bank)
        return bank
    }

    override fun updateBank(bank: Bank): Bank {
        var cuurentBank =  banks.firstOrNull() {it.accountNum == bank.accountNum}
            ?: throw NoSuchElementException("bank with this account number ${bank.accountNum} is alreaady exists")

        banks.remove(cuurentBank)
        banks.add(bank)
        return bank
         }

    override fun deleteBank(accNum: String) {
        var deleteBank =  banks.firstOrNull() {it.accountNum == accNum}
            ?: throw NoSuchElementException("bank with this account number ${accNum} is alreaady exists")
        banks.remove(deleteBank)
    }
}
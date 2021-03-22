package com.po.service

import com.po.dataSource.BankDataSourse
import com.po.model.Bank
import org.springframework.stereotype.Service

@Service
class BankService (val banskDataSourse: BankDataSourse){

    fun retriveBanks(): Collection<Bank> {
    return banskDataSourse.retriveBanks()
    }

    fun retriveBankbyAccNum(accNum: String): Bank {
        return banskDataSourse.retriveBankByAccNum(accNum)
    }

    fun saveBank(bank: Bank): Bank {
           return banskDataSourse.createBank(bank)
    }

    fun updateBank(bank: Bank): Bank {
        return  banskDataSourse.updateBank(bank)
    }

    fun deleteBank(accNum: String) {
        return  banskDataSourse.deleteBank(accNum)
    }

}
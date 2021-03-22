package com.po.dataSource

import com.po.model.Bank


interface BankDataSourse {

    fun retriveBanks () : Collection<Bank>
    fun retriveBankByAccNum(accNum: String): Bank
    fun createBank(bank: Bank): Bank
    fun updateBank(bank: Bank): Bank
    fun deleteBank(accNum: String)

}
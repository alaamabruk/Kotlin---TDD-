package com.po.service
import com.po.dataSource.BankDataSourse
import com.po.dataSource.mock.MockBankDataSourse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BankServiceTest{

    @Test
    fun `should call bank data source `(){
        //given

        //when
        val bankDataSourse : BankDataSourse = mockk(relaxed = true)
        val bankservice = BankService(bankDataSourse)

    //    every { bankDataSourse.retriveBanks() } returns emptyList()
        val banks = bankservice.retriveBanks()

        //then
      verify (exactly = 1){ bankDataSourse.retriveBanks() }
    }
}
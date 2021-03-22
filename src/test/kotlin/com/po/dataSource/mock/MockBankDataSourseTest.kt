package com.po.dataSource.mock

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MockBankDataSourseTest{

    private val mockDataSourse = MockBankDataSourse()

    @Test
    fun `should get all banks`(){
        //given
        //when
        val banks = mockDataSourse.retriveBanks()

        //then
        assertThat(banks).isNotEmpty
        assertThat(banks.size).isGreaterThan(2)
    }

    @Test
    fun `should provide some mock data `(){
        //given
        //when
        val banks = mockDataSourse.retriveBanks()

        //then
        assertThat(banks).allMatch { it.accountNum.isNotBlank() }
        assertThat(banks).allMatch { it.trust != 0.0 }
        assertThat(banks).anyMatch { it.transactionFees != 0 }

    }
}
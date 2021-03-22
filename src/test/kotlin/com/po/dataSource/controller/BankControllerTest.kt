package com.po.dataSource.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.jsonpath.JsonPath
import com.po.dataSource.BankDataSourse
import com.po.model.Bank
import com.po.service.BankService
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.*
import org.springframework.web.bind.annotation.ExceptionHandler

@SpringBootTest
@AutoConfigureMockMvc
internal class BankControllerTest @Autowired constructor(
    val mockMvc : MockMvc ,
    val objectmapper : ObjectMapper
){

    private var baseURL : String = "/api/banks"


    @Test
    fun `should get all bank data usning controller`(){
        //when then
        mockMvc.get(baseURL)
            .andDo { print() }
            .andExpect { status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$[0].accountNum") {value(1234)}
            }
    }

    @Test
    fun `should get a bank with it's accountNumber `(){
        //given

        val accountNum :String = "3434"

        //when then
        mockMvc.get("$baseURL/$accountNum")
            .andDo { print() }
            .andExpect { status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.trust") {value(3.5)}
            }
    }


    @Test
    fun `should return NOT FOUND with this AccNum`(){
        //given

        val accountNum :String = "yyy"

        //when then
        mockMvc.get("$baseURL/$accountNum")
            .andDo { print() }
            .andExpect { status { isNotFound() }
            }
    }

    @Test
    fun `should save a bank with it's AccNum`(){
        //given

        val newBank :Bank = Bank("1010",1.5,12)

        //when
        val responsePost = mockMvc.post(baseURL){
            contentType = MediaType.APPLICATION_JSON
            content = objectmapper.writeValueAsString(newBank)
        }

        // then
            responsePost
            .andDo { print() }
            .andExpect { status { isCreated()
                content { contentType(MediaType.APPLICATION_JSON)
                json(objectmapper.writeValueAsString(newBank))
                }

               // jsonPath("$.accountNum") {value(1010)}
               //jsonPath("$.trust") {value(1.5)}
               //jsonPath("$.transactionFees") {value(12)}
                }
            }
            }



    @Test
    fun `should upadte an existing bank with it's AccNum`(){
        //given

        val updatedBank :Bank = Bank("666",1.5,12)

        //when
        val responsePost = mockMvc.patch(baseURL){
            contentType = MediaType.APPLICATION_JSON
            content = objectmapper.writeValueAsString(updatedBank)
        }

        // then
        responsePost
            .andDo { print() }
            .andExpect { status { isOk()}
                content { contentType(MediaType.APPLICATION_JSON)
                    json(objectmapper.writeValueAsString(updatedBank))
                }
            }

         mockMvc.get("$baseURL/${updatedBank.accountNum}")
             .andExpect { status { isOk() }
             content { json(objectmapper.writeValueAsString(updatedBank)) }
             }

    }

    @Test
    fun `should returnNot Found for a bank with it's AccNum`(){
        //given

        val updatedBank :Bank = Bank("notFound",1.5,12)

        //when
        val responsePost = mockMvc.patch(baseURL){
            contentType = MediaType.APPLICATION_JSON
            content = objectmapper.writeValueAsString(updatedBank)
        }

        // then
        responsePost
            .andDo { print() }
            .andExpect { status { isNotFound()
              }
            }
      }

    @Test
    fun `should delete a bank with it's AccNum`(){
        //given

        val accNum :String = "666"

        //when
        mockMvc.delete("$baseURL/$accNum")
            .andDo { print() }
            .andExpect { status { isOk() }
              //  content { contentType(MediaType.APPLICATION_JSON) }
              //  jsonPath("$.trust") {value(3.5)}
            }
    }

}
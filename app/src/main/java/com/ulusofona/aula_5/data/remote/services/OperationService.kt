package com.ulusofona.aula_5.data.remote.services

import com.ulusofona.aula_5.data.local.entities.Operation
import com.ulusofona.aula_5.data.remote.responses.LoginResponse
import com.ulusofona.aula_5.data.remote.responses.OperationsResponse
import retrofit2.Response
import retrofit2.http.*

interface OperationService {

    @GET("operations")
    suspend fun getOperations(@Header("Authorization") token : String ): Response<List<Operation>>

    @POST("operations")
    suspend fun postOperation(@Header("Authorization") token : String, @Body body: Operation ): Response<OperationsResponse>

    @DELETE("operations")
    suspend fun deleteOperation(@Header("Authorization") token : String) : Response<OperationsResponse>
}
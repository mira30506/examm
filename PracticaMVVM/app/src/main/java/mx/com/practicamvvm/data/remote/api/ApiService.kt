package mx.com.practicamvvm.data.remote.api

import mx.com.practicamvvm.data.remote.response.FactsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET("/v1/gobmx.facts")
    suspend fun getValidateMessagesVersion(): Response<FactsResponse>
}
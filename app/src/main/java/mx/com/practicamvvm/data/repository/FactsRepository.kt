package mx.com.practicamvvm.data.repository

import mx.com.practicamvvm.data.local.mappers.FactsMapper
import mx.com.practicamvvm.data.local.model.FactsModel
import mx.com.practicamvvm.data.remote.api.ApiService
import mx.com.practicamvvm.sys.utils.Resource
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FactsRepository @Inject constructor(private val service: ApiService) {
    suspend fun getFacts(): Resource<FactsModel> {
        return try {
            val response = service.getValidateMessagesVersion()
            if (response.isSuccessful) {
                val model = response.body()?.let { FactsMapper().map(it) }
                Resource.success(model!!)
            } else
                throw HttpException(response)
        } catch (e: Exception) {
            Resource.error(e.toString())
        }
    }
}
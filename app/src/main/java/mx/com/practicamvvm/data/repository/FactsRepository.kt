package mx.com.practicamvvm.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import mx.com.practicamvvm.data.local.database.dao.DaoFacts
import mx.com.practicamvvm.data.local.database.entity.Facts
import mx.com.practicamvvm.data.local.database.mappers.FactsEntityMapper
import mx.com.practicamvvm.data.local.mappers.FactsMapper
import mx.com.practicamvvm.data.local.model.ResultsModel
import mx.com.practicamvvm.data.remote.api.ApiService
import mx.com.practicamvvm.data.remote.response.FactsResponse
import mx.com.practicamvvm.sys.utils.Resource
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FactsRepository @Inject constructor(
    private val service: ApiService,
    private val dao: DaoFacts
) {
    suspend fun getPage(size:Int,page:Int)= withContext(Dispatchers.IO){
        var result= dao.getPage(size,size*page)
        var list= mutableListOf<ResultsModel>()
        result.forEach {
            list.add(FactsMapper().map(it))
        }
        list
    }
    suspend fun getFacts() = withContext(Dispatchers.IO) {
        try {
            val response = service.getValidateMessagesVersion()
            if (response.isSuccessful) {
                insertFacts(response.body()!!)
                Resource.success(true)
            } else
                throw HttpException(response)
        } catch (e: Exception) {
            Resource.error(e.toString())
        }
    }
    suspend fun insertFacts(response: FactsResponse) {
        dao.delete()
        var list = mutableListOf<Facts>()
        response.results.forEach {
            list.add(FactsEntityMapper().map(it))
        }
        dao.insert(list)
    }

    suspend fun getSearch(search: String) = withContext(Dispatchers.IO) {
        var s="%"+search+"%"
        var result=dao.getSearch(search)
        result
    }


}
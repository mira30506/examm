package mx.com.practicamvvm.data.local.database.mappers

import mx.com.practicamvvm.data.local.database.entity.Facts
import mx.com.practicamvvm.data.local.mappers.Mapper
import mx.com.practicamvvm.data.remote.response.ResultsResponse

class FactsEntityMapper :Mapper<ResultsResponse,Facts> {
    override suspend fun map(input: ResultsResponse): Facts {
        return Facts(
            Id=input.Id,
            dataset = input.dataset,
            dateInsert = input.dateInsert,
            fact = input.fact,
            operations = input.operations,
            organization = input.organization,
            resource = input.resource,
            createdAt = input.createdAt,
            columns = input.columns,
            slug = input.slug,
            url = input.url
        )
    }

}
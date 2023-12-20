package mx.com.practicamvvm.data.local.mappers

import mx.com.practicamvvm.data.local.model.FactsModel
import mx.com.practicamvvm.data.local.model.PaginationModel
import mx.com.practicamvvm.data.local.model.ResultsModel
import mx.com.practicamvvm.data.remote.response.FactsResponse

class FactsMapper : Mapper<FactsResponse, FactsModel> {
    override suspend fun map(input: FactsResponse): FactsModel {
        return FactsModel(
            pagination = PaginationModel(
                pageSize = input.pagination?.pageSize,
                page = input.pagination?.page,
                total = input.pagination?.total
            ),
            results = input.results.map {
                ResultsModel(
                    Id = it.Id,
                    dateInsert = it.dateInsert,
                    slug = it.slug,
                    columns = it.columns,
                    fact = it.fact,
                    organization = it.organization,
                    resource = it.resource,
                    url = it.url,
                    operations = it.operations,
                    dataset = it.dataset,
                    createdAt = it.createdAt
                )
            }.toMutableList()
        )
    }
}
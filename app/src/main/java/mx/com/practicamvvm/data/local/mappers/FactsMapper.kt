package mx.com.practicamvvm.data.local.mappers

import mx.com.practicamvvm.data.local.database.entity.Facts
import mx.com.practicamvvm.data.local.model.ResultsModel

class FactsMapper : Mapper<Facts, ResultsModel> {
    override suspend fun map(input: Facts): ResultsModel {
        return ResultsModel(
            Id = input.Id,
            dateInsert = input.dateInsert,
            slug = input.slug,
            columns = input.columns,
            fact = input.fact,
            organization = input.organization,
            resource = input.resource,
            url = input.url,
            operations = input.operations,
            dataset = input.dataset,
            createdAt = input.createdAt
        )
    }

}
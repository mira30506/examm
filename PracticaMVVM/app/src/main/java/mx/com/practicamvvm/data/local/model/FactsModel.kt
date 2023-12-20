package mx.com.practicamvvm.data.local.model

data class FactsModel(
    var pagination: PaginationModel? = PaginationModel(),
    var results: List<ResultsModel>
)

data class PaginationModel(
    var pageSize: Int? = null,
    var page: Int? = null,
    var total: Int? = null
)

data class ResultsModel(
    var Id: String? = null,
    var dateInsert: String? = null,
    var slug: String? = null,
    var columns: String? = null,
    var fact: String? = null,
    var organization: String? = null,
    var resource: String? = null,
    var url: String? = null,
    var operations: String? = null,
    var dataset: String? = null,
    var createdAt: Int? = null
)
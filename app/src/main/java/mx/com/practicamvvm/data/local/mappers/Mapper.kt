package mx.com.practicamvvm.data.local.mappers

interface Mapper<I, O> {
    suspend fun map(input: I): O
}
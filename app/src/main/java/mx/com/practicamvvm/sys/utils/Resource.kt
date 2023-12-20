package mx.com.practicamvvm.sys.utils

data class Resource<out T>(
    val status: Status,
    val data: T? = null,
    val message: String? = null){
    companion object{
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }
        fun <T> error(msg: String? = null): Resource<T> {
            return Resource(Status.ERROR, message = msg)
        }
        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}
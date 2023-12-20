package mx.com.practicamvvm.data.repository

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import mx.com.practicamvvm.sys.utils.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor() {
    suspend fun registerUser(email: String, password: String): Resource<Boolean> {
        return try {
            val response = Firebase.auth.createUserWithEmailAndPassword(email, password)
            response.await()
            Resource.success(response.isSuccessful)
        } catch (e: Exception) {
            Resource.error(e.toString())
        }
    }

    suspend fun authUser(email: String, password: String):Resource<Boolean> {
        return try {
            val response = Firebase.auth.signInWithEmailAndPassword(email, password)
            response.await()
            Resource.success(response.isSuccessful)
        } catch (e: Exception){
            Resource.error(e.toString())
        }
    }
}
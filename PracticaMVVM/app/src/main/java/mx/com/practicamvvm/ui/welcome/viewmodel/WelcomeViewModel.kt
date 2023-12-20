package mx.com.practicamvvm.ui.welcome.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import mx.com.practicamvvm.data.repository.UserRepository
import mx.com.practicamvvm.sys.utils.Resource
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    val resultLogin = MutableLiveData<Resource<Boolean>>()
    fun getAuthUser(email: String, password: String) {
        viewModelScope.launch {
            resultLogin.value = Resource.loading()
            withContext(Dispatchers.IO) {
                resultLogin.postValue(repository.authUser(email, password))
            }
        }
    }
}
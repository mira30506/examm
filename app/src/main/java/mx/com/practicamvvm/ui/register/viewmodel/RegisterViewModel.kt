package mx.com.practicamvvm.ui.register.viewmodel

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
class RegisterViewModel @Inject constructor(private val repository: UserRepository): ViewModel() {
    private var resultRegister=MutableLiveData<Resource<Boolean>>()
    fun registerUser(email:String,password:String){
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                resultRegister.postValue(repository.registerUser(email,password))
            }
        }
    }
}
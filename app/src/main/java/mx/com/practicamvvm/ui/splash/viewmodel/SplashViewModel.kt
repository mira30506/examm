package mx.com.practicamvvm.ui.splash.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import mx.com.practicamvvm.data.repository.FactsRepository
import mx.com.practicamvvm.sys.utils.Resource
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(private val repository: FactsRepository): ViewModel(){
    val resultLiveData=MutableLiveData<Resource<Boolean>>()
    fun getFacts(){
        viewModelScope.launch {
            resultLiveData.value=Resource.loading()
            delay(5000)
            resultLiveData.value=repository.getFacts()
        }
    }
}
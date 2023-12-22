package mx.com.practicamvvm.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.com.practicamvvm.data.local.model.ResultsModel
import mx.com.practicamvvm.data.repository.FactsRepository
import mx.com.practicamvvm.sys.GlobalConstants.Companion.SIZE_PAGE
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(private val factsRepository: FactsRepository):ViewModel() {
    val factsLiveData=MutableLiveData<List<ResultsModel>>()
    var count=0

    fun getPage(){
        viewModelScope.launch {
            factsLiveData.value=factsRepository.getPage(SIZE_PAGE,count)
            count++
        }
    }
}
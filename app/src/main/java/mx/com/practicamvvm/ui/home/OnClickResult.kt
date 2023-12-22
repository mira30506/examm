package mx.com.practicamvvm.ui.home

import mx.com.practicamvvm.data.local.model.ResultsModel

interface OnClickResult {
    fun setOnClickListener(result:ResultsModel)
}
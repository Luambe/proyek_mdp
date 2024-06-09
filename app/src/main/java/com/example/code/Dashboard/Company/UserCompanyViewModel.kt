package com.example.code.Dashboard.Company

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.code.ManageApp
import com.example.code.data.source.model.Company
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class UserCompanyViewModel : ViewModel(){
    private val companyRepository = ManageApp.companyRepository
    private val _companies = MutableLiveData<List<Company>>(listOf())
    private val ioScope:CoroutineScope = CoroutineScope(Dispatchers.IO)

    val companies:LiveData<List<Company>>
        get() = _companies

    fun getCompanies(force:Boolean = false){
        ioScope.launch {
//            _companies.value = companyRepository.getAllCompanies(force)
            _companies.postValue(companyRepository.getAllCompanies(force))
        }
    }
}
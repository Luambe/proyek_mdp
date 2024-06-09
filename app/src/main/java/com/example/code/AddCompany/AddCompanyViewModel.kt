package com.example.code.AddCompany

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.Company
import kotlinx.coroutines.launch

class AddCompanyViewModel : ViewModel() {
    private val companyRepository = ManageApp.companyRepository
    private val _company = MutableLiveData<Company?>(null)
    private val _status:MutableLiveData<String> = MutableLiveData<String>("idle")
    val company:LiveData<Company?>
        get() = _company
    val status: LiveData<String>
        get() = _status

    fun getCompany(id:Int){
        viewModelScope.launch {
//            _post.value = postRepository.getPostById(id)
            _company.postValue(companyRepository.getCompanyById(id))
        }
    }

    fun createCompany(company: Company){
        _status.value = "processing"
        viewModelScope.launch{
            companyRepository.createCompany(company)
            _status.postValue("success")
        }
    }

}

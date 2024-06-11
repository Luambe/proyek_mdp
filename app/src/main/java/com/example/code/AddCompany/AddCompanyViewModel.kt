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

    fun getCompany(id:String){
        viewModelScope.launch {
//            _company.value = postRepository.getPostById(id)
            _company.postValue(companyRepository.getCompanyById(id))
        }
    }

    fun createCompany(companyName: String, ownerId: String, privateKey: String){
        _status.value = "processing"
        viewModelScope.launch{
            println(companyName)
            println(ownerId)
            println(privateKey)
            companyRepository.createCompany(companyName, ownerId, privateKey)
            _status.postValue("success")
        }
    }

}

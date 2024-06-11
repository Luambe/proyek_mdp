package com.example.code.CompanyDashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.Company
import kotlinx.coroutines.launch

class CompanyDashboardViewModel : ViewModel() {
    private val companyRepository = ManageApp.companyRepository
    private val _company = MutableLiveData<Company>()
    private val _status = MutableLiveData<String>()
    private val _announcement = MutableLiveData<String>()

    val company: LiveData<Company>
        get() = _company

    val status: LiveData<String>
        get() = _status

    val announcement: LiveData<String>
        get() = _announcement

    fun getAnnouncement(companyId: String){
        viewModelScope.launch {
            _company.postValue(companyRepository.getCompanyById(companyId))
            _announcement.postValue(company.value?.announcement.toString())
        }
    }
}
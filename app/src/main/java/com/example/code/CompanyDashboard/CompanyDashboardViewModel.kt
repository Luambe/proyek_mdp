package com.example.code.CompanyDashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.Company
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CompanyDashboardViewModel : ViewModel() {
    private val companyRepository = ManageApp.companyRepository
    private val _company = MutableLiveData<Company?>()
    private val _status = MutableLiveData<String>()
    private val _announcement = MutableLiveData<String>()

    val company: MutableLiveData<Company?>
        get() = _company

    val status: LiveData<String>
        get() = _status

    val announcement: LiveData<String>
        get() = _announcement

    fun getAnnouncement(companyId: String){
        viewModelScope.launch {
            val company = withContext(Dispatchers.IO) {
                companyRepository.getCompanyById(companyId)
            }
            println("coba munculin get announcementnya : $company")
            _company.postValue(company)
            _announcement.postValue(company?.announcement.toString())
        }
    }
}
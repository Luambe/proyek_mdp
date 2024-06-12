package com.example.code.AttendanceMenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.Company
import com.example.code.data.source.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AttendanceMenuViewModel : ViewModel() {
    private val companyRepository = ManageApp.companyRepository
    private val _company = MutableLiveData<Company?>(null)
    private val _status = MutableLiveData<String>()
    private val _announcement = MutableLiveData<String>()

    val company: LiveData<Company?>
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
            _company.postValue(company)
            _announcement.postValue(company?.announcement.toString())
        }
    }

    fun updateAnnouncement(companyId: String, announcementText: String){
        _status.value = "processing"
        viewModelScope.launch {
            val company = withContext(Dispatchers.IO) {
                companyRepository.getCompanyById(companyId)
            }
            _company.postValue(company)
            companyRepository.updateCompany(company?.companyId.toString(), company?.companyName.toString(), company?.ownerId.toString(), company?.privateKey.toString(), announcementText)
            _status.postValue("success")
        }
    }


    //COMPANY
    private val _companies = MutableLiveData<List<Company>>()

    val companies: LiveData<List<Company>>
        get() = _companies

    fun getCompany(id:String){
        viewModelScope.launch {
            _company.postValue(companyRepository.getCompanyById(id))
        }
    }


    //USER
    private val userRepository = ManageApp.userRepository
    private val _user = MutableLiveData<User>() // Ubah tipe data menjadi MutableLiveData<List<User>>
    val user: LiveData<User> // Ubah tipe LiveData menjadi List<User>
        get() = _user

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?>
        get() = _error

    fun getUserById(userId: String) {
        viewModelScope.launch {
            _user.postValue(userRepository.getUserById(userId))
        }
    }
}
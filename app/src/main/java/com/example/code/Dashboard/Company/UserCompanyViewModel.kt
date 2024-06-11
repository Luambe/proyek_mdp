package com.example.code.Dashboard.Company

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code.ManageApp
import com.example.code.data.source.model.Company
import com.example.code.data.source.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class UserCompanyViewModel : ViewModel(){
    private val companyRepository = ManageApp.companyRepository
    private val _companies = MutableLiveData<List<Company>>()
    private val _status = MutableLiveData<String>()

    val companies:LiveData<List<Company>>
        get() = _companies

    val status:LiveData<String>
        get() = _status

    fun getCompanies(force:Boolean = false){
        viewModelScope.launch {
//            _companies.value = companyRepository.getAllCompanies(force)
            _companies.postValue(companyRepository.getAllCompanies(force))
        }
    }

//    fun createCompanies(
//        companyName: String,
//        ownerId: String,
//        privateKey: String
//    ) {
//        viewModelScope.launch {
//            try {
//                companyRepository.createCompany(companyName, ownerId, privateKey)
//                _status.postValue("success")
//            } catch (e: Exception) {
//                // Tangani pengecualian di sini, contohnya:
//                _status.postValue("error: ${e.message}")
//            }
//        }
//    }

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
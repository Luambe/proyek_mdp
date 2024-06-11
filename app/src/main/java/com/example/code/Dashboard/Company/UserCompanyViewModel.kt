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
import kotlinx.coroutines.withContext

class UserCompanyViewModel : ViewModel(){
    //COMPANY
    private val companyRepository = ManageApp.companyRepository
    private val _companies = MutableLiveData<List<Company>>()
    private val _company = MutableLiveData<Company?>(null)
    private val _status = MutableLiveData<String>()

    val companies:LiveData<List<Company>>
        get() = _companies

    val status:LiveData<String>
        get() = _status

    fun getCompanies(force:Boolean = false){
        viewModelScope.launch {
            _companies.postValue(companyRepository.getAllCompanies(force))
        }
    }

    val company:LiveData<Company?>
        get() = _company

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
            withContext(Dispatchers.IO) {
                companyRepository.createCompany(companyName, ownerId, privateKey)
                userRepository.joinToCompany(ownerId, privateKey)
            }
            _status.postValue("success")
        }
    }

    fun joinCompany(employeeId: String, privateKey: String){
        _status.value = "processing"
        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                userRepository.joinToCompany(employeeId, privateKey)
            }
            _status.postValue("success")
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
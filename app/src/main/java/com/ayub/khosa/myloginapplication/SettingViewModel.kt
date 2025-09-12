package com.ayub.khosa.myloginapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.myloginapplication.model.USER
import com.ayub.khosa.myloginapplication.room.MainActivityRepository
import com.ayub.khosa.myloginapplication.utils.NetworkHelper
import com.ayub.khosa.myloginapplication.utils.PrintLogs
import kotlinx.coroutines.launch


class SettingViewModel(private val repository: MainActivityRepository, private val networkHelper: NetworkHelper) :
    ViewModel() {


    var user = MutableLiveData<USER>()
    val errorMessage = MutableLiveData<String>()

    val passMessage = MutableLiveData<String>()

    val _is_busy = MutableLiveData<Boolean>()

    init {
        PrintLogs.printD("SettingViewModel init")

    }


    fun onsetting_user_loginClicked(email: String, password: String) {

        _is_busy.value = true
        PrintLogs.printD("SettingViewModel  onsetting_user_loginClicked")
        PrintLogs.printD("SettingViewModel  email " + email)
        PrintLogs.printD("SettingViewModel password " + password)


        viewModelScope.launch {

            try {
                if (!email.isEmpty() && !password.isEmpty()) {
                    if (networkHelper.isNetworkConnected()) {
                        val response = repository.getLoginUser(
                            email, password
                        )

                        PrintLogs.printD(" onResponse Success :  " + response.response)
                        PrintLogs.printD(" onResponse Success :  " + response.data)
                        PrintLogs.printD(" onResponse Success :  " + response.error)
                        if (response.response == "Success") {

                            PrintLogs.printD(" onResponse Success data email :  " + response.data.email_id)
                            PrintLogs.printD(" onResponse Success data first_name :  " + response.data.first_name)
                            PrintLogs.printD(" onResponse Success data last_name :  " + response.data.last_name)
                            PrintLogs.printD(" onResponse Success data user_id :  " + response.data.user_id)
                            PrintLogs.printD(" onResponse Success data password :  " + response.data.password)
                            PrintLogs.printD(" onResponse Success data tokencode :  " + response.data.tokenCode)


                            user.postValue(response.data)
                            addUserinDB(response.data)
                            passMessage.value = "OK" //goto next fragment

                        } else {
                            errorMessage.value = response.error
                        }
                    }else{
                        errorMessage.value = "No internet"
                    }
                } else {
                    errorMessage.value = "Exception  user email or password"
                    PrintLogs.printD("Exception  user email or password")

                }
                _is_busy.value = false
            } catch (e: Exception) {
                errorMessage.value = e.message
                PrintLogs.printD("Exception  " + e.message)
                _is_busy.value = false
            }
        }


    }

    fun onsetting_user_is_logged_inClicked() {
        _is_busy.value = true

        viewModelScope.launch @androidx.annotation.RequiresPermission(android.Manifest.permission.ACCESS_NETWORK_STATE) {

            try {
                if (networkHelper.isNetworkConnected()) {
                    val response = repository.is_logged_in()
                    PrintLogs.printD(" onResponse Success :  " + response.response)
                    PrintLogs.printD(" onResponse Success :  " + response.data)
                    PrintLogs.printD(" onResponse Success :  " + response.error)
                    if (response.response == "Success") {
                        PrintLogs.printD(" onResponse Success  data :  " + response.data)

                        passMessage.value = "OK" //goto next fragment
                    } else {
                        errorMessage.value = response.error
                    }
                }else{
                    errorMessage.value = "No Internet ..."
                }
                _is_busy.value = false

            } catch (e: Exception) {
                PrintLogs.printD("onFailure: ${e.message}")
                errorMessage.value = e.message
                _is_busy.value = false
            }
        }
    }


    fun addUserinDB(user: USER) {
        _is_busy.value = true
        try {
            if (repository.fetchUSERByName(user.email_id, user.password) != null) {
                repository.updateUSERinDB(user)
            } else {
                repository.insertUSERinDB(user)
            }
            _is_busy.value = false
        } catch (e: Exception) {
            errorMessage.postValue(e.message)
            _is_busy.value = false
            PrintLogs.printD("Exception: ${e.message}")
        }
    }

    fun onsetting_user_signup_Clicked(
        email: String,
        password: String,
        firstname: String,
        lastname: String
    ) {


        _is_busy.value = true
        PrintLogs.printD("SettingViewModel  onsetting_user_signup_Clicked")
        PrintLogs.printD("SettingViewModel  email " + email)
        PrintLogs.printD("SettingViewModel password " + password)
        PrintLogs.printD("SettingViewModel firstname " + firstname)
        PrintLogs.printD("SettingViewModel lastname " + lastname)


        viewModelScope.launch {

            try {
                if (!email.isEmpty() && !password.isEmpty() && !firstname.isEmpty() && !lastname.isEmpty()) {
                    if (networkHelper.isNetworkConnected()) {
                        val response = repository.getSignupUser(
                            email, password, firstname, lastname
                        )

                        PrintLogs.printD(" onResponse Success :  " + response.response)
                        PrintLogs.printD(" onResponse Success :  " + response.data)
                        PrintLogs.printD(" onResponse Success :  " + response.error)
                        if (response.response == "Success") {

                            PrintLogs.printD(" onResponse Success data  :  " + response.data)

                            //  `$verify_url` =  "https://ayubkhosa.com/ecommerce-website-master/verify.php?id=' . \$id . '&code=' . \$code"
                            errorMessage.value = " " + response.data
                            user.postValue(response.data)
                            addUserinDB(response.data)

                            passMessage.value = "OK" //goto next fragment

                        } else {
                            errorMessage.value = response.error
                        }
                    }else{
                        errorMessage.value=" No Internet ..."
                    }
                } else {
                    errorMessage.value = "Exception  user email or password"
                    PrintLogs.printD("Exception  user email or password")

                }
                _is_busy.value = false
            } catch (e: Exception) {
                errorMessage.value = e.message
                PrintLogs.printD("Exception  " + e.message)
                _is_busy.value = false
            }
        }


    }

    fun onsetting_user_forgetpassword_Clicked(email: String) {

        _is_busy.value = true
        PrintLogs.printD("SettingViewModel  onsetting_user_forgetpassword_Clicked")
        PrintLogs.printD("SettingViewModel  email " + email)




        viewModelScope.launch {

            try {
                if (!email.isEmpty()) {
                    if (networkHelper.isNetworkConnected()) {
                        val response = repository.getforgetpasswordUser(email)

                        PrintLogs.printD(" onResponse Success :  " + response.response)
                        PrintLogs.printD(" onResponse Success :  " + response.data)
                        PrintLogs.printD(" onResponse Success :  " + response.error)
                        if (response.response == "Success") {

                            PrintLogs.printD(" onResponse Success data  :  " + response.data)

                            //  `$verify_url` =  "https://ayubkhosa.com/ecommerce-website-master/verify.php?id=' . \$id . '&code=' . \$code"
                            passMessage.value = "OK" //goto next fragment

                        } else {
                            errorMessage.value = response.error
                        }
                    }else{
                        errorMessage.value=" No Intenet...."
                    }
                } else {
                    errorMessage.value = "Exception  user email  "
                    PrintLogs.printD("Exception  user email  ")

                }
                _is_busy.value = false
            } catch (e: Exception) {
                errorMessage.value = e.message
                PrintLogs.printD("Exception  " + e.message)
                _is_busy.value = false
            }
        }
    }

}

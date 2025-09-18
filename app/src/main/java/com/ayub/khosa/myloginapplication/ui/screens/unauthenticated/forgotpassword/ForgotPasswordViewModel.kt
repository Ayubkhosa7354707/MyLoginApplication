package com.ayub.khosa.myloginapplication.ui.screens.unauthenticated.forgotpassword

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.myloginapplication.room.MainActivityRepository
import com.ayub.khosa.myloginapplication.utils.NetworkHelper
import com.ayub.khosa.myloginapplication.utils.PrintLogs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ForgotPasswordViewModel @Inject constructor(
    private val repository: MainActivityRepository,
    private val networkHelper: NetworkHelper
) :
    ViewModel() {

    private val _uiStatetextValue = MutableStateFlow("")
    val textValue: StateFlow<String> = _uiStatetextValue
    fun updateState(newValue: String) {
        _uiStatetextValue.value = newValue
        PrintLogs.printD(" ForgotPasswordViewModel updateState "+newValue)
    }



    fun forgotPasswordViewModel_user_forgetpassword_Clicked(email: String) {

        PrintLogs.printD("ForgotPasswordViewModel   forgetpassword_Clicked")
        PrintLogs.printD("ForgotPasswordViewModel  email " + email)
        updateState("--")



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
                             updateState("   "+response.data)
                        } else {
                            updateState( response.error)
                        }
                    } else {
                        updateState(" No Internet .... ")
                    }
                } else {

                    updateState(" Email is empty")
                    PrintLogs.printD("Exception  user email  ")

                }
            } catch (e: Exception) {
                updateState(" Exception : "+e.message)
                PrintLogs.printD("Exception  " + e.message)
            }
        }
    }



}
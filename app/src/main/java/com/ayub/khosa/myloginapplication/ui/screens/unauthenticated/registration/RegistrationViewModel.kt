package com.ayub.khosa.myloginapplication.ui.screens.unauthenticated.registration

import androidx.compose.animation.AnimatedContentScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavBackStackEntry
import com.ayub.khosa.myloginapplication.model.USER
import com.ayub.khosa.myloginapplication.room.MainActivityRepository
import com.ayub.khosa.myloginapplication.utils.NetworkHelper
import com.ayub.khosa.myloginapplication.utils.PrintLogs
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class RegistrationViewModel @Inject constructor(
    private val repository: MainActivityRepository,
    private val networkHelper: NetworkHelper
) :
    ViewModel() {



  //  var user = MutableLiveData<USER>()
    private val _uiStatetextValue = MutableStateFlow("")
    val textValue: StateFlow<String> = _uiStatetextValue

    fun updateState(newValue: String) {
        _uiStatetextValue.value = newValue
    }
    init {
        PrintLogs.printD("RegistrationViewModel init")
    }


    fun RegistrationViewModel_user_signup_Clicked(
        email: String,
        password: String,
        firstname: String,
        lastname: String
    ) {


        PrintLogs.printD("RegistrationViewModel  onsetting_user_signup_Clicked")
        PrintLogs.printD("RegistrationViewModel  email " + email)
        PrintLogs.printD("RegistrationViewModel password " + password)
        PrintLogs.printD("RegistrationViewModel firstname " + firstname)
        PrintLogs.printD("RegistrationViewModel lastname " + lastname)


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

                         //   user.postValue(response.data)
                            addUserinDB(response.data)


                            updateState("User Created ... Please verify your Email " )

                        } else {
                            updateState(" "+response.error)
                        }
                    } else {
                        updateState("No Internet ... " )
                    }
                } else {
                    PrintLogs.printD("Please Enter user email , password  , first name and last name to sing up")

                    updateState("Please Enter user email , password  , first name and last name to sign up" )


                }
            } catch (e: Exception) {
                updateState("Exception  " + e.message )
                PrintLogs.printD("Exception  " + e.message)

            }
        }


    }


    fun addUserinDB(user: USER) {
        try {
            if (repository.fetchUSERByName(user.email_id, user.password) != null) {
                repository.updateUSERinDB(user)
            } else {
                repository.insertUSERinDB(user)
            }
        } catch (e: Exception) {

            updateState("Exception "+e.message)
            PrintLogs.printD("Exception: ${e.message}")
        }
    }


}
package com.ayub.khosa.myloginapplication.ui.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.myloginapplication.room.MainActivityRepository
import com.ayub.khosa.myloginapplication.utils.NetworkHelper
import com.ayub.khosa.myloginapplication.utils.PrintLogs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel@Inject constructor(
    private val repository: MainActivityRepository,
    private val networkHelper: NetworkHelper
) :
    ViewModel() {
    //     var user = MutableLiveData<USER>()
    private val _uiStatetextValue = MutableStateFlow("")
    val textValue: StateFlow<String> = _uiStatetextValue
    fun updateState(newValue: String) {
        _uiStatetextValue.value = newValue
        PrintLogs.printD(" DashboardViewModel updateState "+newValue)
    }

    init {
        PrintLogs.printD("DashboardViewModel init")
    }


    fun user_logout(email: String) {
        updateState("")

         PrintLogs.printD("user_logout  email " + email)


        viewModelScope.launch {
            try {
            if (networkHelper.isNetworkConnected()) {
                val response = repository.userlogout(email)
                PrintLogs.printD(" onResponse Success :  " + response.response)
                PrintLogs.printD(" onResponse Success :  " + response.data)
                PrintLogs.printD(" onResponse Success :  " + response.error)
                if (response.response == "Success") {
                    PrintLogs.printD(" onResponse Success data  :  " + response.data)
                    updateState(response.data.toString())
                } else {
                    updateState(response.error.toString())

                }


            }else{
                updateState("No internet ....  " )
                PrintLogs.printD("No internet ....   "  )
            }
        }  catch (e: Exception) {

            updateState("Exception  " + e.message)
            PrintLogs.printD("Exception  " + e.message)

        }}
    }


}
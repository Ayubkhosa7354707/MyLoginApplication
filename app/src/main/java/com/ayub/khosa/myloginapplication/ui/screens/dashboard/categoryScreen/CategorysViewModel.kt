package com.ayub.khosa.myloginapplication.ui.screens.dashboard.categoryScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayub.khosa.myloginapplication.model.CATEGORY
import com.ayub.khosa.myloginapplication.model.ListCATEGORYS
import com.ayub.khosa.myloginapplication.room.MainActivityRepository
import com.ayub.khosa.myloginapplication.utils.NetworkHelper
import com.ayub.khosa.myloginapplication.utils.PrintLogs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class CategorysViewModel
@Inject constructor(
    private val repository: MainActivityRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {


    private var mycategorys: ArrayList<CATEGORY> = ArrayList<CATEGORY>()
    private var _tasks = MutableStateFlow(mycategorys)
    val tasks: ArrayList<CATEGORY>
        get() = _tasks.value


    fun getCategorysItems(): List<CATEGORY> {

        return tasks
    }

    private val errorMessage = mutableStateOf<String>("")


    fun geterrorMessage(): String {
        PrintLogs.printD(" geterrorMessage  error --> " + errorMessage.value)
        return errorMessage.value!!
    }


    fun seterrorMessage(error: String) {
        errorMessage.value = error
        PrintLogs.printD(" seterrorMessage  error --> " + error)
        PrintLogs.printD(" seterrorMessage  error value --> " + errorMessage.value)
    }

    private val _is_busy = mutableStateOf<Boolean>(false)


    fun get_is_busy(): Boolean {
        PrintLogs.printD("get_is_busy   " + _is_busy.value)
        return _is_busy.value!!
    }

    fun set_is_busy(b: Boolean) {
        _is_busy.value = b
        PrintLogs.printD("set_is_busy   " + _is_busy.value)
    }

    fun isNetworkConnected(): Boolean {
        if (networkHelper.isNetworkConnected()) {
            return true
        } else {
            return false
        }
    }

    fun onClickCallgetAllCategorys() {
        PrintLogs.printD("onClickCallgetAllCategorys  ")
        set_is_busy(true)
        viewModelScope.launch {
            try {
                if (networkHelper.isNetworkConnected()) {

                    seterrorMessage("Iternet is OKay")
                    val response = repository.getAllCategorys()
                    PrintLogs.printD(" onResponse Success :  " + response.response)
                    PrintLogs.printD(" onResponse Success :  " + response.data)
                    PrintLogs.printD(" onResponse Success :  " + response.error)

                    if (response.response == "Success") {
                        val data = kotlin.collections.ArrayList<CATEGORY>()
                        response.data.categorys.forEach { it ->
                            PrintLogs.printD(" id of category : " + it.id)
                            PrintLogs.printD(" name of category : " + it.name)
                            PrintLogs.printD(" img of category : " + it.img)

                            data.add(it)
                            addCategoryinDB(it)
                        }

                        PrintLogs.printD(" data.size ----  " + data.size)
                        var listcategory: ListCATEGORYS = ListCATEGORYS(data)
                        _tasks.value = data
                        PrintLogs.printD(" listcategory.categorys.size ----  " + listcategory.categorys.size)

                    } else {
                        seterrorMessage(response.error)
                    }
                } else {
                    seterrorMessage("No iternet")
                }
                set_is_busy(false)
            } catch (e: Exception) {
                seterrorMessage("Exception  " + e.message.toString())
                PrintLogs.printD("Exception  " + e.message)
                set_is_busy(false)
            }


        }


        PrintLogs.printD("onClickCallgetAllCategorys  ")
    }

    fun onClickCallgetCategory_DB() {

        set_is_busy(true)
        PrintLogs.printD(" -----------  onClickCallgetCategory_DB   -------------")

        try {
            val response = repository.getCategrys_DB()

            seterrorMessage("Database is OKay")
            response.categorys.forEach {
                PrintLogs.printD(" name of Categry : " + it.name)
                PrintLogs.printD(" Categry_id of Categry : " + it.category_id)
                PrintLogs.printD(" img of Categry : " + it.img)

            }

            _tasks.value = response.categorys
            set_is_busy(false)
        } catch (e: Exception) {
            seterrorMessage("Exception  " + e.message.toString())
            PrintLogs.printD("Exception  " + e.message)
            set_is_busy(false)
        }


        PrintLogs.printD(" -----------  onClickCallgetCategory_DB END  -------------")


    }

    fun addCategoryinDB(category: CATEGORY) {
        //   set_is_busy(true)
        try {
            if (repository.fetchCategoryByName(category.name, category.category_id) != null) {
                repository.updateCategoryinDB(category)
            } else {
                repository.insertCategoryinDB(category)
            }

        } catch (e: Exception) {
            seterrorMessage("Exception " + e.message.toString())

            PrintLogs.printD("Exception: ${e.message}")
        }
        //   set_is_busy(false)
    }


}

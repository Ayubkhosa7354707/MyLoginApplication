package com.ayub.khosa.myloginapplication.room

import android.content.Context
import android.os.AsyncTask
import com.ayub.khosa.emptyplaylistapplication.room.AppDatabase
import com.ayub.khosa.myloginapplication.api.ApiService
import com.ayub.khosa.myloginapplication.model.APIResponce
import com.ayub.khosa.myloginapplication.model.APIResponceListPRODUCTS
import com.ayub.khosa.myloginapplication.model.APIResponceUser
import com.ayub.khosa.myloginapplication.model.CATEGORY
import com.ayub.khosa.myloginapplication.model.ListCATEGORYS
import com.ayub.khosa.myloginapplication.model.ListPRODUCTS
import com.ayub.khosa.myloginapplication.model.PRODUCT
import com.ayub.khosa.myloginapplication.model.USER
import com.ayub.khosa.myloginapplication.utils.PrintLogs
import javax.inject.Inject


class MainActivityRepository @Inject constructor(
    context: Context,
    private val apiService: ApiService
) {


    var db: ShopDAO = AppDatabase.getInstance(context)?.shopDAO()!!


    fun insertUSERinDB(user: USER) {
        insertUSERAsyncTask(db).execute(user)
    }

    fun updateUSERinDB(user: USER) {
        db.updateUSER(user)
    }


    fun fetchUSERByName(email_id: String, password: String): USER {
        return db.fetchUserByName(email_id, password)
    }

    suspend fun getSignupUser(
        email: String,
        password: String,
        firstname: String,
        lastname: String
    ): APIResponceUser {

        return apiService.getSignup("btn-signup", email, password, firstname, lastname)

    }

    suspend fun getforgetpasswordUser(email: String): APIResponce {

        return apiService.getforgetpasswordUser("btn-forgetpasswor", email)

    }


    suspend fun getLoginUser(email: String, password: String): APIResponceUser =
        apiService.getLogin("btn-login", email, password)

    suspend fun is_logged_in(): APIResponce = apiService.is_logged_in("is_logged_in")
    suspend fun userlogout(email: String): APIResponce = apiService.userlogout("is_log_out", email)


    private class insertUSERAsyncTask(private val shopDAO: ShopDAO) :
        AsyncTask<USER, Void, Void>() {

        override fun doInBackground(vararg params: USER): Void? {
            shopDAO.insertUSER(params[0])
            return null
        }
    }


    suspend fun getAllProducts(): APIResponceListPRODUCTS {

        return apiService.get_ListPRODUCTS("ListPRODUCTS")
    }


    fun getProducts_DB(): ListPRODUCTS {

        val response: List<PRODUCT> = db.gelAllProducts()
        PrintLogs.printD("MainActivityRepository getProducts_DB  " + response.size)
        var data = ArrayList<PRODUCT>()


        for (i in response.indices) {
            data.add(response[i])

        }

        val listPRODUCTS: ListPRODUCTS = ListPRODUCTS(data)

        // val   apiResponceListPRODUCTS:APIResponceListPRODUCTS=APIResponceListPRODUCTS(listPRODUCTS,"Success","")
        return listPRODUCTS
    }

    fun insertProductinDB(product: PRODUCT) {
        insertAsyncTask(db).execute(product)
    }

    fun updateProductinDB(product: PRODUCT) {
        db.updateProduct(product)
    }

    fun deleteProductinDB(product: PRODUCT) {
        db.deleteProduct(product)
    }

    fun fetchByName(name: String, product_id: String): PRODUCT {
        return db.fetchByName(name = name, product_id = product_id)
    }

    fun fetchProductByName(name: String, product_id: String): PRODUCT {
        return db.fetchProductByName(name = name, product_id = product_id)
    }


    private class insertAsyncTask internal constructor(private val productsDao: ShopDAO) :
        AsyncTask<PRODUCT, Void, Void>() {

        override fun doInBackground(vararg params: PRODUCT): Void? {
            productsDao.insertProduct(params[0])
            return null
        }
    }

    suspend fun getAllCategorys() = apiService.get_ListCATEGORYS("ListCATEGORYS")

    fun getCategrys_DB(): ListCATEGORYS {

        val response: List<CATEGORY> = db.getAllCategorys()
        PrintLogs.printD("MainActivityRepository getCategrys_DB  " + response.size)
        var data = ArrayList<CATEGORY>()


        for (i in response.indices) {
            data.add(response[i])

        }
        return ListCATEGORYS(data)
    }

    fun insertCategoryinDB(category: CATEGORY) {
        insertCategoryAsyncTask(db).execute(category)
    }

    fun updateCategoryinDB(category: CATEGORY) {
        db.updateCategory(category)
    }

    fun deleteCategoryinDB(category: CATEGORY) {
        db.deleteCategory(category)
    }

    fun fetchCategoryByName(name: String, category_id: String): CATEGORY {
        return db.fetchCategoryByName(name = name, category_id = category_id)
    }

    private class insertCategoryAsyncTask(private val shopDAO: ShopDAO) :
        AsyncTask<CATEGORY, Void, Void>() {

        override fun doInBackground(vararg params: CATEGORY): Void? {
            shopDAO.insertCategory(params[0])
            return null
        }
    }


}

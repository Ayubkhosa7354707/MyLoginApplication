package com.ayub.khosa.myloginapplication.room

import android.content.Context
import android.os.AsyncTask
import com.ayub.khosa.emptyplaylistapplication.room.AppDatabase
import com.ayub.khosa.myloginapplication.api.ApiService
import com.ayub.khosa.myloginapplication.model.APIResponce
import com.ayub.khosa.myloginapplication.model.APIResponceUser
import com.ayub.khosa.myloginapplication.model.USER
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


    private class insertUSERAsyncTask(private val shopDAO: ShopDAO) :
        AsyncTask<USER, Void, Void>() {

        override fun doInBackground(vararg params: USER): Void? {
            shopDAO.insertUSER(params[0])
            return null
        }
    }

}

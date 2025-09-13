package com.ayub.khosa.myloginapplication.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ayub.khosa.myloginapplication.model.USER

@Dao
interface ShopDAO {


    @Insert
    fun insertUSER(user: USER)

    @Query("SELECT * FROM users WHERE email_id = :email_id AND password =:password")
    fun fetchUserByName(email_id: String, password: String): USER

    @Update
    fun updateUSER(user: USER)


}
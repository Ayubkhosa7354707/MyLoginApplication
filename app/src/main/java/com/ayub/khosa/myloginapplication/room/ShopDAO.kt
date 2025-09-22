package com.ayub.khosa.myloginapplication.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ayub.khosa.myloginapplication.model.PRODUCT
import com.ayub.khosa.myloginapplication.model.USER

@Dao
interface ShopDAO {


    @Insert
    fun insertProduct(product: PRODUCT)

    @Query("Select * from products")
    fun gelAllProducts(): List<PRODUCT>


    @Query("SELECT * FROM products WHERE name = :name AND product_id =:product_id")
    fun fetchByName(name: String, product_id: String): PRODUCT

    @Update
    fun updateProduct(product: PRODUCT)

    @Delete
    fun deleteProduct(product: PRODUCT)


    @Query("SELECT * FROM products WHERE name = :name AND product_id =:product_id")
    fun fetchProductByName(name: String, product_id: String): PRODUCT

    @Insert
    fun insertUSER(user: USER)

    @Query("SELECT * FROM users WHERE email_id = :email_id AND password =:password")
    fun fetchUserByName(email_id: String, password: String): USER

    @Update
    fun updateUSER(user: USER)


}
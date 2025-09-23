package com.ayub.khosa.myloginapplication.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ayub.khosa.myloginapplication.model.CATEGORY
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


    @Insert
    fun insertCategory(category: CATEGORY)

    @Query("Select * from categorys")
    fun getAllCategorys(): List<CATEGORY>


    @Query("SELECT * FROM categorys WHERE name = :name AND category_id =:category_id")
    fun fetchCategoryByName(name: String, category_id: String): CATEGORY

    @Update
    fun updateCategory(category: CATEGORY)

    @Delete
    fun deleteCategory(category: CATEGORY)


}
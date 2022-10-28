package com.zoho.people.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE first LIKE :first AND last LIKE :last")
    suspend fun filterByName(first: String, last: String): List<LocalUserEntity>

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun findById(email: String): LocalUserEntity

    @Insert
    suspend fun insertAll(vararg users: LocalUserEntity)

    @Delete
    suspend fun delete(user: LocalUserEntity)
}

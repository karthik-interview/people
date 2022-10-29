package com.zoho.people.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE firstName LIKE :first AND lastName LIKE :last")
    suspend fun filterByName(first: String, last: String): List<LocalUserEntity>

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun findById(email: String): LocalUserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: LocalUserEntity)
}

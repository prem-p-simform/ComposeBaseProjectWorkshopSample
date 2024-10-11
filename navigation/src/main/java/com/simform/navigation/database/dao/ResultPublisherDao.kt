package com.simform.navigation.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.simform.navigation.database.entity.ResultEntity
import kotlinx.coroutines.flow.Flow

/**
 * The DAO to perform operations on [ResultEntity]
 */
@Dao
internal interface ResultPublisherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(result: ResultEntity)

    @Delete
    suspend fun delete(result: ResultEntity)

    @Query("DELETE FROM ResultEntity WHERE `key` = :key")
    suspend fun deleteResultByKey(key: String)

    @Query("SELECT * FROM ResultEntity WHERE `key` = :key")
    fun getResult(key: String): Flow<ResultEntity?>
}

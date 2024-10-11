package com.simform.navigation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.simform.navigation.database.dao.ResultPublisherDao
import com.simform.navigation.database.entity.ResultEntity

@Database(entities = [ResultEntity::class], version = 1, exportSchema = false)
/**
 * The in-memory Room database.
 */
internal abstract class InMemoryAppDatabase : RoomDatabase() {

    abstract fun resultDao(): ResultPublisherDao

    companion object {
        @Volatile
        private var instance: InMemoryAppDatabase? = null

        /**
         * Builds the instance of [InMemoryAppDatabase]
         */
        fun buildDatabase(context: Context): InMemoryAppDatabase =
            instance ?: synchronized(this) {
                instance ?: let {
                    instance =
                        Room.inMemoryDatabaseBuilder(context, InMemoryAppDatabase::class.java)
                            .build()
                    instance!!
                }
            }
    }
}
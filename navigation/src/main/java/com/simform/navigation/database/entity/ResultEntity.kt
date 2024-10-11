package com.simform.navigation.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents a result.
 * 
 * @property key The unique identifier
 * @property result The result to be stored
 */
@Entity("ResultEntity")
internal data class ResultEntity(
    @PrimaryKey(autoGenerate = false)
    val key: String,
    val result: String? = null
)

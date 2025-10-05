package com.jahez.storage


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jahez.storage.model.ChatMessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(message: ChatMessageEntity)

    @Query("SELECT * FROM chat_messages ORDER BY id ASC")
    fun getMessages(): Flow<List<ChatMessageEntity>>
}

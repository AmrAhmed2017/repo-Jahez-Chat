package com.jahez.storage


import androidx.room.Database
import androidx.room.RoomDatabase
import com.jahez.storage.model.ChatMessageEntity

@Database(entities = [ChatMessageEntity::class], version = 1)
abstract class ChatDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao
}

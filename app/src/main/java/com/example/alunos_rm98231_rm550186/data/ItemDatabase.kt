package com.example.alunos_rm98231_rm550186.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.alunos_rm98231_rm550186.entities.ItemModel

@Database(entities = [ItemModel::class], version = 1)
abstract class ItemDatabase : RoomDatabase() {
   abstract fun itemDao(): ItemDao
}
package com.droidcode.apps.contactsapp.conatacts.data.database

import androidx.room.Database
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import com.droidcode.apps.contactsapp.conatacts.data.Contact
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass

@Database(entities = [ContactDto::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun contacDao(): ContactDao

}

data class ContactDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)

interface ContactDao {

    @Query("SELECT * FROM contacts")
    fun getAll(): Flow<List<ContactDto>>
}

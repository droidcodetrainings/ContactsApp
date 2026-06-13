package com.droidcode.apps.contactsapp.conatacts.data.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Database(entities = [ContactDto::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "contacts-db"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        val instance: AppDatabase
            get() = INSTANCE ?: throw IllegalStateException("Database not initialized. Call getDatabase(context) first.")
    }
}

@Dao
interface ContactDao {
    @Query("SELECT * FROM contacts")
    fun getAll(): Flow<List<ContactDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(contacts: List<ContactDto>)
}

@Entity(tableName = "contacts")
data class ContactDto(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val relation: String?,
    val email: String,
    val phone: String,
    val city: String?,
    val birthday: Long?,
    val latitude: Double?,
    val longitude: Double?,
    val imageUrl: String?,
    val isFavorite: Boolean,
    val isMe: Boolean,
    val lastActionTime: Long?
)

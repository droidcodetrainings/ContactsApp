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
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.Flow
import org.joda.time.DateTime

@Database(entities = [ContactDto::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        const val DATABASE_NAME = "app_database"
        const val DATABASE_VERSION = 1

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun create(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build().also { this.INSTANCE = it }
            }
        }

        val instance: AppDatabase
            get() = INSTANCE ?: throw IllegalStateException("Database not initialized")
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

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): DateTime? {
        return value?.let { DateTime(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: DateTime?): Long? {
        return date?.millis
    }
}

package pl.farmaprom.trainings.contactsapp.contacts.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ContactEntity::class], version = 1, exportSchema = false)
abstract class ContactsDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDao

    companion object {
        @Volatile
        private var INSTANCE: ContactsDatabase? = null

        fun getInstance(context: Context): ContactsDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    ContactsDatabase::class.java,
                    "contacts_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}

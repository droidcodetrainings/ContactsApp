package pl.farmaprom.trainings.contactsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [SampleEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sampleDao(): SampleDao
    // more daos
}


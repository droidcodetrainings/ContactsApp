package pl.farmaprom.trainings.contactsapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SampleDao {

    @Query("SELECT * FROM sample_table")
    suspend fun getAll(): List<SampleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(items: List<SampleEntity>)

    @Query("DELETE FROM sample_table")
    suspend fun clearAll()
}

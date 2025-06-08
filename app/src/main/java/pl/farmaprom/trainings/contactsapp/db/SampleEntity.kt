package pl.farmaprom.trainings.contactsapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sample_table")
data class SampleEntity(
    @PrimaryKey val id: Long,
    val name: String
)

package pl.farmaprom.trainings.contactsapp.contacts.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface ContactDao {

    @Query("SELECT * FROM contacts")
    suspend fun getContactsList(): List<ContactEntity>

    @Query("SELECT * FROM contacts WHERE id = :id LIMIT 1")
    suspend fun getSingleContact(id: Long): ContactEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContacts(contacts: List<ContactEntity>)

    @Transaction
    suspend fun performMoreOperations(contacts: List<ContactEntity>) {
        clear()
        insertContacts(contacts)
    }

    @Query("DELETE FROM contacts")
    suspend fun clear()
}


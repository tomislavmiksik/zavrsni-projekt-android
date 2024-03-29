package hr.tvz.android.zavrsniprojektmiksik.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.RaceEntity
import hr.tvz.android.zavrsniprojektmiksik.models.Race

@Dao
interface RaceDAO {
    @Query("SELECT * FROM RaceEntity")
    fun getAll(): LiveData<List<RaceEntity>>

    @Query("SELECT * FROM RaceEntity WHERE id = :id")
    fun getById(id: Int): RaceEntity

    @Query("SELECT * FROM RaceEntity WHERE race_name = :name")
    fun getByName(name: String): RaceEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(race: RaceEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(race: List<RaceEntity>)

    @Delete
    fun delete(race: RaceEntity)

    @Update
    fun update(race: RaceEntity)
}
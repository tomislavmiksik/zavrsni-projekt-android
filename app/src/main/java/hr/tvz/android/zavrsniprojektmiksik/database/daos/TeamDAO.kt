package hr.tvz.android.zavrsniprojektmiksik.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.TeamEntity

@Dao
interface TeamDAO {
    @Query("SELECT * FROM TeamEntity")
    fun getAll(): LiveData<List<TeamEntity>>

    @Query("SELECT * FROM TeamEntity WHERE id = :id")
    fun getById(id: Int): TeamEntity

    @Query("SELECT * FROM TeamEntity WHERE team_name = :name")
    fun getByName(name: String): TeamEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(team: TeamEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(team: List<TeamEntity>)

    @Delete
    fun delete(team: TeamEntity)

    @Update
    fun update(team: TeamEntity)
}
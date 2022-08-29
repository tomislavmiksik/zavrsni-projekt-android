package hr.tvz.android.zavrsniprojektmiksik.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.TeamEntity

@Dao
interface TeamDao {
    @Query("SELECT * FROM TeamEntity")
    fun getAll(): LiveData<List<TeamEntity>>

    @Query("SELECT * FROM TeamEntity WHERE id = :id")
    fun getById(id: Int): TeamEntity

    @Query("SELECT * FROM TeamEntity WHERE team_name = :name")
    fun getByName(name: String): TeamEntity

    @Insert
    fun insert(team: TeamEntity)

    @Insert
    fun insertAll(team: List<TeamEntity>)

    @Delete
    fun delete(team: TeamEntity)

    @Update
    fun update(team: TeamEntity)
}
package hr.tvz.android.zavrsniprojektmiksik.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.DriverEntity

@Dao
interface DriverDAO {
    @Query("SELECT * FROM DriverEntity")
    fun getAll(): LiveData<List<DriverEntity>>

    @Query("SELECT * FROM DriverEntity WHERE id = :id")
    fun getById(id: Int): DriverEntity

    @Query("SELECT * FROM DriverEntity WHERE driver_name = :name")
    fun getByName(name: String): DriverEntity

    @Insert
    fun insert(driver: DriverEntity)

    @Insert
    fun insertAll(driver: List<DriverEntity>)

    @Delete
    fun delete(driver: DriverEntity)

    @Update
    fun update(driver: DriverEntity)
}
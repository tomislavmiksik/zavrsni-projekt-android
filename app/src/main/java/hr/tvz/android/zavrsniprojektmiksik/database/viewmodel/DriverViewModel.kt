package hr.tvz.android.zavrsniprojektmiksik.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.DriverEntity
import hr.tvz.android.zavrsniprojektmiksik.database.repos.DriverRepository
import kotlinx.coroutines.launch

class DriverViewModel (application: Application) : AndroidViewModel(application) {
    private val repository = DriverRepository(application)
    private val allDrivers = repository.getAll()

    fun insert(driver: DriverEntity) = viewModelScope.launch {
        repository.insert(driver)
    }

    fun delete(driver: DriverEntity) = viewModelScope.launch {
        repository.delete(driver)
    }

    fun update(driver: DriverEntity) = viewModelScope.launch {
        repository.update(driver)
    }



    @JvmName("getAllDrivers")
    fun getAllRaces(): LiveData<List<DriverEntity>> {
        return allDrivers
    }

    @JvmName("getDriverById")
    fun getRaceById(id: Int): DriverEntity {
        return repository.getById(id)
    }
}
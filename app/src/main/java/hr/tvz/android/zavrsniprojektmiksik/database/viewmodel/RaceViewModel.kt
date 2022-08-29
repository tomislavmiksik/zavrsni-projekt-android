package hr.tvz.android.zavrsniprojektmiksik.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.RaceEntity
import hr.tvz.android.zavrsniprojektmiksik.database.repos.RaceRepository
import kotlinx.coroutines.launch

class RaceViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = RaceRepository(application)
    private val allRaces = repository.getAll()

    fun insert(race: RaceEntity) = viewModelScope.launch {
        repository.insert(race)
    }

    fun delete(race: RaceEntity) = viewModelScope.launch {
        repository.delete(race)
    }

    fun update(race: RaceEntity) = viewModelScope.launch {
        repository.update(race)
    }



    @JvmName("getAllRaces")
    fun getAllRaces(): LiveData<List<RaceEntity>> {
        return allRaces
    }

    @JvmName("getRaceById")
    fun getRaceById(id: Int): RaceEntity{
        return repository.getById(id)
    }
}
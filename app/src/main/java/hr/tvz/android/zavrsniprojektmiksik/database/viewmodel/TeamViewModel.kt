package hr.tvz.android.zavrsniprojektmiksik.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.TeamEntity
import hr.tvz.android.zavrsniprojektmiksik.database.repos.TeamRepository
import kotlinx.coroutines.launch

class TeamViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = TeamRepository(application)
    private val allTeams = repository.getAll()

    fun insert(team: TeamEntity) = viewModelScope.launch {
        repository.insert(team)
    }

    fun delete(team: TeamEntity) = viewModelScope.launch {
        repository.delete(team)
    }

    fun update(team: TeamEntity) = viewModelScope.launch {
        repository.update(team)
    }



    @JvmName("getAllTeams")
    fun getAllRaces(): LiveData<List<TeamEntity>> {
        return allTeams
    }

    @JvmName("getTeamById")
    fun getRaceById(id: Int): TeamEntity {
        return repository.getById(id)
    }
}
package hr.tvz.android.zavrsniprojektmiksik.database.repos

import android.app.Application
import androidx.lifecycle.LiveData
import hr.tvz.android.zavrsniprojektmiksik.database.AppDatabase
import hr.tvz.android.zavrsniprojektmiksik.database.daos.TeamDAO
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.TeamEntity
import subscribeOnBackground

class TeamRepo (application: Application) {

    private var teamDao: TeamDAO
    private var allTeams: LiveData<List<TeamEntity>>


    private val database = AppDatabase.getInstance(application)

    init {
        teamDao = database.teamDao()
        allTeams = teamDao.getAll()
    }
    fun insert(team: TeamEntity) {
        subscribeOnBackground {
            teamDao.insert(team)
        }
    }

    fun update(team: TeamEntity) {
        subscribeOnBackground {
            teamDao.update(team)
        }
    }

    fun delete(team: TeamEntity) {
        subscribeOnBackground {
            teamDao.delete(team)
        }
    }


    fun getAll(): LiveData<List<TeamEntity>> {
        return allTeams
    }

    fun getById(id: Int): TeamEntity {
        return teamDao.getById(id)
    }
}
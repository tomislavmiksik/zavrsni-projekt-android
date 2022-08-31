package hr.tvz.android.zavrsniprojektmiksik.database.repos

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import hr.tvz.android.zavrsniprojektmiksik.database.AppDatabase
import hr.tvz.android.zavrsniprojektmiksik.database.daos.TeamDAO
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.TeamEntity
import hr.tvz.android.zavrsniprojektmiksik.services.ServiceGenerator
import hr.tvz.android.zavrsniprojektmiksik.services.ServiceInterface
import retrofit2.Call
import retrofit2.Response
import subscribeOnBackground

class TeamRepository (application: Application) {

    private var teamDao: TeamDAO
    private var allTeams: LiveData<List<TeamEntity>>
    val client: ServiceInterface = ServiceGenerator().createService(
        ServiceInterface::class.java,
    )


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
        val teams : Call<MutableList<TeamEntity>> = client.fetchConstructorsStandings()


        teams.enqueue(object : retrofit2.Callback<MutableList<TeamEntity>> {
            override fun onResponse(
                call: Call<MutableList<TeamEntity>>,
                response: Response<MutableList<TeamEntity>>
            ) {
                for (item in response.body()!!) {
                    insert(item)
                }

            }

            override fun onFailure(call: Call<MutableList<TeamEntity>>, t: Throwable) {
            }
        })
        return allTeams
    }

    fun getById(id: Int): TeamEntity {
        return teamDao.getById(id)
    }
}
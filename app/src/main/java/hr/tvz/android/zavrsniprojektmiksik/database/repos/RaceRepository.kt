package hr.tvz.android.zavrsniprojektmiksik.database.repos

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import hr.tvz.android.zavrsniprojektmiksik.database.AppDatabase
import hr.tvz.android.zavrsniprojektmiksik.database.daos.RaceDAO
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.RaceEntity
import hr.tvz.android.zavrsniprojektmiksik.services.ServiceGenerator
import hr.tvz.android.zavrsniprojektmiksik.services.ServiceInterface
import retrofit2.Call
import retrofit2.Response
import subscribeOnBackground

class RaceRepository (application: Application){

    private var raceDao: RaceDAO
    private var allRaces: LiveData<List<RaceEntity>>
    val client: ServiceInterface = ServiceGenerator().createService(
        ServiceInterface::class.java,
    )


    private val database = AppDatabase.getInstance(application)

    init {
        raceDao = database.raceDao()
        allRaces = raceDao.getAll()
    }
    fun insert(race: RaceEntity) {
        subscribeOnBackground {
            raceDao.insert(race)
        }
    }

    fun update(race: RaceEntity) {
        subscribeOnBackground {
            raceDao.update(race)
        }
    }

    fun delete(race: RaceEntity) {
        subscribeOnBackground {
            raceDao.delete(race)
        }
    }


    fun getAll(): LiveData<List<RaceEntity>> {


        val races: Call<MutableList<RaceEntity>> = client.fetchRaces()

        races.enqueue(object : retrofit2.Callback<MutableList<RaceEntity>> {
            override fun onResponse(
                call: Call<MutableList<RaceEntity>>,
                response: Response<MutableList<RaceEntity>>
            ) {
                for (item in response.body()!!) {
                    insert(item)
                }
            }

            override fun onFailure(call: Call<MutableList<RaceEntity>>, t: Throwable) {
            }

        })

        return allRaces
    }

    fun getById(id: Int): RaceEntity {
        return raceDao.getById(id)
    }
}
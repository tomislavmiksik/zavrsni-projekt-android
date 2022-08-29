package hr.tvz.android.zavrsniprojektmiksik.database.repos

import android.app.Application
import androidx.lifecycle.LiveData
import hr.tvz.android.zavrsniprojektmiksik.database.AppDatabase
import hr.tvz.android.zavrsniprojektmiksik.database.daos.RaceDao
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.RaceEntity
import subscribeOnBackground

class RaceRepo (application: Application){

    private var raceDao: RaceDao
    private var allRaces: LiveData<List<RaceEntity>>


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
        return allRaces
    }

    fun getById(id: Int): RaceEntity {
        return raceDao.getById(id)
    }
}
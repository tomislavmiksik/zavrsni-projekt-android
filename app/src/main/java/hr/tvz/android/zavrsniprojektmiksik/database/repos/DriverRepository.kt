package hr.tvz.android.zavrsniprojektmiksik.database.repos

import android.app.Application
import androidx.lifecycle.LiveData
import hr.tvz.android.zavrsniprojektmiksik.database.AppDatabase
import hr.tvz.android.zavrsniprojektmiksik.database.daos.DriverDAO
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.DriverEntity
import subscribeOnBackground

class DriverRepository (application: Application) {

    private var driverDao: DriverDAO
    private var allDrivers: LiveData<List<DriverEntity>>


    private val database = AppDatabase.getInstance(application)

    init {
        driverDao = database.driverDao()
        allDrivers = driverDao.getAll()
    }
    fun insert(driver: DriverEntity) {
        subscribeOnBackground {
            driverDao.insert(driver)
        }
    }

    fun update(driver: DriverEntity ) {
        subscribeOnBackground {
            driverDao.update(driver)
        }
    }

    fun delete(driver: DriverEntity) {
        subscribeOnBackground {
            driverDao.delete(driver)
        }
    }


    fun getAll(): LiveData<List<DriverEntity>> {
        return allDrivers
    }

    fun getById(id: Int): DriverEntity {
        return driverDao.getById(id)
    }
}
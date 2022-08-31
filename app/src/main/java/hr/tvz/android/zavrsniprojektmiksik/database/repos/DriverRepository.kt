package hr.tvz.android.zavrsniprojektmiksik.database.repos

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import hr.tvz.android.zavrsniprojektmiksik.database.AppDatabase
import hr.tvz.android.zavrsniprojektmiksik.database.daos.DriverDAO
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.DriverEntity
import hr.tvz.android.zavrsniprojektmiksik.services.ServiceGenerator
import hr.tvz.android.zavrsniprojektmiksik.services.ServiceInterface
import retrofit2.Call
import retrofit2.Response
import subscribeOnBackground

class DriverRepository (application: Application) {

    private var driverDao: DriverDAO
    private var allDrivers: LiveData<List<DriverEntity>>
    val client: ServiceInterface = ServiceGenerator().createService(
        ServiceInterface::class.java,
    )


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
        val drivers : Call<MutableList<DriverEntity>> = client.fetchDriverStandings()



        drivers.enqueue(
            object : retrofit2.Callback<MutableList<DriverEntity>> {
                override fun onResponse(
                    call: Call<MutableList<DriverEntity>>,
                    response: Response<MutableList<DriverEntity>>
                ) {
                    for (item in response.body()!!) {
                        insert(item)
                    }
                }

                override fun onFailure(call: Call<MutableList<DriverEntity>>, t: Throwable) {
                }
            }
        )
        return allDrivers
    }

    fun getById(id: Int): DriverEntity {
        return driverDao.getById(id)
    }
}
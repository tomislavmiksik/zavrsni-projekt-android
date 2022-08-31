package hr.tvz.android.zavrsniprojektmiksik

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.DriverEntity
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.RaceEntity
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.TeamEntity
import hr.tvz.android.zavrsniprojektmiksik.database.viewmodel.DriverViewModel
import hr.tvz.android.zavrsniprojektmiksik.database.viewmodel.RaceViewModel
import hr.tvz.android.zavrsniprojektmiksik.database.viewmodel.TeamViewModel
import hr.tvz.android.zavrsniprojektmiksik.databinding.ActivityMainBinding
import hr.tvz.android.zavrsniprojektmiksik.services.ServiceGenerator
import hr.tvz.android.zavrsniprojektmiksik.services.ServiceInterface
import hr.tvz.android.zavrsniprojektmiksik.ui.main.SectionsPagerAdapter
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var raceViewModel: RaceViewModel
    lateinit var teamViewModel: TeamViewModel
    lateinit var driverViewModel: DriverViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client: ServiceInterface = ServiceGenerator().createService(
            ServiceInterface::class.java,
        )

        binding = ActivityMainBinding.inflate(layoutInflater)

        raceViewModel = ViewModelProvider(this)[RaceViewModel::class.java]
        driverViewModel = ViewModelProvider(this)[DriverViewModel::class.java]
        teamViewModel = ViewModelProvider(this)[TeamViewModel::class.java]

        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)

        val races: Call<MutableList<RaceEntity>> = client.fetchRaces()
        val teams : Call<MutableList<TeamEntity>> = client.fetchConstructorsStandings()
        val drivers : Call<MutableList<DriverEntity>> = client.fetchDriverStandings()

        races.enqueue(object : retrofit2.Callback<MutableList<RaceEntity>> {
            override fun onResponse(
                call: Call<MutableList<RaceEntity>>,
                response: Response<MutableList<RaceEntity>>
            ) {
                for (item in response.body()!!) {
                    raceViewModel.insert(item)
                }
            }

            override fun onFailure(call: Call<MutableList<RaceEntity>>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Something went wrong loading races",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        drivers.enqueue(
            object : retrofit2.Callback<MutableList<DriverEntity>> {
                override fun onResponse(
                    call: Call<MutableList<DriverEntity>>,
                    response: Response<MutableList<DriverEntity>>
                ) {
                    for (item in response.body()!!) {
                        driverViewModel.insert(item)
                    }
                }

                override fun onFailure(call: Call<MutableList<DriverEntity>>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Something went wrong loading drivers",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        )

        teams.enqueue(object : retrofit2.Callback<MutableList<TeamEntity>> {
            override fun onResponse(
                call: Call<MutableList<TeamEntity>>,
                response: Response<MutableList<TeamEntity>>
            ) {
                for (item in response.body()!!) {
                    teamViewModel.insert(item)
                }

            }

            override fun onFailure(call: Call<MutableList<TeamEntity>>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Something went wrong loading teams",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }
}
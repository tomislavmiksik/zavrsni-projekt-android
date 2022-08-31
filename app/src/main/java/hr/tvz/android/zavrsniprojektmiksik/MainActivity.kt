package hr.tvz.android.zavrsniprojektmiksik

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.facebook.drawee.backends.pipeline.Fresco
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
        Fresco.initialize(this);
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)


        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)



    }
}
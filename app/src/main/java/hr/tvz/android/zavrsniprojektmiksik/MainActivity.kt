package hr.tvz.android.zavrsniprojektmiksik

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import hr.tvz.android.zavrsniprojektmiksik.ui.main.SectionsPagerAdapter
import hr.tvz.android.zavrsniprojektmiksik.databinding.ActivityMainBinding
import hr.tvz.android.zavrsniprojektmiksik.models.Driver
import hr.tvz.android.zavrsniprojektmiksik.models.Race
import hr.tvz.android.zavrsniprojektmiksik.models.Team
import hr.tvz.android.zavrsniprojektmiksik.services.ServiceGenerator
import hr.tvz.android.zavrsniprojektmiksik.services.ServiceInterface

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var driverList: MutableList<Driver>
    lateinit var teamList: MutableList<Team>
    lateinit var raceList: MutableList<Race>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val client: ServiceInterface = ServiceGenerator().createService(
            ServiceInterface::class.java,
        )

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)


    }
}
package hr.tvz.android.zavrsniprojektmiksik.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.tvz.android.zavrsniprojektmiksik.R
import hr.tvz.android.zavrsniprojektmiksik.ui.fragments.DriversFragment
import hr.tvz.android.zavrsniprojektmiksik.ui.fragments.RacesFragment
import hr.tvz.android.zavrsniprojektmiksik.ui.fragments.TeamsFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_drivers,
    R.string.tab_races,
    R.string.tab_teams,
)
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when (position){
            0 -> return DriversFragment()
            1 -> return RacesFragment()
            2 -> return TeamsFragment()
            else -> return DriversFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }
}
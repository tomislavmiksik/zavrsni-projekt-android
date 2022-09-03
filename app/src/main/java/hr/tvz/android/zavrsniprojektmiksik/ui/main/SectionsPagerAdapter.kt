package hr.tvz.android.zavrsniprojektmiksik.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hr.tvz.android.zavrsniprojektmiksik.R
import hr.tvz.android.zavrsniprojektmiksik.ui.fragments.DriverListFragment
import hr.tvz.android.zavrsniprojektmiksik.ui.fragments.RaceListFragment
import hr.tvz.android.zavrsniprojektmiksik.ui.fragments.TeamListFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_drivers,
    R.string.tab_races,
    R.string.tab_teams,
)
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> DriverListFragment()
            1 -> RaceListFragment()
            2 -> TeamListFragment()
            else -> throw IllegalStateException()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return 3
    }
}
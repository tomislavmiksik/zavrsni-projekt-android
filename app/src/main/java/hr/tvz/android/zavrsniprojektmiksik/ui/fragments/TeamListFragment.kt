package hr.tvz.android.zavrsniprojektmiksik.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.fragment.app.ListFragment
import androidx.lifecycle.ViewModelProvider
import hr.tvz.android.zavrsniprojektmiksik.R
import hr.tvz.android.zavrsniprojektmiksik.database.viewmodel.TeamViewModel
import hr.tvz.android.zavrsniprojektmiksik.ui.adapters.RaceListAdapter
import hr.tvz.android.zavrsniprojektmiksik.ui.adapters.TeamListAdapter

class TeamListFragment : ListFragment() {

    private val STATE_ACTIVATED_POSITION = "activated_position"
    private var mActivatedPosition = ListView.INVALID_POSITION
    private var teamViewModel : TeamViewModel? = null

    interface Callbacks {
        fun onItemSelected(id: Int?)
    }

    private val sDummyCallbacks: Callbacks =
        object : Callbacks {
            override fun onItemSelected(id: Int?) {}
        }

    private var mCallbacks: Callbacks = sDummyCallbacks

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        teamViewModel = ViewModelProvider(this)[TeamViewModel::class.java]

        teamViewModel!!.getAllTeams().observe(
            this
        ) { teams ->
            teams.map { it.team_name }.toMutableList()
            listAdapter = TeamListAdapter(
                requireActivity(),
                R.layout.driver_card,
                teams
            )
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION))
        }
    }

    private fun setActivatedPosition(position: Int) {
        if (position == ListView.INVALID_POSITION) {
            listView.setItemChecked(mActivatedPosition, false)
        } else {
            listView.setItemChecked(position, true)
        }

        mActivatedPosition = position
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition)
        }
    }

}
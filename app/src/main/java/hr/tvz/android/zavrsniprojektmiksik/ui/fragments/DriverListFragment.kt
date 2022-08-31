package hr.tvz.android.zavrsniprojektmiksik.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.fragment.app.ListFragment
import androidx.lifecycle.ViewModelProvider
import com.facebook.drawee.backends.pipeline.Fresco
import hr.tvz.android.zavrsniprojektmiksik.R
import hr.tvz.android.zavrsniprojektmiksik.database.viewmodel.DriverViewModel
import hr.tvz.android.zavrsniprojektmiksik.ui.adapters.DriverListAdapter

class DriverListFragment : ListFragment() {

    private val STATE_ACTIVATED_POSITION = "activated_position"
    private var mActivatedPosition = ListView.INVALID_POSITION
    private var driverViewModel : DriverViewModel? = null

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
        driverViewModel = ViewModelProvider(this)[DriverViewModel::class.java]

        driverViewModel!!.getAllDrivers().observe(
            this
        ) { grandPrixList ->
            grandPrixList.map { it.driver_name }.toMutableList()
            listAdapter = DriverListAdapter(
                requireActivity(),
                R.layout.driver_card,
                grandPrixList
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
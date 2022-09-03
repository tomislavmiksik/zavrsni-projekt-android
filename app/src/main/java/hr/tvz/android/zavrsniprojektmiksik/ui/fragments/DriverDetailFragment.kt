package hr.tvz.android.zavrsniprojektmiksik.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.facebook.drawee.view.SimpleDraweeView
import hr.tvz.android.zavrsniprojektmiksik.R
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.DriverEntity
import hr.tvz.android.zavrsniprojektmiksik.database.viewmodel.DriverViewModel
import hr.tvz.android.zavrsniprojektmiksik.databinding.FragmentDriverDetailBinding

class DriverDetailFragment : Fragment(R.layout.fragment_driver_detail) {

    val ARG_ITEM_ID = "item_id"
    private var driverViewModel: DriverViewModel? = null
    var driver: DriverEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val driverId = requireArguments().getInt(ARG_ITEM_ID)

        driverViewModel = ViewModelProvider(this)[DriverViewModel::class.java]

        driver = driverViewModel?.getDriverById(driverId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_driver_detail, container, false)
        val binding = FragmentDriverDetailBinding.bind(rootView)
        if (driver != null) {

            binding.name.text = driver?.driver_name
            binding.nationality.text = driver?.nationality
            binding.teamName.text = driver?.team_name
            binding.position.text = driver?.position
            binding.points.text = driver?.points

            val uri: Uri =
                Uri.parse(driver?.image)
            val draweeView = binding.driverImage
            draweeView.setImageURI(uri)


            binding.shareButton.setOnClickListener {
                val url = "https://www.formula1.com/en/drivers.html"
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            }
        }

        return rootView
    }
}
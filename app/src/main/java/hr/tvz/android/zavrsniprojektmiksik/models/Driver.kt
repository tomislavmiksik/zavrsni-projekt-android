package hr.tvz.android.zavrsniprojektmiksik.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Driver(
    var id : Int,
    var driver_name :String,
    var position: String,
    var points: String,
    var team_name: String,
    var nationality: String,
    var image: String,
) : Parcelable{

}
package hr.tvz.android.zavrsniprojektmiksik.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(
    var team_name: String,
    var position: String,
    var points: String,
    var image: String,

) : Parcelable {
}
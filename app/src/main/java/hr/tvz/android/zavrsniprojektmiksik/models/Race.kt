package hr.tvz.android.zavrsniprojektmiksik.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Race(
    var race_name: String,
    var race_date: String,
    var race_location: String,
) : Parcelable {
}
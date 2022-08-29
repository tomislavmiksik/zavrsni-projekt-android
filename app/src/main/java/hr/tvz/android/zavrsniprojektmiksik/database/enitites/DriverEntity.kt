package hr.tvz.android.zavrsniprojektmiksik.database.enitites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DriverEntity (
@PrimaryKey val id: Int,
@ColumnInfo(name = "position") val position: String,
@ColumnInfo(name = "driver_name") val driver_name: String,
@ColumnInfo(name = "points") val points: String,
@ColumnInfo(name = "team_name") val team_name: String,
@ColumnInfo(name = "nationality") val nationality: String,
@ColumnInfo(name = "image") val image: String,

)
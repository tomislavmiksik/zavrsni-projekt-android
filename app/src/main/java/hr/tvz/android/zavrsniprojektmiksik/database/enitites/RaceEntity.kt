package hr.tvz.android.zavrsniprojektmiksik.database.enitites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RaceEntity (
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "race_name") val race_name: String,
    @ColumnInfo(name = "race_date") val race_date: String,
    @ColumnInfo(name="race_location") val race_location: String,
    @ColumnInfo(name = "image") val image: String,
)
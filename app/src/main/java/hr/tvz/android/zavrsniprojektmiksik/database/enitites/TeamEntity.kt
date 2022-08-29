package hr.tvz.android.zavrsniprojektmiksik.database.enitites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TeamEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "team_name") val team_name: String,
    @ColumnInfo(name = "position") val position: String,
    @ColumnInfo(name = "points") val points: String,
    @ColumnInfo(name = "image") val image: String,
)
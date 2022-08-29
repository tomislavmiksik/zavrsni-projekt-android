package hr.tvz.android.zavrsniprojektmiksik.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import hr.tvz.android.zavrsniprojektmiksik.database.daos.DriverDAO
import hr.tvz.android.zavrsniprojektmiksik.database.daos.RaceDAO
import hr.tvz.android.zavrsniprojektmiksik.database.daos.TeamDAO
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.DriverEntity
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.RaceEntity
import hr.tvz.android.zavrsniprojektmiksik.database.enitites.TeamEntity

@Database(entities = [DriverEntity::class, RaceEntity::class, TeamEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun driverDao(): DriverDAO
    abstract fun teamDao(): TeamDAO
    abstract fun raceDao(): RaceDAO

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): AppDatabase {
            if (instance == null)
                instance = Room.databaseBuilder(
                    ctx.applicationContext, AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}
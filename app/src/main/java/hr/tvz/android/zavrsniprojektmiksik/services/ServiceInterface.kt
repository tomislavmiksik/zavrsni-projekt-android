package hr.tvz.android.zavrsniprojektmiksik.services

import hr.tvz.android.zavrsniprojektmiksik.models.Driver
import hr.tvz.android.zavrsniprojektmiksik.models.Race
import hr.tvz.android.zavrsniprojektmiksik.models.Team
import retrofit2.Call
import retrofit2.http.GET

interface ServiceInterface {
    @GET("6ebbe8bc4c23b6864a5a")
    fun fetchDriverStandings(): Call<MutableList<Driver>>

    @GET("9acffdda29f1c517caa0")
    fun fetchRaces(): Call<MutableList<Race>>

    @GET("31a0bf212a6859e002b8")
    fun fetchConstructorsStandings(): Call<MutableList<Team>>
}
package com.multiplatform.clubdistances.homeScreen.repositories
import com.multiplatform.clubdistances.homeScreen.model.Club

// Not Currently used, only for testing a full list of ready clubs
class GetClubsStaticRepository() {

    fun getClubList(): List<Club> {
        return listOf(
            Club(
                "D ",
                "9.5",
                "Ping",
                220
            ),
            Club(
                "7W",
                "20",
                "Callaway",
                190
            ),
            Club(
                "4i",
                "X",
                "TaylorMade",
                170
            ),
            Club(
                "5i",
                "X",
                "TaylorMade",
                165
            ),
            Club(
                "6i",
                "X",
                "TaylorMade",
                160
            ),
            Club(
                "7i",
                "X",
                "TaylorMade",
                145
            ),
            Club(
                "8i",
                "X",
                "TaylorMade",
                130
            ),
            Club(
                "9i",
                "X",
                "TaylorMade",
                120
            ),
            Club(
                "PW",
                "45",
                "TaylorMade",
                105
            ),
            Club(
                "GP",
                "52",
                "Callaway",
                90
            ),
            Club(
                "SW",
                "56",
                "Callaway",
                70
            ),
            Club(
                "LB",
                "60",
                "Callaway",
                50
            )
        )
    }
}
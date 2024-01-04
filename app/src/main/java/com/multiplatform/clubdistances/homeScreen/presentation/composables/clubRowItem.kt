package com.multiplatform.clubdistances.homeScreen.presentation.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.multiplatform.clubdistances.homeScreen.model.Club

@Composable
fun ClubRowItem (club: Club){
    Row {
        Text(text = club.clubName)
        Text(text = club.clubBrand)
        Text(text = club.clubLoft)
        Text(text = club.distance.toString())
    }
}
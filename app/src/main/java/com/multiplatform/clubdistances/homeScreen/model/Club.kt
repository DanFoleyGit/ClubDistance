package com.multiplatform.clubdistances.homeScreen.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "club_table")
data class Club (
    @PrimaryKey @ColumnInfo(name = "club_name") val clubName : String,
    @ColumnInfo(name = "club_loft") val clubLoft : String,
    @ColumnInfo(name = "first_brand") val clubBrand :String,
    @ColumnInfo(name = "distance") val distance : Int
    )
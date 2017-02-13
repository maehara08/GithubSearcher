package com.riku.land.cs.githubsearcher.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by riku_maehara on 17/02/08.
 */

data class Item(val name: String,
                @SerializedName("full_name")
                val fullName: String,
                val description: String,
                val language: String
) : Serializable
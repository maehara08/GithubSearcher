package com.riku.land.cs.githubsearcher.model.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

/**
 * Created by riku_maehara on 17/02/08.
 */
class SearchResponse(
        @SerializedName("total_count")
        val totalCount: Int,
        @SerializedName("incomplete_results")
        val incompleteResults: Boolean,
        val items: List<Item>
) : Serializable
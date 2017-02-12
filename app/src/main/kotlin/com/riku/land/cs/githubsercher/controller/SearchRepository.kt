package com.riku.land.cs.githubsercher.controller

import com.riku.land.cs.githubsercher.model.entity.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

public interface SearchRepository {

    @GET("search/repositories")
    fun getSearchRepository(
            @Query("q") keywords: String
    ): Observable<SearchResponse>
}
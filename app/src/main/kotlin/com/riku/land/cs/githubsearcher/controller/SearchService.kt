package com.riku.land.cs.githubsearcher.controller

import com.riku.land.cs.githubsearcher.model.entity.SearchResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable


class SearchService() {
    companion object {
        val BASE_URL = "https://api.github.com/"
        val okClient = OkHttpClient()
    }

    fun SearchService(): Unit {
    }

    fun searchClient(keyWord: String): Observable<SearchResponse> {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
        return retrofit.create(SearchRepository::class.java).getSearchRepository(keyWord)
    }
}


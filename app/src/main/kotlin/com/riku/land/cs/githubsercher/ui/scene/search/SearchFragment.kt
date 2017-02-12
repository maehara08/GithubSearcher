package com.riku.land.cs.githubsercher.ui.scene.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.jakewharton.rxbinding.widget.RxTextView
import com.riku.land.cs.githubsercher.BaseFragment
import com.riku.land.cs.githubsercher.R
import com.riku.land.cs.githubsercher.bindView
import com.riku.land.cs.githubsercher.controller.SearchService
import com.riku.land.cs.githubsercher.model.entity.SearchResponse
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import kotlin.properties.Delegates

/**
 * Created by riku_maehara on 17/02/08.
 */

public class SearchFragment : BaseFragment() {
    companion object {
        const val TAG = "SearchFragment"
    }

    var searchEditText: EditText by Delegates.notNull()


    private var searchDebugButton: Button by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_search, container, false).apply {
                searchDebugButton = bindView(R.id.debug_button)
                searchEditText = bindView(R.id.search_edit_text)
            }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchDebugButton.setOnClickListener({
            val service = SearchService()
            service.searchClient("tetros")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<SearchResponse> {
                        override fun onError(error: Throwable?) {
                            Log.e(TAG + " onError", error.toString())
                        }

                        override fun onCompleted() {
                            Log.d(TAG, "onCompleted")
                        }

                        override fun onNext(t: SearchResponse?) {
                            Log.d(TAG, t!!.items.get(0).name)
                        }
                    })
            Log.d(TAG, "onClicked")
        })
        observeSearchEditText(editText = searchEditText)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { }
    }

    private fun observeSearchEditText(editText: EditText) =
            RxTextView.textChanges(editText).map {
                char ->
                Log.d(TAG, char.toString())
            }
}
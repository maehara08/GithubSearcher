package com.riku.land.cs.githubsearcher.ui.scene.search

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ListView
import com.jakewharton.rxbinding.widget.RxTextView
import com.riku.land.cs.githubsearcher.BaseFragment
import com.riku.land.cs.githubsearcher.R
import com.riku.land.cs.githubsearcher.bindView
import com.riku.land.cs.githubsearcher.controller.SearchService
import com.riku.land.cs.githubsearcher.model.entity.SearchResponse
import com.riku.land.cs.githubsearcher.ui.adapter.RepositoryAdapter
import com.trello.rxlifecycle.kotlin.bindToLifecycle
import rx.Observer
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

/**
 * Created by riku_maehara on 17/02/08.
 */

public class SearchFragment : BaseFragment() {
    companion object {
        const val TAG = "SearchFragment"
        const val DEBOUNCE_WAIT = 300L
    }

    val service = SearchService()
    var searchEditText: EditText by Delegates.notNull()
    var repositoryListView: ListView by Delegates.notNull()
    var repositoryAdapter: RepositoryAdapter by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_search, container, false).apply {
                searchEditText = bindView(R.id.search_edit_text)
                repositoryListView = bindView(R.id.repository_list_view)
            }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repositoryAdapter = RepositoryAdapter(context)
        repositoryListView.adapter = repositoryAdapter
        observeSearchEditText(searchEditText)
                .bindToLifecycle(this)
                .debounce(DEBOUNCE_WAIT, TimeUnit.MILLISECONDS, Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap {
                    service.searchClient(it)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                }
                .subscribe(object : Observer<SearchResponse> {
                    override fun onError(error: Throwable?) {
                        Log.e(TAG + " onError", error.toString())
                    }

                    override fun onCompleted() {
                        Log.d(TAG, "onCompleted")
                    }

                    override fun onNext(t: SearchResponse?) {
                        Log.d(TAG, t!!.items.get(0).fullName)
                        repositoryAdapter.clear()
                        repositoryAdapter.addAll(t.items)
                    }
                })
    }

    private fun observeSearchEditText(editText: EditText) =
            RxTextView.textChanges(editText)
                    .filter {
                        !TextUtils.isEmpty(it)
                    }
                    .map {
                        it.toString()
                    }
}
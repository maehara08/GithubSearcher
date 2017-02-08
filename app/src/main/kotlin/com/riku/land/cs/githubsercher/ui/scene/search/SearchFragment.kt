package com.riku.land.cs.githubsercher.ui.scene.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.riku.land.cs.githubsercher.BaseFragment
import com.riku.land.cs.githubsercher.R
import com.riku.land.cs.githubsercher.bindView
import kotlin.properties.Delegates

/**
 * Created by riku_maehara on 17/02/08.
 */

public class SearchFragment : BaseFragment() {
    companion object{
        const val TAG = "SearchFragment"
    }


    private var searchDebugButton : Button by Delegates.notNull()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_search, container, false).apply {
                searchDebugButton = bindView(R.id.debugButton)
            }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchDebugButton.setOnClickListener({
            Log.d(TAG, "onClicked")
        })
    }
}
package com.riku.land.cs.githubsercher.ui.scene.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.riku.land.cs.githubsercher.BaseFragment
import com.riku.land.cs.githubsercher.R

/**
 * Created by riku_maehara on 17/02/08.
 */

public class SearchFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_search, container, false)

}
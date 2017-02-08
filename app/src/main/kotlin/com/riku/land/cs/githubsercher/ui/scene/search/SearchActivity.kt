package com.riku.land.cs.githubsercher.ui.scene.search

import android.os.Bundle
import com.riku.land.cs.githubsercher.BaseActivity
import com.riku.land.cs.githubsercher.R

/**
 * Created by riku_maehara on 17/02/08.
 */

public class SearchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, SearchFragment())
            commit()
        }
    }
}
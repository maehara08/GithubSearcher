package com.riku.land.cs.githubsearcher.ui.adapter

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.riku.land.cs.githubsearcher.R
import com.riku.land.cs.githubsearcher.bindView
import com.riku.land.cs.githubsearcher.model.entity.Item
import kotlin.properties.Delegates

/**
 * Created by riku_maehara on 17/02/13.
 */

public class RepositoryAdapter(context: Context) : ArrayAdapter<Item>(context, R.layout.item_repository) {

    companion object {
        const val TAG = "RepositoryAdapter"
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = getItem(position)
        val inflateView = LayoutInflater.from(context).inflate(R.layout.item_repository, parent, false)

        val viewHolder = ViewHolder(inflateView)

        viewHolder.apply {
            nameTextView.text = item.fullName
            if (TextUtils.isEmpty(item.description)) {
                descriptionTextView.visibility = View.GONE
            }
            descriptionTextView.text = item.description
            languageTextView.text = item.language
        }

        return inflateView
    }

    inner class ViewHolder(inflateView: View) {
        var nameTextView: TextView by Delegates.notNull()
        var descriptionTextView: TextView by Delegates.notNull()
        var languageTextView: TextView by Delegates.notNull()

        init {
            nameTextView = inflateView.bindView(R.id.item_name_text_view)
            descriptionTextView = inflateView.bindView(R.id.item_description_text_view)
            languageTextView = inflateView.bindView(R.id.item_language_text_view)
        }
    }
}
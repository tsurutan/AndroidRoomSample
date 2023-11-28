package com.example.sampleapp2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleapp2.api.Entry
import com.example.sampleapp2.R
import java.util.logging.Logger


class EntryAdapter(private val dataSet: Array<Entry>) :
    RecyclerView.Adapter<EntryAdapter.ViewHolder>() {
    private val logger = Logger.getLogger(EntryAdapter::class.java.name)

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val description: TextView
        val category: TextView
        val link: TextView

        init {
            description = view.findViewById(R.id.description)
            category = view.findViewById(R.id.category)
            link = view.findViewById(R.id.link)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_text_row, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val entry = dataSet[position]
        viewHolder.description.text = entry.description
        viewHolder.category.text = entry.category
        viewHolder.link.text = entry.link
    }

    override fun getItemCount() = dataSet.size

}
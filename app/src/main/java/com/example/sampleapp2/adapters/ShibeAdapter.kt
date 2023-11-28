package com.example.sampleapp2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampleapp2.R

class ShibeAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<ShibeAdapter.ShibeViewHolder>() {

    class ShibeViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val url: TextView = view.findViewById(R.id.url)
        val image: ImageView = view.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ShibeViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_shibe_row, viewGroup, false)

        return ShibeViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ShibeViewHolder, position: Int) {
        val url = dataSet[position]
        viewHolder.url.text = url
        Glide.with(viewHolder.view).load(url).into(viewHolder.image)
    }

    override fun getItemCount() = dataSet.size

}
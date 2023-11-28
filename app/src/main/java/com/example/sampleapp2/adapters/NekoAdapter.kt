package com.example.sampleapp2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sampleapp2.R
import com.example.sampleapp2.api.Neko

class NekoAdapter(private val dataSet: Array<Neko>) :
    RecyclerView.Adapter<NekoAdapter.NekoViewHolder>() {

    class NekoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val artistName: TextView = view.findViewById(R.id.artist_name)
        val url: TextView = view.findViewById(R.id.url)
        val image: ImageView = view.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): NekoViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.layout_neko_row, viewGroup, false)

        return NekoViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: NekoViewHolder, position: Int) {
        val neko = dataSet[position]
        viewHolder.artistName.text = neko.artistName
        viewHolder.url.text = neko.url
        Glide.with(viewHolder.view).load(neko.url).into(viewHolder.image)
    }

    override fun getItemCount() = dataSet.size

}

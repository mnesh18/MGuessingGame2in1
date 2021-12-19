package com.example.mguessinggame2in1


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter2(private val colors: List<String>): RecyclerView.Adapter<RecyclerViewAdapter2.ItemViewHolder>() {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row2,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val color = colors[position]

        holder.itemView.apply {
            val tvColor = findViewById<TextView>(R.id.tvColor)
            tvColor.text = color
        }
    }

    override fun getItemCount(): Int = colors.size
}
package com.example.mguessinggame2in1


import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mguessinggame2in1.R

class RecyclerViewAdapter(private val results: List<String>):RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = results[position]

        holder.itemView.apply {
            val tvResults = findViewById<TextView>(R.id.tvResults)
            tvResults.text = result

            if (result.startsWith("Wrong") || result.startsWith("Game"))
                tvResults.setTextColor(Color.RED)
            else if (result.startsWith("Found"))
                tvResults.setTextColor(Color.GREEN)
            else
                tvResults.setTextColor(Color.BLACK)
        }
    }

    override fun getItemCount(): Int = results.size
}
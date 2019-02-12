package com.lavanya.firebase_kotlin.Adapters

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lavanya.firebase_kotlin.Models.Note
import com.lavanya.firebase_kotlin.R
import kotlinx.android.synthetic.main.item_row.view.*

class CustomAdapter(private val notes: ArrayList<Note>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val curr = notes[position]
        Log.e("TAG", "size in adapter is ${notes.size}")
        Log.e("TAG", "value from adapter is  ${curr.message}")

        with(holder.itemView) {
            tv_textMessage.text = curr.message
        }
    }
}
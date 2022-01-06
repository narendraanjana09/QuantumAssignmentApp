package com.nsa.quantumassignmentapp.ui.home.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nsa.quantumassignmentapp.databinding.Level3LayoutBinding
import com.nsa.quantumassignmentapp.databinding.Level4LayoutBinding
import com.nsa.quantumassignmentapp.databinding.LooperLayoutBinding
import com.nsa.quantumassignmentapp.ui.home.models.data

class Level4_Adapter(private var list:ArrayList<data>) : RecyclerView.Adapter<Level4_Adapter.ViewHolder>() {
    private lateinit var binding: Level4LayoutBinding
    inner class ViewHolder(binding: Level4LayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: data) {
            binding.apply {
                imageView.setImageDrawable(data.drawable)
                textview.setText(data.text)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= Level4LayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(list.get(position))
    }

    override fun getItemCount(): Int {
      return  list.size
    }
}
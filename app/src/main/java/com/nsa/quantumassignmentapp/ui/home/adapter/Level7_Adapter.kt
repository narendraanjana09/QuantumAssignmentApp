package com.nsa.quantumassignmentapp.ui.home.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nsa.quantumassignmentapp.databinding.*
import com.nsa.quantumassignmentapp.ui.home.models.data

class Level7_Adapter() : RecyclerView.Adapter<Level7_Adapter.ViewHolder>() {
    private lateinit var binding: Level7LayoutBinding
    inner class ViewHolder(binding: Level7LayoutBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding= Level7LayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
      return  3
    }
}
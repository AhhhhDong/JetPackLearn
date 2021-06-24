package com.wjd.jetpacklearn.room

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wjd.jetpacklearn.databinding.ItemStudentBinding
import com.wjd.jetpacklearn.room.entity.Student

class StudentAdapter : RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    val data = ArrayList<Student>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.binding.tvNum.text = "编号:${item.id}"
        holder.binding.tvName.text = "姓名:${item.name}"
        holder.binding.tvAge.text = "年龄:${item.age}"
    }

    class ViewHolder(val binding: ItemStudentBinding) : RecyclerView.ViewHolder(binding.root)
}
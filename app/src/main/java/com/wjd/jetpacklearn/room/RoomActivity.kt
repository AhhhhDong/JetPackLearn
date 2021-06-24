package com.wjd.jetpacklearn.room

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wjd.jetpacklearn.databinding.ActivityRoomBinding

class RoomActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewBinding: ActivityRoomBinding
    private var studentAdapter: StudentAdapter? = null
    private lateinit var viewModel: RoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initView()
        initData()
    }

    private fun initView() {
        initAdapter()
        viewBinding.btnAdd.setOnClickListener(this)
        viewBinding.btnDelete.setOnClickListener(this)
        viewBinding.btnEdit.setOnClickListener(this)
    }

    private fun initAdapter() {
        viewBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        studentAdapter = StudentAdapter()
        viewBinding.recyclerView.adapter = studentAdapter
    }

    private fun initData() {
        viewModel = ViewModelProvider(this).get(RoomViewModel::class.java)
        viewModel.studentListLiveData.observe(this, Observer {
            studentAdapter?.run {
                data.clear()
                data.addAll(it)
                notifyDataSetChanged()
            }
        })
    }

    override fun onClick(v: View) {
        val name = viewBinding.etName.text.toString()
        val age = viewBinding.etAge.text.toString().toIntOrNull() ?: 0
        when (v.id) {
            viewBinding.btnAdd.id -> viewModel.addStudent(name, age)
            viewBinding.btnDelete.id -> viewModel.deleteStudent(name)
            viewBinding.btnEdit.id -> viewModel.editStudent(name, age)
        }
    }
}
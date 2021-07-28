package com.example.databindingdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.databindingdemo.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = makeApiCall()

        setUpBinding(viewModel)
    }

    private fun setUpBinding(viewModel: MyViewModel) {
        val activityBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityBinding.setVariable(BR.viewModel, viewModel)
        activityBinding.executePendingBindings()


        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decoration = DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
            addItemDecoration(decoration)
        }
    }

    fun makeApiCall(): MyViewModel{
        val viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, Observer<RecyclerList>{
            progressBar.visibility = GONE
            if (it != null) {
                viewModel.setAdapter(it.items)
            } else {
                Toast.makeText(this, "Error in fetching data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.makeAPICall("newyork")

        return viewModel
    }
}
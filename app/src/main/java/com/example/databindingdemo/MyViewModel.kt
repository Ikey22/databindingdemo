package com.example.databindingdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyViewModel: ViewModel() {


    var recyclerListData: MutableLiveData<RecyclerList> = MutableLiveData()
    var recyclerViewAdapter : MyAdapter = MyAdapter()

    fun getRecyclerListDataObserver(): MutableLiveData<RecyclerList> {
        return recyclerListData
    }

    fun getAdapter() : MyAdapter {
        return recyclerViewAdapter
    }

    fun setAdapter(data: ArrayList<RecyclerData>) {
        recyclerViewAdapter.setDatalist(data)
        recyclerViewAdapter.notifyDataSetChanged()
    }

    fun makeAPICall(input: String) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getDataFromAPI(input)
        call.enqueue(object : Callback<RecyclerList>{
            override fun onResponse(call: Call<RecyclerList>, response: Response<RecyclerList>) {
                if (response.isSuccessful) {
                    recyclerListData.postValue(response.body())
                }
                else {
                    recyclerListData.postValue(null)
                }
            }

            override fun onFailure(call: Call<RecyclerList>, t: Throwable) {
                recyclerListData.postValue(null)            }

        })
    }
}
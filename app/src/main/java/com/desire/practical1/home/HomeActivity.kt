package com.desire.practical1.home

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserHandle
import android.util.Log
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.desire.practical1.R
import com.desire.practical1.api.User
import com.desire.practical1.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: HomeAdapter
    var listOfData = arrayListOf<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        ivBack()

    }

    private fun initView() {
        var call: Call<ArrayList<User>> = com.desire.practical1.api.Retrofit.api.getData()
        call.enqueue(object : Callback<ArrayList<User>> {

            override fun onResponse(
                call: Call<ArrayList<User>>,
                response: Response<ArrayList<User>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        listOfData.addAll(it)
                        adapter = HomeAdapter(listOfData)
                        binding.rcvAllProduct.adapter = adapter
                        binding.rcvAllProduct.layoutManager =
                            GridLayoutManager(this@HomeActivity, 2)

                        adapter.onItemClick = { item ->
                            Log.i("test", "${item.id}")
                            initViewDelete(item.id)

                            val index = adapter.listOfData.indexOfFirst { it.id == item.id }
                            adapter.deleteItem(index)
                        }
                    }
                }
            }
            override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun initViewDelete(id: Int) {
        var call: Call<Unit> = com.desire.practical1.api.Retrofit.api.deleteData(id)
        call.enqueue(object : Callback<Unit> {

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@HomeActivity, "Deleted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@HomeActivity, "Failed to Deleted", Toast.LENGTH_SHORT)
                        .show()
                }
            }

            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "Failed to Deleted", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun ivBack() {
        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}
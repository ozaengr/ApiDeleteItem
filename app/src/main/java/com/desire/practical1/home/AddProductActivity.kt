package com.desire.practical1.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.desire.practical1.R
import com.desire.practical1.api.AddRequest
import com.desire.practical1.api.AddResponse
import com.desire.practical1.api.Retrofit
import com.desire.practical1.api.User
import com.desire.practical1.databinding.ActivityAddProductBinding
import com.desire.practical1.databinding.ActivityAllProductBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.addProduct.setOnClickListener {
            if (binding.edittextName.text.toString()
                    .isEmpty() || binding.edittextCategory.text.toString()
                    .isEmpty() || binding.image.display.toString().isEmpty()
            ) {
                Toast.makeText(this, "All Fields are mendatory", Toast.LENGTH_SHORT)
                    .show()
            } else {

                var model = AddRequest(
                    binding.edittextName.text.toString(),
                    binding.edittextCategory.text.toString(),
                    "https://www.gstatic.com/webp/gallery3/1.sm.png",0
                )
                callApiToAddItem(model)
                finish()
            }
        }
    }

    private fun callApiToAddItem(model: AddRequest) {
        var call: Call<AddResponse> = Retrofit.api.addItem(model)
        call.enqueue(object : Callback<AddResponse> {

            override fun onResponse(call: Call<AddResponse>, response: Response<AddResponse>) {
                if (response.isSuccessful && response.body()!= null) {
                    Toast.makeText(this@AddProductActivity,"Success", Toast.LENGTH_SHORT).show()
                    Log.i("test", "Item Added")
                    Log.i("test","Our Post : ${response.body()}")
                }
            }

            override fun onFailure(call: Call<AddResponse>, t: Throwable) {
                Toast.makeText(this@AddProductActivity,"UnSuccessful", Toast.LENGTH_SHORT).show()
            }

        })
    }
}